package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Examlib;
import edu.exam.online.professional.domain.Groupinfo;

import java.util.List;

/**
 * Created by guodandan on 2016/3/28.
 */
public interface GroupinfoService {
    /**
     * 添加或更新分组
     * @param groupinfo
     * @return
     * @throws Exception
     */
    public Groupinfo insertorUpdateGroupinfo(Groupinfo groupinfo) throws Exception;
    /**
     * 添加分组
     *
     * @param groupinfo
     */
    public Groupinfo insertGroupinfo(Groupinfo groupinfo) throws Exception;

    /**
     * 更新分组
     *
     * @param groupinfo
     */
    public Groupinfo updateGroupinfo(Groupinfo groupinfo) throws Exception;

    /**
     * 删除分组
     *
     * @param id
     */
    public void deleteGroupinfo(String id) throws Exception;

    /**
     * 根据id查询分组
     *
     * @param id
     * @return
     */
    public Groupinfo getGroupinfoById(String id) throws Exception;

    /**
     * 获取所有分组
     *
     * @param type
     * @return
     */
    public List<Groupinfo> getGroupinfos() throws Exception;
}
