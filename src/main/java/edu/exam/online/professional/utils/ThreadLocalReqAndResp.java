package edu.exam.online.professional.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by guodandan on 2016/3/24.
 */
public class ThreadLocalReqAndResp {
    private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
    private static ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();

    public static HttpServletRequest getRequest() {
        return request.get();
    }
    public static void setRequest(HttpServletRequest req) {
        request.set(req);
    }
    public static HttpServletResponse getResponse() {
        return response.get();
    }
    public static void setResponse(HttpServletResponse req) {
        response.set(req);
    }
    public static void closeRequest(){
        request.remove();
    }
    public static HttpSession getSession() {
        return session.get();
    }
    public static void setSession(HttpSession ses) {
        session.set(ses);
    }
    public static void closeSession(){
        session.remove();
    }
}
