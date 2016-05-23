package edu.exam.online.professional.domain;

/** 接收登录信息
 * Created by guodandan on 2016/3/24.
 */
public class UserVO {
    private String account;
    private String password;
    private boolean isRemember;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return isRemember;
    }

    public void setIsRemember(boolean isRemember) {
        this.isRemember = isRemember;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", isRemember=" + isRemember +
                '}';
    }
}
