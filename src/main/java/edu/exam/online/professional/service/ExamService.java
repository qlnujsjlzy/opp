package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Exam;
import edu.exam.online.professional.domain.ExamVO;
import edu.exam.online.professional.domain.Examlib;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by licy13 on 2016/4/11.
 */
public interface ExamService {

    /**
     * 新增或更新试题库
     * @param exam
     * @return
     * @throws Exception
     */
    public Exam insertorUpdateExam(Exam exam) throws Exception;
    /**
     * 添加考试信息
     *
     * @param exam
     */
    public Exam insertExam(Exam exam) throws Exception;

    /**
     * 更新考试信息
     * @param exam
     */
    public Exam updateExam(Exam exam) throws Exception;

    /**删除考试信息
     * @param id
     */
    public int deleteExam(String id) throws Exception;

    /**
     * 根据id查询考试信息
     * @param id
     * @return
     */
    public Exam getExamById(String id) throws Exception;
    public ExamVO getExamVOById(String id) throws Exception;

    /**
     * 获取所有考试信息
     * @param type
     * @return
     */
    public List<Exam> getExams() throws Exception;
    public List<ExamVO> getExamVOs() throws Exception;
}
