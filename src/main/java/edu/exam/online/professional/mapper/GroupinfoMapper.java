package edu.exam.online.professional.mapper;

import edu.exam.online.professional.domain.Groupinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 考生分组信息
 * Created by licy13 on 2016/4/7.
 */
public interface GroupinfoMapper {
    /**
     * 添加分组
     * @param groupinfo
     */
    @Insert("INSERT INTO `groupinfo` (`id`, `groupname`, `createtime`) VALUES (#{groupinfo.id}, #{groupinfo.groupname}, #{groupinfo.createtime})")
    public void insertGroupinfo(@Param("groupinfo") Groupinfo groupinfo);

    /**
     * 更新分组
     * @param groupinfo
     */
    @Update("UPDATE `groupinfo` SET  `groupname`=#{groupinfo.groupname} WHERE `id`=#{groupinfo.id}")
    public void updateGroupinfo(@Param("groupinfo") Groupinfo groupinfo);

    /**删除分组
     * @param id
     */
    @Delete("Delete from groupinfo where id=#{id}")
    public void deleteGroupinfo(@Param("id") String id);

    /**
     * 根据id查询分组
     * @param id
     * @return
     */
    @Select("Select * from groupinfo where id=#{id} order by createtime desc")
    public Groupinfo getGroupinfoById(@Param("id") String id);

    /**
     * 获取所有分组
     * @param type
     * @return
     */
    @Select("Select * from groupinfo  order by createtime desc")
    public List<Groupinfo> getGroupinfos();
}
