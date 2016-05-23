package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.Groupinfo;
import edu.exam.online.professional.mapper.GroupinfoMapper;
import edu.exam.online.professional.service.GroupinfoService;
import edu.exam.online.professional.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by guodandan on 2016/3/28.
 */
@Service
public class GroupinfoServiceImpl implements GroupinfoService {
    @Autowired
    private GroupinfoMapper groupinfoMapper;

    public Groupinfo insertorUpdateGroupinfo(Groupinfo groupinfo) throws Exception {
        if (StringUtils.isNotBlank(groupinfo.getId())) {
            groupinfo = updateGroupinfo(groupinfo);
        } else {
            groupinfo = insertGroupinfo(groupinfo);
        }
        return groupinfo;
    }

    public Groupinfo insertGroupinfo(Groupinfo groupinfo) throws Exception {
        groupinfo.setId(ObjectId.get().toStringMongod());
        groupinfo.setCreatetime(new Date().getTime());
        groupinfoMapper.insertGroupinfo(groupinfo);
        return groupinfo;
    }

    public Groupinfo updateGroupinfo(Groupinfo groupinfo) throws Exception {
        groupinfoMapper.updateGroupinfo(groupinfo);
        return groupinfo;
    }

    public void deleteGroupinfo(String id) throws Exception {
        groupinfoMapper.deleteGroupinfo(id);
    }

    public Groupinfo getGroupinfoById(String id) throws Exception {
        Groupinfo groupinfo = groupinfoMapper.getGroupinfoById(id);
        return groupinfo;
    }

    public List<Groupinfo> getGroupinfos() throws Exception {
        List<Groupinfo> groupinfos = groupinfoMapper.getGroupinfos();
        return groupinfos;
    }
}
