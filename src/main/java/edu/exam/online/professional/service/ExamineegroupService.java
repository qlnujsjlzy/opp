package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Examinee;
import edu.exam.online.professional.domain.Examineegroup;
import edu.exam.online.professional.domain.GroupDTO;

import java.util.List;

/**
 * Created by licy13 on 2016/4/8.
 */
public interface ExamineegroupService {

    /**
     * 批量添加或更新 分组与考生
     * @param examineegroups
     * @return
     * @throws Exception
     */
    public List<Examineegroup> batchAddOrUpdateExamineegroup( List<Examineegroup> examineegroups) throws Exception;

    /**
     * 删除
     * @param groupid
     * @param userid
     * @return
     * @throws Exception
     */
    public boolean deleteExamineegroup(String groupid,String userid)throws Exception;

    /**
     * 获取分组里的考生信息
     * @param groupid
     * @param userid
     * @return
     * @throws Exception
     */
    public List<GroupDTO> getExamineegroup(String groupid,String userid)throws Exception;
}
