package edu.exam.online.professional.controller;

import edu.exam.online.professional.domain.Exam;
import edu.exam.online.professional.domain.ExamVO;
import edu.exam.online.professional.domain.Totalscores;
import edu.exam.online.professional.service.ExamService;
import edu.exam.online.professional.service.ExamService;
import edu.exam.online.professional.service.TotalscoresService;
import edu.exam.online.professional.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考试信息
 * Created by guodandan on 2016/3/28.
 */
@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    @Autowired
    private ExamService examService;

    /**
     * 添加或更新考试信息
     * @param exam
     */
    @RequestMapping(value = "/addorupdate" ,method = RequestMethod.POST)
    public DataResult insertorUpdateExam(@RequestBody Exam exam) throws Exception {
        checkAuth();//校验是否登录
        Exam rc_exam = null;
        try {
            rc_exam = examService.insertorUpdateExam(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_exam);
    }
    /**
     * 添加考试信息
     * @param exam
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public DataResult insertExam(@RequestBody Exam exam) throws Exception {
        checkAuth();//校验是否登录
        Exam rc_exam = null;
        try {
            rc_exam = examService.insertExam(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_exam);
    }

    /**
     * 更新考试信息
     * @param exam
     */
    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public DataResult updateExam(@RequestBody Exam exam)throws Exception {
        checkAuth();//校验是否登录
        Exam rc_exam = null;
        try {
            rc_exam = examService.updateExam(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_exam);

    }

    /**删除考试信息（根据id）
     * @param id
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public DataResult deleteExam(@PathVariable String id)throws Exception {
        checkAuth();//校验是否登录
        try {
            examService.deleteExam(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return new DataResult();
    }

    /**
     * 根据id查询考试信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public DataResult getExamById(@PathVariable String id)throws Exception {
        checkAuth();//校验是否登录
        ExamVO exam=null;
        try {
             exam = examService.getExamVOById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return new DataResult(exam);

    }

    /**
     * 获取所有考试信息
     * @param type
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public DataResult getExamsByType() throws Exception {
        checkAuth();//校验是否登录
        List<ExamVO> exams=null;
        try {
            exams = examService.getExamVOs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(exams);
    }
}
