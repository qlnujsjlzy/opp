package edu.exam.online.professional.controller;

import edu.exam.online.professional.domain.Groupinfo;
import edu.exam.online.professional.service.GroupinfoService;
import edu.exam.online.professional.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考生分组
 * Created by guodandan on 2016/3/28.
 */
@RestController
@RequestMapping("/group")
public class GroupinfoController extends BaseController {

    @Autowired
    private GroupinfoService groupinfoService;

    /**
     * 添加或更新分组
     * @param groupinfo
     */
    @RequestMapping(value = "/addorupdate" ,method = RequestMethod.POST)
    public DataResult insertorUpdateGroupinfo(@RequestBody Groupinfo groupinfo) throws Exception {
        checkAuth();//校验是否登录
        Groupinfo rc_groupinfo = null;
        try {
            rc_groupinfo = groupinfoService.insertorUpdateGroupinfo(groupinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_groupinfo);
    }
    /**
     * 添加分组
     * @param groupinfo
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public DataResult insertGroupinfo(@RequestBody Groupinfo groupinfo) throws Exception {
        checkAuth();//校验是否登录
        Groupinfo rc_groupinfo = null;
        try {
            rc_groupinfo = groupinfoService.insertGroupinfo(groupinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_groupinfo);
    }

    /**
     * 更新分组
     * @param groupinfo
     */
    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public DataResult updateGroupinfo(@RequestBody Groupinfo groupinfo) throws Exception {
        checkAuth();//校验是否登录
        Groupinfo rc_groupinfo = null;
        try {
            rc_groupinfo = groupinfoService.updateGroupinfo(groupinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_groupinfo);

    }

    /**删除分组（根据id）
     * @param id
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public DataResult deleteGroupinfo(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        try {
            groupinfoService.deleteGroupinfo(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return new DataResult();
    }

    /**
     * 根据id查询分组
     * @param id
     * @return
     */
    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public DataResult getGroupinfoById(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        Groupinfo groupinfo=null;
        try {
             groupinfo = groupinfoService.getGroupinfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return new DataResult(groupinfo);

    }

    /**
     * 获取所有分组
     * @param type
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public DataResult getGroupinfos()  throws Exception {
        checkAuth();//校验是否登录
        List<Groupinfo> groupinfos=null;
        try {
            groupinfos = groupinfoService.getGroupinfos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(groupinfos);
    }
}
