package edu.exam.online.professional.mapper;

import edu.exam.online.professional.domain.Examlib;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 试题库
 * Created by guodandan on 2016/3/28.
 */
public interface ExamlibMapper {
    /**
     * 添加试题库
     * @param examlib
     */
    @Insert("INSERT INTO `examlib` (`id`, `title`, `options`, `answer`, `type`,createtime) VALUES (#{examlib.id}, #{examlib.title}, #{examlib.options},  #{examlib.answer},  #{examlib.type},  #{examlib.createtime})")
    public void insertExamlib(@Param("examlib") Examlib examlib);

    /**
     * 更新试题库（条件id、试题类型单选、多选、判断）
     * @param examlib
     */
    @Update("UPDATE `examlib` SET `title`=#{examlib.title}, `options`=#{examlib.options}, `answer`=#{examlib.answer} WHERE id=#{examlib.id} and  `type`=#{examlib.type}")
    public void updateExamlib(@Param("examlib") Examlib examlib);

    /**删除试题库（根据id）
     * @param id
     */
    @Delete("Delete from examlib where id=#{id}")
    public void deleteExamlib(@Param("id") String id);

    /**
     * 根据id查询试题
     * @param id
     * @return
     */
    @Select("Select * from examlib where id=#{id} order by createtime desc")
    public Examlib getExamlibById(@Param("id") String id);

    /**
     * 根据试题类型，获取所有该类型的试题
     * @param type
     * @return
     */
    @Select("Select * from examlib where type=#{type} order by createtime desc")
    public List<Examlib> getExamlibsByType(@Param("type") String type);
}
