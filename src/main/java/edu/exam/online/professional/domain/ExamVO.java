package edu.exam.online.professional.domain;

import java.io.Serializable;

/**
 * exam 实体类
 * authorName guodandan
 */
public class ExamVO implements Serializable {
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
     * 套题名称
     */
    private String topicname;
    /**
     * 分组id
     */
    private String groupid;
    /**
     * 分组名称
     */
    private String groupname;
    /**
     * 开始时间
     */
    private long begintime;
    /**
     * 结束时间
     */
    private long endtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamname() {
        return examname;
    }

    public void setExamname(String examname) {
        this.examname = examname;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

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
        return "ExamVO{" +
                "id='" + id + '\'' +
                ", examname='" + examname + '\'' +
                ", topicid='" + topicid + '\'' +
                ", topicname='" + topicname + '\'' +
                ", groupid='" + groupid + '\'' +
                ", groupname='" + groupname + '\'' +
                ", begintime=" + begintime +
                ", endtime=" + endtime +
                '}';
    }
}

