package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Totalscores;
import edu.exam.online.professional.domain.TotalscoresDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by licy13 on 2016/4/12.
 */
public interface TotalscoresService {
    /**
     * 批量添加或更新考生成绩信息
     *
     * @param totalscores
     */
    public void batchAddOrUpdateTotalscore(List<Totalscores> totalscores)throws Exception;

    /**
     * 添加考生考试成绩
     *
     * @param totalscores
     */
    public Totalscores insertTotalscores(Totalscores totalscores) throws Exception;

    /**
     * 更新考生考试成绩
     *
     * @param totalscores
     */
    public Totalscores updateTotalscores(Totalscores totalscores) throws Exception;

    /**
     * 删除考生考试成绩
     *
     * @param id
     */
    public boolean deleteTotalscores(String id) throws Exception;

    /**
     * 根据id查询考生考试成绩
     *
     * @param boolean
     * @return
     */
    public Totalscores getTotalscoresById(String id) throws Exception;

    /**
     * 获取所有考生考试成绩
     *
     * @return
     */
    public List<Totalscores> getTotalscores() throws Exception;

    /**
     * 根据考试信息获取该次考试所有学生的成绩
     *
     * @param examid
     * @return
     */
    public List<Totalscores> getTotalscoresByExamId(String examid) throws Exception;

    /**
     * 根据考生信息获取该考生所有的考试成绩
     *
     * @param userid
     * @return
     */
    public List<Totalscores> getTotalscoresByUserId(String userid) throws Exception;

    /**
     *
     * @param examid
     * @param userid
     * @return
     * @throws Exception
     */
    public List<TotalscoresDTO> getTotalscore(String examid,String userid) throws Exception;

    /**
     * 根据考试id删除成绩信息
     * @param examid
     * @return
     * @throws Exception
     */
    public boolean delTotalscoresByExamid(String examid) throws Exception;
}
