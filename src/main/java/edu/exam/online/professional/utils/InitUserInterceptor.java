package edu.exam.online.professional.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import java.net.URLDecoder;


/**
 * Created by guodandan on 2016/3/22.
 */
public class InitUserInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse response, Object target) throws BusinessException {
        //验证用户是否登录
        SessionUser userSession = (SessionUser)servletRequest.getSession().getAttribute("userInfo");
        if (userSession == null) {//未登录
            ThreadLocalSessionUser.removeUser();
        }else{
            servletRequest.getSession().setAttribute("userInfo", userSession);
            ThreadLocalSessionUser.setUser(userSession);
        }
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        ThreadLocalSessionUser.removeUser();
    }


}
