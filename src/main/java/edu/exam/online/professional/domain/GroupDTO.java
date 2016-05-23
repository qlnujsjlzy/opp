package edu.exam.online.professional.domain;

import java.io.Serializable;

/**
 * GroupDTO 实体类
 * guodandan
 */
public class GroupDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * groupid
     */
    private String groupid;
    /**
     * 分组名称
     */
    private String groupname;
    /**
     * 创建时间
     */
    private long createtime;
    /**
     * 用户ID
     */
    private String userid;
    /**
     * 姓名
     */
    private String username;
    /**
     * 准考证号
     */
    private String ticketnumber;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 密码
     */
    private String password;
    /**
     * 1: 正常 0: 锁定  -1：删除
     */
    private String actstatus;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 性别
     */
    private String sex;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }


    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setTicketnumber(String ticketnumber) {
        this.ticketnumber = ticketnumber;
    }

    public String getTicketnumber() {
        return ticketnumber;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setActstatus(String actstatus) {
        this.actstatus = actstatus;
    }

    public String getActstatus() {
        return actstatus;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "groupid='" + groupid + '\'' +
                ", groupname='" + groupname + '\'' +
                ", createtime=" + createtime +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", ticketnumber='" + ticketnumber + '\'' +
                ", idcard='" + idcard + '\'' +
                ", password='" + password + '\'' +
                ", actstatus='" + actstatus + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

