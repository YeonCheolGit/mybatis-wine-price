package main.service.interceptor;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import main.DTO.MemberDTO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Log4j2
public class AuthInterceptor extends WebContentInterceptor {

    @SneakyThrows // throws, try-catch 생략
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        /*
         * 1. HandlerMethod(@Controller) 여부 확인
         * 2. if false then return true; (접속 허용)
         */
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }


        HandlerMethod handlerMethod = (HandlerMethod) handler;

        /*
         * 1. 접속 요청한 HandlerMethod @Auth 여부 확인
         * 2. if @Auth <- null then true; (접속 허용)
         */
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        /*
         * if HandlerMethod && @Auth <- true
         * 1. 로그인 시 저장한 session("member") 가지고 옮
         * 2. if session <- null then return /main
         */
        HttpSession session = request.getSession();
        MemberDTO memberSession = (MemberDTO) session.getAttribute("member");

        if (memberSession == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            // 로그인 필요 팝업 후 /main redirect
            out.println("<script>alert(\"로그인이 필요합니다.\");</script>");
            request.getRequestDispatcher("/main").include(request, response);
            out.close();

            return false; // 인터셉터 false
        }

        /*
         * if HandlerMethod && @Auth && Session <- true
         * 1. if @Auth 권한 <- "ROLE_ADMIN"
         * 2. if 로그인 Session role != "ROLE_ADMIN" then false (접근 불가)
         * 3. else return true; (접근 허용)
         */
        String role = auth.role().toString();

        if ("ROLE_ADMIN".equals(role)) {
            if (!"ROLE_ADMIN".equals(memberSession.getRole())) {
                response.setContentType("text/html; charset=UTF-8");

                PrintWriter out = response.getWriter();
                out.println("<script>alert(\"잘 못 된 접근 입니다.\");</script>");
                request.getRequestDispatcher("/main/errorPage").include(request, response);
                out.close();

                return false;
            }
        }

        /*
         * if HandlerMethod && @Auth && Session <- true
         * 1. if @Auth 권한 <- "ROLE_USER"
         * 2. if 로그인 Session role != "ROLE_USER" then false (접근 불가)
         * 3. else return true; (접근 허용)
         */
        if ("ROLE_USER".equals(role)) {
            if (!"ROLE_USER".equals(memberSession.getRole())) {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();

                // 로그인 필요 팝업 후 /main redirect
                out.println("<script>alert(\"로그인이 필요합니다.\");</script>");
                request.getRequestDispatcher("/main").include(request, response);
                out.close();

                return false; // 인터셉터 false
            }
        }
        return true;
    }
}
