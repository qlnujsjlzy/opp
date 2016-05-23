package edu.exam.online.professional.controller;

import edu.exam.online.professional.domain.Examlib;
import edu.exam.online.professional.service.ExamlibService;
import edu.exam.online.professional.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试题库
 * Created by guodandan on 2016/3/28.
 */
@RestController
@RequestMapping("/examlib")
public class ExamlibController extends BaseController {

    @Autowired
    private ExamlibService examlibService;


    /**
     * 添加或更新试题库
     * @param examlib
     */
    @RequestMapping(value = "/addorupdate" ,method = RequestMethod.POST)
    public DataResult insertorUpdateExamlib(@RequestBody Examlib examlib) throws Exception {
        checkAuth();//校验是否登录
        Examlib rc_examlib = null;
        try {
            rc_examlib = examlibService.insertorUpdateExamlib(examlib);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_examlib);
    }
    /**
     * 添加试题库
     * @param examlib
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public DataResult insertExamlib(@RequestBody Examlib examlib) throws Exception {
        checkAuth();//校验是否登录
        Examlib rc_examlib = null;
        try {
            rc_examlib = examlibService.insertExamlib(examlib);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_examlib);
    }

    /**
     * 更新试题库（条件id、试题类型单选、多选、判断）
     * @param examlib
     */
    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public DataResult updateExamlib(@RequestBody Examlib examlib) throws Exception {
        checkAuth();//校验是否登录
        Examlib rc_examlib = null;
        try {
            rc_examlib = examlibService.updateExamlib(examlib);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_examlib);

    }

    /**删除试题库（根据id）
     * @param id
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public DataResult deleteExamlib(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        try {
            examlibService.deleteExamlib(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return new DataResult();
    }

    /**
     * 根据id查询试题
     * @param id
     * @return
     */
    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public DataResult getExamlibById(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        Examlib examlib=null;
        try {
             examlib = examlibService.getExamlibById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return new DataResult(examlib);

    }

    /**
     * 根据试题类型，获取所有该类型的试题
     * @param type
     * @return
     */
    @RequestMapping(value = "/type/{type}",method = RequestMethod.GET)
    public DataResult getExamlibsByType(@PathVariable String type) throws Exception {
        checkAuth();//校验是否登录
        List<Examlib> examlibs=null;
        try {
            examlibs = examlibService.getExamlibsByType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(examlibs);
    }
}
