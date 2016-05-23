package edu.exam.online.professional.mapper;

import edu.exam.online.professional.domain.Exam;
import edu.exam.online.professional.domain.Exam;
import edu.exam.online.professional.domain.ExamVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by licy13 on 2016/4/11.
 */
public interface ExamMapper {
    /**
     * 添加考试信息
     *
     * @param exam
     */
    @Insert("INSERT INTO `exam` (`id`, `examname`, `topicid`, `groupid`, `begintime`, `endtime`) VALUES (#{exam.id}, #{exam.examname}, #{exam.topicid}, #{exam.groupid}, #{exam.begintime}, #{exam.endtime})")
    public int insertExam(@Param("exam") Exam exam);

    /**
     * 更新考试信息
     *
     * @param exam
     */
    @Update("UPDATE `exam` SET  `examname`=#{exam.examname}, `topicid`=#{exam.topicid}, `groupid`=#{exam.groupid}, `begintime`=#{exam.begintime}, `endtime`=#{exam.endtime} WHERE `id`=#{exam.id}")
    public int updateExam(@Param("exam") Exam exam);

    /**
     * 删除考试信息
     *
     * @param id
     */
    @Delete("Delete from exam where id=#{id}")
    public int deleteExam(@Param("id") String id);

    /**
     * 根据id查询考试信息
     *
     * @param id
     * @return
     */
    @Select("Select * from exam where id=#{id}")
    public Exam getExamById(@Param("id") String id);

    @Select("Select e.`id`, `examname`, `topicid`,t.`name` topicname, `groupid`,g.groupname, `begintime`, `endtime` from exam e INNER JOIN groupinfo g ON g.id=e.groupid INNER JOIN topics t ON t.id=e.topicid  where e.id=#{id} ")
    public ExamVO getExamVOById(@Param("id") String id);

    /**
     * 获取所有考试信息
     *
     * @param type
     * @return
     */
    @Select("Select * from exam  order by begintime desc")
    public List<Exam> getExams();

    @Select("Select e.`id`, `examname`, `topicid`,t.`name` topicname, `groupid`,g.groupname, `begintime`, `endtime` from exam e INNER JOIN groupinfo g ON g.id=e.groupid INNER JOIN topics t ON t.id=e.topicid order by begintime desc")
    public List<ExamVO> getExamVOs();
}
