package edu.exam.online.professional.domain;
import java.io.Serializable;

   /**
    * totalscores 实体类
    * authorName guodandan
    */ 
public class Totalscores implements Serializable {
	private static final long serialVersionUID = 1L;
   /**
    * id
    */ 
	private String id;
   /**
    * 考试id
    */ 
	private String examid;
   /**
    * 考生id
    */ 
	private String userid;
   /**
    * 考试成绩
    */ 
	private String score;
   /**
    * 提交时间
    */ 
	private long createtime;
   /**
    * 状态
    */ 
	private String status;
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setExamid(String examid){
		this.examid=examid;
	}
	public String getExamid(){
		return examid;
	}
	public void setUserid(String userid){
		this.userid=userid;
	}
	public String getUserid(){
		return userid;
	}
	public void setScore(String score){
		this.score=score;
	}
	public String getScore(){
		return score;
	}
	public void setCreatetime(long createtime){
		this.createtime=createtime;
	}
	public long getCreatetime(){
		return createtime;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public String toString() {
		return " totalscores [id="+id+  ", examid="+examid+ ", userid="+userid+ ", score="+score+ ", createtime="+createtime+ ", status="+status+"]"; 
	}
}

