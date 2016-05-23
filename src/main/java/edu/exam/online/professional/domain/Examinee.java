package edu.exam.online.professional.domain;
import java.io.Serializable;

   /**
    * examinee 实体类
    * guodandan
    */ 
public class Examinee implements Serializable {
	private static final long serialVersionUID = 1L;
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
    * 1: 正常 0: 锁定  -1：删除 999999
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
	public void setUserid(String userid){
		this.userid=userid;
	}
	public String getUserid(){
		return userid;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setTicketnumber(String ticketnumber){
		this.ticketnumber=ticketnumber;
	}
	public String getTicketnumber(){
		return ticketnumber;
	}
	public void setIdcard(String idcard){
		this.idcard=idcard;
	}
	public String getIdcard(){
		return idcard;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setActstatus(String actstatus){
		this.actstatus=actstatus;
	}
	public String getActstatus(){
		return actstatus;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public String getSex(){
		return sex;
	}
	public String toString() {
		return " examinee [userid="+userid+  ", username="+username+ ", ticketnumber="+ticketnumber+ ", idcard="+idcard+ ", password="+password+ ", actstatus="+actstatus+ ", phone="+phone+ ", email="+email+ ", address="+address+ ", sex="+sex+"]"; 
	}
}

