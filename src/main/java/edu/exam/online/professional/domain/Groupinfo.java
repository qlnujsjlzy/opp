package edu.exam.online.professional.domain;
import java.io.Serializable;

   /**
    * groupinfo 实体类
    * authorName guodandan
    */ 
public class Groupinfo implements Serializable {
	private static final long serialVersionUID = 1L;
   /**
    * id
    */ 
	private String id;
   /**
    * 分组名称
    */ 
	private String groupname;
   /**
    * 创建时间
    */ 
	private long createtime;
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setGroupname(String groupname){
		this.groupname=groupname;
	}
	public String getGroupname(){
		return groupname;
	}
	public void setCreatetime(long createtime){
		this.createtime=createtime;
	}
	public long getCreatetime(){
		return createtime;
	}
	public String toString() {
		return " groupinfo [id="+id+  ", groupname="+groupname+ ", createtime="+createtime+"]"; 
	}
}

