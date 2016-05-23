package edu.exam.online.professional.utils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;


/**
 * @author licy13
 * @ClassName: AuthUtil
 * @Description: 处理验证的工具类，提供写入和读取验证cookie的方法，提供当前登录用户编号。
 */
public class AuthUtil {
    /**
     * 登录
     * @param userId
     * @param userName
     * @param type
     * @return
     */
    public static SessionUser login(HttpServletRequest request,String userId, String userName,String account,String type,boolean isRemember) {
        SessionUser userSession = (SessionUser)request.getSession().getAttribute("userInfo");
        if (userSession == null) {  //为空--写入缓存
            userSession=new SessionUser(userId,userName,account,type,isRemember);
        }else{
            userSession.setType(type);
            userSession.setUserName(userName);
            userSession.setUserId(userId);
            userSession.setIsRemember(isRemember);
            userSession.setAccount(account);
        }
        //写入或重写缓存
        request.getSession().setAttribute("userInfo", userSession);
        return userSession;
    }

    /**
     * @Title: removeUser
     * @Description: 离开，用户请求操作结束时调用。
     */
    public static void removeUser() {
        ThreadLocalSessionUser.removeUser();
    }

    public static SessionUser getCurrentUser() {
        return ThreadLocalSessionUser.getUser();
    }
    public static void setCurrentUser(SessionUser sessionUser) {
         ThreadLocalSessionUser.setUser(sessionUser);
    }

}
