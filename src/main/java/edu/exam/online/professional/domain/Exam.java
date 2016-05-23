package edu.exam.online.professional.domain;
import java.io.Serializable;

   /**
    * exam 实体类
    * authorName guodandan
    */ 
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;
   /**
    * id
    */ 
	private String id;
   /**
    * 
    */ 
	private String examname;
   /**
    * 套题id
    */ 
	private String topicid;
   /**
    * 分组id
    */ 
	private String groupid;
   /**
    * 开始时间
    */ 
	private long begintime;
   /**
    * 结束时间
    */ 
	private long endtime;
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setExamname(String examname){
		this.examname=examname;
	}
	public String getExamname(){
		return examname;
	}
	public void setTopicid(String topicid){
		this.topicid=topicid;
	}
	public String getTopicid(){
		return topicid;
	}
	public void setGroupid(String groupid){
		this.groupid=groupid;
	}
	public String getGroupid(){
		return groupid;
	}
	public void setBegintime(long begintime){
		this.begintime=begintime;
	}
	public long getBegintime(){
		return begintime;
	}
	public void setEndtime(long endtime){
		this.endtime=endtime;
	}
	public long getEndtime(){
		return endtime;
	}
	public String toString() {
		return " exam [id="+id+  ", examname="+examname+ ", topicid="+topicid+ ", groupid="+groupid+ ", begintime="+begintime+ ", endtime="+endtime+"]"; 
	}
}

