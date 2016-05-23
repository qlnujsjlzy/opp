package edu.exam.online.professional.mapper;

import edu.exam.online.professional.domain.Examineegroup;
import edu.exam.online.professional.domain.GroupDTO;
import edu.exam.online.professional.mapper.provider.ExamineegroupProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by licy13 on 2016/4/8.
 */
public interface ExamineegroupMapper {
    /**
     * 批量添加或更新分组
     * @param examineegroups
     */
    @InsertProvider(type=ExamineegroupProvider.class,method="batchAddOrUpdateExamineegroup")
    public void batchAddOrUpdateExamineegroup( List<Examineegroup> examineegroups);

    /**
     * 删除分组信息
     * @param groupid
     * @param examineeId
     */
    @Delete("delete from examineegroup where `groupid`=#{groupid} and `userid`=#{userid}")
    public void deleteExamineegroup(@Param("groupid") String groupid,@Param("userid") String userid);

    /**
     * 按条件查询分组信息
     * @param groupid
     * @param userid
     * @return
     */
    @SelectProvider(type = ExamineegroupProvider.class, method = "getExamineegroup")
    public List<GroupDTO> getExamineegroup(@Param("groupid") String groupid,@Param("userid") String userid);
}
