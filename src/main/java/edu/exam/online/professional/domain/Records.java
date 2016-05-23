package edu.exam.online.professional.domain;
import java.io.Serializable;

   /**
    * records 实体类
    * authorName guodandan
    */ 
public class Records implements Serializable {
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
    * 权重
    */ 
	private String weight;
   /**
    * 考生id
    */ 
	private String userid;
   /**
    * 试题类型
    */ 
	private String type;
   /**
    * 题号-题目排序
    */ 
	private String sort;
   /**
    * 试题id
    */ 
	private String testid;
   /**
    * 题目
    */ 
	private String title;
   /**
    * 选项（##A##B##C##D）
    */ 
	private String options;
   /**
    * 答案
    */ 
	private String answer;
   /**
    * 考生答案
    */ 
	private String myanswer;
   /**
    * 成绩 0 | 1
    */ 
	private String score;
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
	public void setWeight(String weight){
		this.weight=weight;
	}
	public String getWeight(){
		return weight;
	}
	public void setUserid(String userid){
		this.userid=userid;
	}
	public String getUserid(){
		return userid;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setSort(String sort){
		this.sort=sort;
	}
	public String getSort(){
		return sort;
	}
	public void setTestid(String testid){
		this.testid=testid;
	}
	public String getTestid(){
		return testid;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setOptions(String options){
		this.options=options;
	}
	public String getOptions(){
		return options;
	}
	public void setAnswer(String answer){
		this.answer=answer;
	}
	public String getAnswer(){
		return answer;
	}
	public void setMyanswer(String myanswer){
		this.myanswer=myanswer;
	}
	public String getMyanswer(){
		return myanswer;
	}
	public void setScore(String score){
		this.score=score;
	}
	public String getScore(){
		return score;
	}
	public String toString() {
		return " records [id="+id+  ", examid="+examid+ ", weight="+weight+ ", userid="+userid+ ", type="+type+ ", sort="+sort+ ", testid="+testid+ ", title="+title+ ", options="+options+ ", answer="+answer+ ", myanswer="+myanswer+ ", score="+score+"]"; 
	}
}

