package edu.exam.online.professional.utils;

/**
 * 统一的返回信息枚举
 * Created by guodandan on 2016/3/21.
 */
public enum MessageEnum {
    SUCCESS(0, "success"),
    ERROR_PARAM(4000, "提供的参数错误，请您刷新页面重试！"),
    ERROR_ILLEGAL(4000, "参数非法！"),
    ERROR_QUERY(4000, "查询失败! "),
    ERROR_TIMEOUT(5000, "网络异常，请稍后再试!"),
    ERROR_NOT_FOUND(5000, "您处理的对象不存在或已删除，请您刷新页面"),
    ERROR_FORBIDDEN(401, "您没有权限访问当前内容，请返回原来页面"),
    ERROR_NOT_LOGIN(401, "请登陆后操作！"),
    ERROR_NOT_AUTH(401, "用户尚未登录，请您登录后重试!"),
    ERROR_SYSTEM_EXCEPTION(9999, "系统错误");


    private int code;// 编码
    private String common;// 说明

    MessageEnum(Integer code, String common) {
        this.code = code;
        this.common = common;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }


}