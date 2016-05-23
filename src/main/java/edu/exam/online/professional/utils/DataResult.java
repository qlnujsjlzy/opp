package edu.exam.online.professional.utils;

/**
 * 统一的返回对象
 * Created by guodandan on 2016/3/21.
 */

public class DataResult<T> implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private int rc = 0; //rc 0 成功，其他表示失败
    private String msg; // 返回信息
    private T data;     //数据

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DataResult() {
        this.rc = 0;
        this.msg = "success";
    }

    public DataResult(int rc, String msg, T data) {
        this.rc = rc;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(int rc, String msg) {
        this.rc = rc;
        if(rc!=0&&msg==null){
            this.msg = MessageEnum.ERROR_SYSTEM_EXCEPTION.getCommon();
        }else{
            this.msg = msg;
        }
    }

    public DataResult(T data) {
        this.rc = 0;
        this.msg = "success";
        this.data = data;
    }

    public void setResultInfo(MessageEnum info) {
        this.setRc(info.getCode());
        this.setMsg(info.getCommon());
    }

    public void setResultInfo(MessageEnum info, T data) {
        this.setRc(info.getCode());
        this.setMsg(info.getCommon());
        this.setData(data);
    }
}