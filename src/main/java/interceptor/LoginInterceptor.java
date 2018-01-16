package interceptor;

import domain.Constants;
import domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        //以.do结尾的都需要身份认证
        if(!url.endsWith(".do")){
            return true;
        }
        /* 获取Session */
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.SESSION_KEY);

        if(user != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
//        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        response.sendRedirect("http://localhost:8080/Sian/backend/toLogin");

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
