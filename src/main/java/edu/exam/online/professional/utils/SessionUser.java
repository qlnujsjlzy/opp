package edu.exam.online.professional.utils;

import java.io.Serializable;

/**
 * Created by guodandan on 2016/3/22.
 */
public class SessionUser implements Serializable {
    /**
     * useId
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 登录账号
     */
    private String account;
    /**
     * 用户类型（anonymous、examinee、admin）
     */
    private String type = "anonymous";

    //是否记住账号
    private boolean isRemember;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setIsRemember(boolean isRemember) {
        this.isRemember = isRemember;
    }

    public SessionUser() {
        super();
    }

    public SessionUser(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public SessionUser(String userId, String userName,String account, String type, boolean isRemember) {
        this.userId = userId;
        this.userName = userName;
        this.type = type;
        this.isRemember = isRemember;
        this.account=account;
    }
}
