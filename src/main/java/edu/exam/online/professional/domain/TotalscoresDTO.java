package edu.exam.online.professional.domain;

/**
 * Created by licy13 on 2016/4/15.
 */
public class TotalscoresDTO {
    /**
     * id
     */
    private String id;
    /**
     * 考试id
     */
    private String examid;
    private String examname;
    /**
     * 考生id
     */
    private String userid;
    private String username;
    private String idcard;
    /**
     * 考试成绩
     */
    private String score;
    /**
     * 提交时间
     */
    private long createtime;

    private long begintime;
    private long endtime;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getBegintime() {
        return begintime;
    }

    public void setBegintime(long begintime) {
        this.begintime = begintime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "TotalscoresDTO{" +
                "id='" + id + '\'' +
                ", examid='" + examid + '\'' +
                ", examname='" + examname + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", idcard='" + idcard + '\'' +
                ", score='" + score + '\'' +
                ", createtime=" + createtime +
                ", begintime=" + begintime +
                ", endtime=" + endtime +
                ", status='" + status + '\'' +
                '}';
    }
}
