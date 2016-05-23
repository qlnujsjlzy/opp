package edu.exam.online.professional.mapper;

import edu.exam.online.professional.domain.Topics;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by guodandan on 2016/3/29.
 */
public interface TopicsMapper {

    /**
     * 添加套题
     * @param topics
     */
    @Insert("INSERT INTO `topics` (`id`, `name`, `singlenumber`, `singleweight`, `multiplenumber`, `multipleweight`, `judgenumber`, `judgeweight`) VALUES (#{topics.id}, #{topics.name}, #{topics.singlenumber}, #{topics.singleweight}, #{topics.multiplenumber}, #{topics.multipleweight}, #{topics.judgenumber}, #{topics.judgeweight})")
    public void insertTopics(@Param("topics") Topics topics);

    /**
     * 更新套题
     * @param topics
     */
    @Update("UPDATE `topics` SET  `name`=#{topics.name}, `singlenumber`=#{topics.singlenumber}, `singleweight`=#{topics.singleweight}, `multiplenumber`=#{topics.multiplenumber}, `multipleweight`=#{topics.multipleweight}, `judgenumber`=#{topics.judgenumber}, `judgeweight`=#{topics.judgeweight} WHERE id=#{topics.id}")
    public void updateTopics(@Param("topics") Topics topics);

    /**删除套题（根据id）
     * @param id
     */
    @Delete("Delete from Topics where id=#{id}")
    public void deleteTopics(@Param("id") String id);

    /**
     * 根据id查询套题
     * @param id
     * @return
     */
    @Select("Select * from topics where id=#{id}")
    public Topics getTopicsById(@Param("id") String id);

    /**
     * 获取所有套题
     * @return
     */
    @Select("Select * from topics")
    public List<Topics> getTopicsAll();
}
