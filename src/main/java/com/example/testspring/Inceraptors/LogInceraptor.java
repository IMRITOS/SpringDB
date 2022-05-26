package com.example.testspring.Inceraptors;

import com.example.testspring.Validators.Validation;
import com.example.testspring.helpers.jwt.JWT;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInceraptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String header = request.getHeader("Authourization");
        JWT jwt = new JWT();
        String[] ListURI = new String[]{"/articles/new", "/articles/edit", "/articles/delete",
                "/users/showUser", "/users/editUser"};

        for(String URI : ListURI){
            if (URI.equals(request.getRequestURI())){
                if(jwt.validateToken(header)==false){
                    String errorMethod = Validation.checkMethod(request.getMethod());
                    response.sendRedirect(errorMethod);
                    return false;
                }
                else if(jwt.validatePassword(header)==false){
                    String errorMethod = Validation.checkMethod(request.getMethod());
                    response.sendRedirect(errorMethod);
                }
                else{
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {
    }
}
