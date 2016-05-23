package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Examinee;
import edu.exam.online.professional.domain.ExamineeVO;

import java.util.List;

/**
 * Created by guodandan on 2016/3/22.
 */
public interface ExamineeService {
    /**
     * 查询考生所有信息
     * @return
     * @throws Exception
     */
    public List<Examinee> getExamineeAll() throws Exception;
    /**
     * 获取考生总数
     * @return
     */
    public int getExamineeCount();
    /**
     * 查询考生所有信息(根据状态)
     * @return
     * @throws Exception
     */
    public List<Examinee> getExamineeAllByActStatus(String actstatus) throws Exception;
    /**
     * 根据Id获取考生信息
     * @param userId
     * @return
     */
    public Examinee getExamineeByUserId(String userId) throws Exception;

    /**根据身份证号获取考生信息
     * @param idcard
     * @return
     */
    public Examinee getExamineeByIdCard(String idcard) throws Exception;
    /**
     *  添加或更新考生信息
     * @param examinee
     * @return true --成功
     * @throws Exception 报错打印log，向上抛出异常
     */
    public Boolean addOrUpdateExaminee(Examinee examinee) throws Exception;

    /**
     * 删除考生信息
     * @param userid
     * @return
     * @throws Exception
     */
    public boolean deleteExamineeByUseridF(String userid) throws Exception;

    /**
     * 更新考生的状态
     * @param userid
     * @param actstatus
     * @return
     * @throws Exception
     */
    public boolean updateExamineectstatusByUserid(String userid,String actstatus) throws Exception;

    /**
     * 考生自己更新个人信息
     * @param examinee
     * @return Examinee
     * @throws Exception
     */
    public Examinee updateExamineectByExaminee(ExamineeVO examinee) throws Exception;
}
