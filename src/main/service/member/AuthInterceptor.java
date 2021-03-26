package main.service.member;

import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends WebContentInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();

        try {
            if (session.getAttribute("member") == null) { // 로그인 세션이 있으면 없다면, return '/main'
                response.sendRedirect("/main");
                return false;
            }
        } catch (Exception e) {
            System.out.println("============ error ============");
            e.printStackTrace();
        }
        return true; // 로그인 세션이 있다면 접속 허용
    }
}
