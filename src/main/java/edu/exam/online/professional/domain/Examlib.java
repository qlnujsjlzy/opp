package edu.exam.online.professional.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * examlib 实体类
 * guodandan
 */
public class Examlib implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String id;
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
     * 题目类型--单选single、多选multiple，判断judge
     */
    private String type;
    /**
     * 创建时间排序用
     */
    private long createtime = new Date().getTime();

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Examlib{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", options='" + options + '\'' +
                ", answer='" + answer + '\'' +
                ", type='" + type + '\'' +
                ", createtime=" + createtime +
                '}';
    }

}

