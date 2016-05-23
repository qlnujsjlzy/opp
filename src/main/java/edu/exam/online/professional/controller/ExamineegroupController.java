package edu.exam.online.professional.controller;

import edu.exam.online.professional.domain.Examineegroup;
import edu.exam.online.professional.domain.GroupDTO;
import edu.exam.online.professional.service.ExamineegroupService;
import edu.exam.online.professional.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by licy13 on 2016/4/8.
 */
@RestController
@RequestMapping("/examineegroup")
public class ExamineegroupController extends BaseController {

    public static Logger log =  LogManager.getLogger(ExamineegroupController.class.getName());

    @Autowired
    private ExamineegroupService examineegroupService;

    /**
     *添加或更新分组-考生 关联表
     * @param examineegroups
     * @return
     */
    @RequestMapping(value = "/addorupdate" ,method = RequestMethod.POST)
    public DataResult batchAddOrUpdateExamineegroup(@RequestBody List<Examineegroup> examineegroups) throws Exception {
        checkAuth();//校验是否登录
        List<Examineegroup> rc_examineegroups = null;
        try {
            rc_examineegroups = examineegroupService.batchAddOrUpdateExamineegroup(examineegroups);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_examineegroups);
    }

    /**
     * 根据分组获取分组考生信息，或是根据分组和考生获取考生信息
     * @param groupid
     * @param userid
     * @return
     */
    @RequestMapping(value = "/get/{groupid}" ,method = RequestMethod.GET)
    public DataResult getExamineegroup(@PathVariable String groupid)  throws Exception {
        checkAuth();//校验是否登录
        List<GroupDTO> groupDTOList = null;
        try {
             groupDTOList = examineegroupService.getExamineegroup(groupid, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(groupDTOList);
    }

    /**
     * 删除分组中的考生
     * @param groupid
     * @param userid
     * @return
     */
    @RequestMapping(value = "/delete/{groupid}/{userid}",method = RequestMethod.DELETE)
    public DataResult deleteExamlib(@PathVariable String groupid,@PathVariable String userid) throws Exception {
        checkAuth();//校验是否登录
        try {
            examineegroupService.deleteExamineegroup(groupid, userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult();
    }
}
