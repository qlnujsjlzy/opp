package edu.exam.online.professional.mapper;
import edu.exam.online.professional.domain.Examineegroup;
import edu.exam.online.professional.domain.Records;
import edu.exam.online.professional.mapper.provider.ExamineegroupProvider;
import edu.exam.online.professional.mapper.provider.RecordsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by licy13 on 2016/4/11.
 */
public interface RecordsMapper {
    /**
     * 批量添加或更新记录
     * @param records
     */
    @InsertProvider(type=RecordsProvider.class,method="batchAddOrUpdateRecords")
    public void batchAddOrUpdateRecords( List<Records> records);

    /**
     * 添加考试记录
     *
     * @param records
     */
    @Insert("INSERT INTO `records` (`id`, `examid`, `weight`, `userid`, `type`, `sort`, `testid`, `title`, `options`, `answer`, `myanswer`, `score`) VALUES (#{records.id}, #{records.examid}, #{records.weight}, #{records.userid}, #{records.type}, #{records.sort}, #{records.testid}, #{records.title}, #{records.options}, #{records.answer}, #{records.myanswer}, #{records.score})")
    public int insertRecords(@Param("records") Records records);

    /**
     * 更新考试记录
     * @param records
     */
    @Update("UPDATE `records` SET  `examid`=#{records.examid}, `weight`=#{records.weight}, `userid`=#{records.userid}, `type`= #{records.type}, `sort`=#{records.sort}, `testid`=#{records.testid}, `title`=#{records.title}, `options`=#{records.options}, `answer`=#{records.answer}, `myanswer`=#{records.myanswer}, `score`=#{records.score} WHERE `id`=#{records.id})")
    public int updateRecords(@Param("records") Records records);

    /**删除考试记录
     * @param id
     */
    @Delete("Delete from records where id=#{id}")
    public int deleteRecords(@Param("id") String id);

    /**
     * 根据id查询考试记录
     * @param id
     * @return
     */
    @Select("Select * from records where id=#{id}")
    public Records getRecordsById(@Param("id") String id);

    /**
     * 根据用户id和考试id，获取所有考试记录
     * @param type
     * @return
     */
    @Select("Select * from records where `examid`=#{examid} and `userid`=#{userid}")
    public List<Records> getRecords(@Param("examid") String examid,@Param("userid") String userid);

    /**
     * 通过题目类型
     * @param examid
     * @param userid
     * @return
     */
    @Select("Select * from records where `examid`=#{examid} and `userid`=#{userid} and type=#{type} order by CAST(sort as SIGNED)")
    public List<Records> getRecordsByType(@Param("examid") String examid,@Param("userid") String userid,@Param("type") String type);

    /**
     * 判定是否有考试记录
     * @param examid
     * @param userid
     * @return
     */
    @Select("Select count(0) from records where `examid`=#{examid} and `userid`=#{userid}")
    public int getRecordsCount(@Param("examid") String examid,@Param("userid") String userid);

}
