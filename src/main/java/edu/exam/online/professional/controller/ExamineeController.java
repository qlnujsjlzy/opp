package edu.exam.online.professional.controller;

import edu.exam.online.professional.domain.Examinee;
import edu.exam.online.professional.domain.ExamineeVO;
import edu.exam.online.professional.domain.UserVO;
import edu.exam.online.professional.service.ExamineeService;
import edu.exam.online.professional.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 考生信息
 * Created by guodandan on 2016/3/22.
 */
@RestController
@RequestMapping("/examinee")
public class ExamineeController extends BaseController{
    public static Logger log =  LogManager.getLogger(ExamineeController.class.getName());

    @Autowired
    private ExamineeService examineeService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataResult login(@RequestBody UserVO userVO, HttpServletResponse response, HttpServletRequest request) throws Exception {
        String account = userVO.getAccount();
        String password = userVO.getPassword();
        boolean isRemember=userVO.isRemember();
        SessionUser sessionuser;
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return new DataResult(4000, "身份证号或密码为空");
        }
        Examinee examineeByIdCard = null;
        try {
            examineeByIdCard = examineeService.getExamineeByIdCard(account);
        } catch (Exception e) {
            log.error("根据身份证号查询考生信息--{}", e);
            return new DataResult(5000, e.getMessage());
        }
        if (examineeByIdCard == null) {
            return new DataResult(4000, "身份证号或密码错误");
        }
        String psw = MD5Util.MD5(password);//md5处理
        if (psw.equals(examineeByIdCard.getPassword())) {
            try {//账号密码一致--添加用户信息到session中
                String status="examinee";
                if(("999999").equals(examineeByIdCard.getActstatus())){
                    status="admin";
                }
                sessionuser = AuthUtil.login(request, examineeByIdCard.getUserid(), examineeByIdCard.getUsername(),account,status ,isRemember);
            } catch (Exception e) {
                log.error("添加session失败--{}", e.getMessage());
                return new DataResult(5000, "添加session失败");
            }
        } else {
            return new DataResult(4000, "身份证号或密码错误");
        }
        return new DataResult(sessionuser);
    }

    /**
     * 添加或更新考生信息
     *
     * @param examinee
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public DataResult addOrUpdateExaminee(@RequestBody Examinee examinee) throws Exception {
        checkAuth();//校验是否登录
        try {
            examineeService.addOrUpdateExaminee(examinee);
        } catch (Exception e) {
            log.error("添加考生信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult(examinee);
    }

    /**
     * 考生自己更新个人信息
     * @param examineeVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update/examinee", method = RequestMethod.POST)
    public DataResult updateExamineeByExaminee(@RequestBody ExamineeVO examineeVO) throws Exception {
        checkAuth();//校验是否登录
        Examinee examinee;
        try {
             examinee = examineeService.updateExamineectByExaminee(examineeVO);
        } catch (Exception e) {
            log.error("考生修改个人信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult(examinee);
    }
    /**
     * 查询登录用户信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
      public DataResult getLoginExaminee() throws Exception {
        SessionUser user =(SessionUser)checkAuth().getData();//校验是否登录
        //从session中取用户的id，不需要前端传，前端不可信
        Examinee examineeByUserId;
        try {
            examineeByUserId = examineeService.getExamineeByUserId(user.getUserId());
        } catch (Exception e) {
            log.error("查询考生信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult(examineeByUserId);
    }

    /**
     * 根据考生ID获取考生信息
     * @param userid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/{userid}", method = RequestMethod.GET)
    public DataResult getExaminee(@PathVariable String userid) throws Exception {
        SessionUser user =(SessionUser)checkAuth().getData();//校验是否登录
        Examinee examineeByUserId;
        try {
            examineeByUserId = examineeService.getExamineeByUserId(userid);
        } catch (Exception e) {
            log.error("查询考生信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult(examineeByUserId);
    }
    /**
     * 获取所有用户信息（正常、锁定）
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public DataResult getExamineeAll() throws Exception {
        checkAuth();//校验是否登录
        List<Examinee> examineeList;
        try {
            examineeList = examineeService.getExamineeAll();
        } catch (Exception e) {
            log.error("查询考生信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult(examineeList);
    }

    /**
     * 根据状态获取所有用户信息
     * @param actstatus
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAllByActStatus/{actstatus}", method = RequestMethod.GET)
    public DataResult  getExamineeAllByActStatus(@PathVariable String actstatus) throws Exception{
        checkAuth();//校验是否登录
        List<Examinee> examineeList;
        try {
            examineeList = examineeService.getExamineeAllByActStatus(actstatus);
        } catch (Exception e) {
            log.error("根据状态查询考生信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult(examineeList);
    }
    /**
     * 假删考生信息
     * @param userid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{userid}", method = RequestMethod.DELETE)
    public DataResult delExaminee(@PathVariable String userid) throws Exception {
        checkAuth();//校验是否登录
        try {
            examineeService.deleteExamineeByUseridF(userid);
        } catch (Exception e) {
            log.error("假删考生信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult();
    }

    /**
     * 更新考生状态
     * @param userid
     * @param actstatus
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/actstatus/{userid}/{actstatus}", method = RequestMethod.POST)
    public DataResult updateExamineeActstatus(@PathVariable String userid,@PathVariable String actstatus) throws Exception {
        checkAuth();//校验是否登录
        try {
            examineeService.updateExamineectstatusByUserid(userid,actstatus);
        } catch (Exception e) {
            log.error("更新考生状态信息失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult();
    }

    /**
     * 获取考生数量
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public DataResult getExamineeCount() throws Exception {
        checkAuth();//校验是否登录
        int count=0;
        try {
            count= examineeService.getExamineeCount();
        } catch (Exception e) {
            log.error("获取考生总数失败--{}", e.getMessage());
            return new DataResult(5000, e.getMessage());
        }
        return new DataResult(count);
    }


}
