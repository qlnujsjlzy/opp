package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.*;
import edu.exam.online.professional.mapper.ExamMapper;
import edu.exam.online.professional.mapper.ExamineegroupMapper;
import edu.exam.online.professional.mapper.TotalscoresMapper;
import edu.exam.online.professional.service.ExamService;
import edu.exam.online.professional.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by licy13 on 2016/4/11.
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private TotalscoresMapper totalscoresMapper;
    @Autowired
    private ExamineegroupMapper examineegroupMapper;


    /**
     * 新增或更新试题库
     *
     * @param exam
     * @return
     * @throws Exception
     */
    public Exam insertorUpdateExam(Exam exam) throws Exception {
        if (StringUtils.isNotBlank(exam.getId())) {
            exam = updateExam(exam);
        } else {
            exam = insertExam(exam);
        }
        return exam;
    }

    /**
     * 添加考试信息
     *
     * @param exam
     */
    public Exam insertExam(Exam exam) throws Exception {
        String  examid=ObjectId.get().toStringMongod();
        exam.setId(examid);
        List < Totalscores > totalscores=new ArrayList<Totalscores>();
        String groupid = exam.getGroupid();
        List<GroupDTO> groupDTOs = examineegroupMapper.getExamineegroup(groupid, null);
        for (GroupDTO groupinfo:groupDTOs){
            Totalscores ts = new Totalscores();
            ts.setId(ObjectId.get().toStringMongod());
            ts.setExamid(examid);
            ts.setUserid(groupinfo.getUserid());
            ts.setScore("-1");
            ts.setCreatetime(new Date().getTime());
            totalscores.add(ts);
        }
        totalscoresMapper.batchAddOrUpdateTotalscore(totalscores);
        examMapper.insertExam(exam);
        return exam;
    }

    /**
     * 更新考试信息
     *
     * @param exam
     */
    public Exam updateExam(Exam exam) throws Exception {
        examMapper.updateExam(exam);
        return exam;
    }

    /**
     * 删除考试信息
     *
     * @param id
     */
    public int deleteExam(String id) throws Exception {
        //先删除考试成绩
        totalscoresMapper.delTotalscoresByExamid(id);
        //再删除考试信息
        return examMapper.deleteExam(id);
    }

    /**
     * 根据id查询考试信息
     *
     * @param id
     * @return
     */
    public Exam getExamById(String id) throws Exception {
        Exam exam = examMapper.getExamById(id);
        return exam;
    }
    public ExamVO getExamVOById(String id) throws Exception {
        ExamVO exam = examMapper.getExamVOById(id);
        return exam;
    }

    /**
     * 获取所有考试信息
     *
     * @return
     */
    public List<Exam> getExams() throws Exception {
        List<Exam> exams = examMapper.getExams();
        return exams;
    }
    public List<ExamVO> getExamVOs() throws Exception {
        List<ExamVO> exams = examMapper.getExamVOs();
        return exams;
    }
}
