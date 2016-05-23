package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.Examinee;
import edu.exam.online.professional.domain.ExamineeVO;
import edu.exam.online.professional.mapper.ExamineeMapper;
import edu.exam.online.professional.service.ExamineeService;
import edu.exam.online.professional.utils.BusinessException;
import edu.exam.online.professional.utils.MD5Util;
import edu.exam.online.professional.utils.ObjectId;
import edu.exam.online.professional.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guodandan on 2016/3/22.
 */
@Service
public class ExamineeServiceImpl implements ExamineeService {
    public static Logger log =  LogManager.getLogger(ExamineeServiceImpl.class.getName());

    @Autowired
    private ExamineeMapper examineeMapper;

    /**
     * 查询所有考生信息
     *
     * @return
     * @throws Exception
     */
    public List<Examinee> getExamineeAll() throws Exception {
        List<Examinee> examineeList;
        try {
            examineeList = examineeMapper.getExamineeAll();
        } catch (Exception e) {
            log.error("查询所有考生信息失败--{}", e.getMessage());
            throw new BusinessException(5000, "查询所有考生信息失败");
        }
        return examineeList;
    }

    /**
     * 获取考生总数
     *
     * @return
     */
    public int getExamineeCount() {
        return examineeMapper.getExamineeCount();
    }

    public List<Examinee> getExamineeAllByActStatus(String actstatus) throws Exception {
        List<Examinee> examineeList;
        try {
            examineeList = examineeMapper.getExamineeAllByActStatus(actstatus);
        } catch (Exception e) {
            log.error("根据状态查询所有考生信息失败--{}", e.getMessage());
            throw new BusinessException(5000, "根据状态查询所有考生信息失败");
        }
        return examineeList;
    }

    /**
     * 根据身份证号获取考生信息
     *
     * @param idcard
     * @return
     */
    public Examinee getExamineeByIdCard(String idcard) throws Exception {
        if (StringUtils.isBlank(idcard)) {
            throw new BusinessException(4000, "身份证号为空");
        }
        Examinee examineeByIdCard = null;
        try {
            examineeByIdCard = examineeMapper.getExamineeByIdCard(idcard);
        } catch (Exception e) {
            log.error("根据身份证号查询考生信息失败--{}", e.getMessage());
            throw new BusinessException(5000, "根据身份证号查询考生信息失败");
        }
        return examineeByIdCard;
    }

    /**
     * 根据Id获取考生信息
     *
     * @param userId
     * @return
     */
    public Examinee getExamineeByUserId(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            throw new BusinessException(4000, "用户ID为空");
        }
        Examinee examineeByUserid = null;
        try {
            examineeByUserid = examineeMapper.getExamineeByUserid(userId);
        } catch (Exception e) {
            log.error("根据userId查询考生信息失败--{}", e.getMessage());
            throw new BusinessException(5000, "根据userId查询考生信息失败");
        }
        return examineeByUserid;
    }

    /**
     * 添加考生信息
     *
     * @param examinee
     * @return true --成功
     * @throws Exception 报错打印log，向上抛出异常
     */
    public Boolean addOrUpdateExaminee(Examinee examinee) throws Exception {
        if (examinee == null) {
            throw new BusinessException(4000, "Examinee为空");
        }
        //添加考生信息
        if (StringUtils.isBlank(examinee.getUserid())) {//isBlank 过滤空格
            String idcard_C = examinee.getIdcard();
            if (StringUtil.isEmpty(idcard_C)) {
                throw new BusinessException(4000, "身份证号为空");
            }
            //验证用户是否存在
            Examinee examineeByIdCard = getExamineeByIdCard(idcard_C);
            if (examineeByIdCard != null) {
                if (examinee.getIdcard().equals(examineeByIdCard.getIdcard())) {
                    throw new BusinessException(4000, "该考生信息已存在");
                }
            }
            examinee.setUserid(ObjectId.get().toStringMongod());
            /*密码后台根据身份证号截取为准，不适用前台传递的密码*/
            examinee.setPassword(MD5Util.MD5(idcard_C.substring(idcard_C.length() - 6, idcard_C.length())));
            try {
                examineeMapper.insertExaminee(examinee);
            } catch (Exception e) {
                log.error("添加考生信息失败--{}", e.getMessage());
                throw new BusinessException(5000, "添加考生信息失败");
            }
        } else {//更新考生信息
            //验证用户是否存在
            Examinee examineeByUserid = getExamineeByUserId(examinee.getUserid());
            if (examineeByUserid == null) {
                throw new BusinessException(4000, "该考生信息不存在");
            }
            String  n_password=examinee.getPassword();
            if (StringUtils.isNotBlank(n_password)){//重置密码
                examineeByUserid.setPassword(MD5Util.MD5(n_password));
            }
            examineeByUserid.setSex(examinee.getSex());
            examineeByUserid.setUsername(examinee.getUsername());
            examineeByUserid.setPhone(examinee.getPhone());
            examineeByUserid.setEmail(examinee.getEmail());
            examineeByUserid.setAddress(examinee.getAddress());
            try {
                examineeMapper.updateExaminee(examineeByUserid);
            } catch (Exception e) {
                log.error("更新考生信息失败--{}", e.getMessage());
                throw new BusinessException(5000, "更新考生信息失败");
            }
        }
        return true;
    }

    /**
     * 假删考生信息
     * @param userid
     * @return
     * @throws Exception
     */
    public boolean deleteExamineeByUseridF(String userid) throws Exception {
        if (StringUtils.isNotBlank(userid)) {//isBlank 过滤空格
            Examinee examineeByUserid = getExamineeByUserId(userid);
            if (examineeByUserid == null) {
                throw new BusinessException(4000, "该考生信息不存在");
            }
            try {
                examineeMapper.deleteExamineeByUseridF(userid);
            } catch (Exception e) {
                log.error("假删除考生信息失败--{}", e.getMessage());
                throw new BusinessException(5000, "假删除考生信息失败");
            }
        } else {
            throw new BusinessException(4000, "用户ID为空");
        }
        return true;
    }
    public boolean updateExamineectstatusByUserid(String userid,String actstatus) throws Exception {
        if (StringUtils.isNotBlank(userid)&&StringUtils.isNotBlank(actstatus)) {//isBlank 过滤空格
            Examinee examineeByUserid = getExamineeByUserId(userid);
            if (examineeByUserid == null) {
                throw new BusinessException(4000, "该考生信息不存在");
            }
            try {
                examineeMapper.updateExamineectstatusByUserid(userid, actstatus);
            } catch (Exception e) {
                log.error("更新考生状态信息失败--{}", e.getMessage());
                throw new BusinessException(5000, "更新考生状态信息失败");
            }
        } else {
            throw new BusinessException(4000, "用户ID或需要修改的状态为空");
        }
        return true;
    }

    public Examinee updateExamineectByExaminee(ExamineeVO examineeVO) throws Exception {

        if (examineeVO==null){
            throw new BusinessException(4000, "考生信息为null");
        }
        Examinee examinee = getExamineeByUserId(examineeVO.getUserid());
        if (examinee == null) {
            throw new BusinessException(4000, "该考生信息不存在");
        }
        if (!examinee.getPassword().equals(MD5Util.MD5(examineeVO.getPassword()))){
            throw new BusinessException(4000, "原密码不正常，请输入正确的原密码");
        }
        String  n_password=examineeVO.getNewpassword();
        if (StringUtils.isNotBlank(n_password)){//更新密码
            examinee.setPassword(MD5Util.MD5(n_password));
        }
        examinee.setAddress(examineeVO.getAddress());
        examinee.setEmail(examineeVO.getEmail());
        examinee.setPhone(examineeVO.getPhone());
        examinee.setUsername(examineeVO.getUsername());
        examinee.setSex(examineeVO.getSex());

        try {
            examineeMapper.updateExamineeByExaminee(examinee);
        } catch (Exception e) {
            log.error("考生更新信息失败--{}", e.getMessage());
            throw new BusinessException(5000, "考生更新信息失败");
        }
        return examinee;
    }

}