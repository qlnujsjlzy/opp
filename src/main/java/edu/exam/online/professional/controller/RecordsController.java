package edu.exam.online.professional.controller;

import edu.exam.online.professional.domain.Records;
import edu.exam.online.professional.service.RecordsService;
import edu.exam.online.professional.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by licy13 on 2016/4/15.
 */
@RestController
@RequestMapping("/records")
public class RecordsController extends BaseController {
    public static Logger log = LogManager.getLogger(RecordsController.class.getName());

    @Autowired
    private RecordsService recordsService;

    /**
     * 添加或更新记录
     *
     * @param records
     */
    @RequestMapping(value = "/addorupdate", method = RequestMethod.POST)
    public DataResult insertorUpdateRecords(@RequestBody List<Records> records)  throws Exception {
        checkAuth();//校验是否登录
        try {
            recordsService.batchAddOrUpdateRecords(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult();
    }

    /**
     * 初始化考卷
     * @param examid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/init/{examid}", method = RequestMethod.GET)
    public DataResult initRecords(@PathVariable String examid) throws Exception {
        SessionUser user =(SessionUser)checkAuth().getData();//校验是否登录
        Map<String, List<Records>> map=new HashMap<String, List<Records>>();
        try {
            map = recordsService.initRecords(user.getUserId(), examid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(map);
    }

    /**
     * 获取历史数据
     * @param examid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/history/{examid}", method = RequestMethod.GET)
    public DataResult historyRecords(@PathVariable String examid) throws Exception {
        SessionUser user =(SessionUser)checkAuth().getData();//校验是否登录
        Map<String, List<Records>> map=new HashMap<String, List<Records>>();
        try {
            map = recordsService.getHistoryRecords(user.getUserId(), examid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(map);
    }
    /**
     * 添加记录
     *
     * @param records
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public DataResult insertRecords(@RequestBody Records records)  throws Exception {
        checkAuth();//校验是否登录
        Records rc_records = null;
        try {
            rc_records = recordsService.insertRecords(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_records);
    }

    /**
     * 更新记录
     *
     * @param records
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult updateRecords(@RequestBody Records records)  throws Exception {
        checkAuth();//校验是否登录
        Records rc_records = null;
        try {
            rc_records = recordsService.updateRecords(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_records);

    }

    /**
     * 删除记录（根据id）
     *
     * @param id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public DataResult deleteRecords(@PathVariable String id)  throws Exception {
        checkAuth();//校验是否登录
        try {
            recordsService.deleteRecords(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult();
    }

    /**
     * 根据id获取记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public DataResult getRecordsById(@PathVariable String id)  throws Exception {
        checkAuth();//校验是否登录
        Records records = null;
        try {
            records = recordsService.getRecordsById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(records);

    }


    /**
     * 根据用户id和考试id，获取所有考试记录
     *
     * @param examid
     * @return
     */
    @RequestMapping(value = "/getall/{examid}/{userid}", method = RequestMethod.GET)
    public DataResult getRecords(@PathVariable String examid, @PathVariable String userid)  throws Exception {
        checkAuth();//校验是否登录
        List<Records> recordss = null;
        try {
            recordss = recordsService.getRecords(examid, userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(recordss);
    }

    /**
     * 跳转页面
     * @param examid
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/open/{examid}", method = RequestMethod.GET)
    public ModelAndView openOnlineExam(@PathVariable String examid) throws Exception {
        return new ModelAndView("redirect:/openexam.html?examid="+examid);
    }

    /**
     * 倒计时时间
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/initTimer/{examid}", method = RequestMethod.GET)
    public String getTimer(@PathVariable String examid,HttpServletRequest request) throws Exception {
        SessionUser user = (SessionUser) checkAuth().getData();
        long initTimer = getInitTimer(String.format("%s_%s",examid,user.getUserId()), request);
        return String.valueOf(initTimer);
    }

    private long getInitTimer(String id, HttpServletRequest request) {
        long initTimer = 100 * 60;
        String session_time = (String) request.getSession().getAttribute(id);
        log.info("倒计时-id[{}]-session_time值{}", id, session_time);
        long currTime = new Date().getTime();
        if (StringUtils.isBlank(session_time)) {
            log.info("倒计时session_time值为空，session-id[{}]初始化-当前时间{}毫秒", id, currTime);
            request.getSession().setAttribute(id, String.valueOf(currTime));
        } else {
            long sessionTime = Long.parseLong(session_time);
            long time = currTime - sessionTime;
            if (time >= initTimer * 1000) {
                initTimer = 0;
            } else {
                initTimer = (initTimer * 1000 - time) / 1000;
            }
        }
        log.info("倒计时返回值{}秒", initTimer);
        return initTimer;
    }

}
