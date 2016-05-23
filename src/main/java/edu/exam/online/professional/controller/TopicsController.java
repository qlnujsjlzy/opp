package edu.exam.online.professional.controller;

import edu.exam.online.professional.domain.Topics;
import edu.exam.online.professional.service.TopicsService;
import edu.exam.online.professional.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by guodandan on 2016/3/29.
 */
@RestController
@RequestMapping("/topic")
public class TopicsController extends BaseController {

    @Autowired
    private TopicsService topicsService;
    /**
     * 添加/更新套题
     * @param topics
     */
    @RequestMapping(value = "/addorupdate" ,method = RequestMethod.POST)
    public DataResult insertorUpdateTopics(@RequestBody Topics topics) throws Exception {
        checkAuth();//校验是否登录
        Topics rc_topics = null;
        try {
            rc_topics = topicsService.insertorUpdateTopics(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_topics);
    }
    /**
     * 添加套题
     * @param topics
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public DataResult insertTopics(@RequestBody Topics topics) throws Exception {
        checkAuth();//校验是否登录
        Topics rc_topics = null;
        try {
            rc_topics = topicsService.insertTopics(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_topics);
    }

    /**
     * 更新套题
     * @param topics
     */
    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public DataResult updateTopics(@RequestBody Topics topics) throws Exception {
        checkAuth();//校验是否登录
        Topics rc_topics = null;
        try {
            rc_topics = topicsService.updateTopics(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_topics);

    }

    /**删除套题（根据id）
     * @param id
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public DataResult deleteTopics(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        try {
            topicsService.deleteTopics(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult();
    }

    /**
     * 根据id查询套题
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public DataResult getTopicsById(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        Topics topics=null;
        try {
            topics = topicsService.getTopicsById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(topics);

    }

    /**
     * 获取所有套题
     * @return
     */
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public DataResult getTopicsAll()  throws Exception {
        checkAuth();//校验是否登录
        List<Topics> topicsAll=null;
        try {
            topicsAll = topicsService.getTopicsAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(topicsAll);
    }
}
