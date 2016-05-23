package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.Examineegroup;
import edu.exam.online.professional.domain.GroupDTO;
import edu.exam.online.professional.domain.Groupinfo;
import edu.exam.online.professional.mapper.ExamineegroupMapper;
import edu.exam.online.professional.mapper.GroupinfoMapper;
import edu.exam.online.professional.service.ExamineegroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by licy13 on 2016/4/8.
 */
@Service
public class ExamineegroupServiceImpl implements ExamineegroupService {
    @Autowired
    private ExamineegroupMapper examineegroupMapper;

    public List<Examineegroup> batchAddOrUpdateExamineegroup(List<Examineegroup> examineegroups) throws Exception {
        examineegroupMapper.batchAddOrUpdateExamineegroup(examineegroups);
        return examineegroups;
    }

    public boolean deleteExamineegroup(String groupid, String userid) throws Exception {
        examineegroupMapper.deleteExamineegroup(groupid, userid);
        return true;
    }
    public List<GroupDTO> getExamineegroup(String groupid,String userid)throws Exception {
        List<GroupDTO> examineegroup = examineegroupMapper.getExamineegroup(groupid, userid);
       return  examineegroup;
    }
}
