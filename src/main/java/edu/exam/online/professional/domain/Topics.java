package edu.exam.online.professional.domain;

import java.io.Serializable;

/**
 * topics 实体类（套题）
 * guodandan
 */
public class Topics implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String id;
    /**
     * 套题名称
     */
    private String name;
    /**
     * 单选题数量（单选题出多少题）
     */
    private String singlenumber;
    /**
     * 单选题权重
     */
    private String singleweight;
    /**
     * 多选题数量
     */
    private String multiplenumber;
    /**
     * 多选题权重
     */
    private String multipleweight;
    /**
     * 判断题数量
     */
    private String judgenumber;
    /**
     * 判断题权重
     */
    private String judgeweight;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSinglenumber(String singlenumber) {
        this.singlenumber = singlenumber;
    }

    public String getSinglenumber() {
        return singlenumber;
    }

    public void setSingleweight(String singleweight) {
        this.singleweight = singleweight;
    }

    public String getSingleweight() {
        return singleweight;
    }

    public void setMultiplenumber(String multiplenumber) {
        this.multiplenumber = multiplenumber;
    }

    public String getMultiplenumber() {
        return multiplenumber;
    }

    public void setMultipleweight(String multipleweight) {
        this.multipleweight = multipleweight;
    }

    public String getMultipleweight() {
        return multipleweight;
    }

    public void setJudgenumber(String judgenumber) {
        this.judgenumber = judgenumber;
    }

    public String getJudgenumber() {
        return judgenumber;
    }

    public void setJudgeweight(String judgeweight) {
        this.judgeweight = judgeweight;
    }

    public String getJudgeweight() {
        return judgeweight;
    }

    public String toString() {
        return " topics [id=" + id + ", name=" + name + ", singlenumber=" + singlenumber + ", singleweight=" + singleweight + ", multiplenumber=" + multiplenumber + ", multipleweight=" + multipleweight + ", judgenumber=" + judgenumber + ", judgeweight=" + judgeweight + "]";
    }
}

