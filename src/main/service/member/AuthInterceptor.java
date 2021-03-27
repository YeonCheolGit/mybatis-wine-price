package main.service.member;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
public class AuthInterceptor extends WebContentInterceptor {

    @SneakyThrows // throws, try-catch 생략
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute("member") != null) { // 로그인 세션이 있으면 와인 목록 접속 허용
                return true;
            } else { // 로그인 세션이 없다면 로그인 필요 팝업 후 redirect /main
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();

                // 로그인 필요 팝업 후 /main redirect
                out.println("<script>alert(\"로그인이 필요합니다\");</script>");
                request.getRequestDispatcher("/main").include(request, response);
                out.close();

                return false; // 인터셉터 false
            }
        } catch (Exception e) {
            log.debug("====================== 인터셉터 에러 ======================");
            log.debug(e.getMessage());
            response.sendRedirect("errorPage.jsp");
            return false;
        }
    }
}
