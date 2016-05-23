package edu.exam.online.professional.utils;

/**
 * Created by guodandan on 2016/3/22.
 */
public class ThreadLocalSessionUser {
    public static ThreadLocal<SessionUser> localUser = new ThreadLocal<SessionUser>();


    public static SessionUser getUser() {
        return localUser.get();
    }

    public static void setUser(SessionUser req) {
        localUser.set(req);
    }


    public static void removeUser(){
        localUser.remove();
    }

}
