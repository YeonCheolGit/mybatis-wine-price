package main.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class AuthLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("member");


        if (obj == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.print("<script>alert('로그인이 필요합니다.'); history.go(-1);</script>");
            out.flush();

            return false;
        }

        return true;
    }
}
