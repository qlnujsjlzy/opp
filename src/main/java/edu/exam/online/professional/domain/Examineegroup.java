package edu.exam.online.professional.domain;
import java.io.Serializable;

   /**
    * examineegroup 实体类
    * authorName guodandan
    */ 
public class Examineegroup implements Serializable {
	private static final long serialVersionUID = 1L;
   /**
    * 分组id
    */ 
	private String groupid;
   /**
    * 考生id
    */ 
	private String userid;
	public void setGroupid(String groupid){
		this.groupid=groupid;
	}
	public String getGroupid(){
		return groupid;
	}
	public void setUserid(String userid){
		this.userid=userid;
	}
	public String getUserid(){
		return userid;
	}
	public String toString() {
		return " examineegroup [groupid="+groupid+  ", userid="+userid+"]"; 
	}
}

