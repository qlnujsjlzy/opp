package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.Totalscores;
import edu.exam.online.professional.domain.TotalscoresDTO;
import edu.exam.online.professional.mapper.TotalscoresMapper;
import edu.exam.online.professional.service.TotalscoresService;
import edu.exam.online.professional.utils.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by licy13 on 2016/4/12.
 */
@Service
public class TotalscoresServiceImpl  implements TotalscoresService {
    @Autowired
    private TotalscoresMapper totalscoresMapper;

    /**
     * 批量添加或更新考生成绩信息
     *
     * @param totalscores
     */
    public void batchAddOrUpdateTotalscore(List<Totalscores> totalscores) throws Exception {
        totalscoresMapper.batchAddOrUpdateTotalscore(totalscores);
    }

    /**
     * 添加考生考试成绩
     *
     * @param totalscores
     */
    public Totalscores insertTotalscores(Totalscores totalscores) throws Exception {
        totalscores.setId(ObjectId.get().toStringMongod());
        totalscores.setCreatetime(new Date().getTime());
        totalscoresMapper.insertTotalscores(totalscores);
        return totalscores;
    }

    /**
     * 更新考生考试成绩
     *
     * @param totalscores
     */
    public Totalscores updateTotalscores(Totalscores totalscores) throws Exception {
        totalscores.setCreatetime(new Date().getTime());
        totalscoresMapper.updateTotalscores(totalscores);
        return totalscores;
    }

    /**
     * 删除考生考试成绩
     *
     * @param id
     */
    public boolean deleteTotalscores(String id) throws Exception {
        totalscoresMapper.deleteTotalscores(id);
        return true;
    }

    /**
     * 根据id查询考生考试成绩
     *
     * @param id
     * @return
     */
    public Totalscores getTotalscoresById(String id) throws Exception {
        Totalscores totalscores = totalscoresMapper.getTotalscoresById(id);
        return totalscores;
    }

    /**
     * 获取所有考生考试成绩
     *
     * @return
     */
    public List<Totalscores> getTotalscores() throws Exception {
        List<Totalscores> totalscoresList = totalscoresMapper.getTotalscores();
        return totalscoresList;
    }

    /**
     * 根据考试信息获取该次考试所有学生的成绩
     *
     * @param examid
     * @return
     */
    public List<Totalscores> getTotalscoresByExamId(String examid) throws Exception {
        List<Totalscores> totalscoresList = totalscoresMapper.getTotalscoresByExamId(examid);
        return totalscoresList;
    }

    /**
     * 根据考生信息获取该考生所有的考试成绩
     *
     * @param userid
     * @return
     */
    public List<Totalscores> getTotalscoresByUserId(String userid) throws Exception {
        List<Totalscores> totalscoresList = totalscoresMapper.getTotalscoresByUserId(userid);
        return totalscoresList;
    }

    /**
     * @param examid
     * @param userid
     * @return
     * @throws Exception
     */
    public List<TotalscoresDTO> getTotalscore(String examid, String userid) throws Exception {
        List<TotalscoresDTO> totalscoresList = totalscoresMapper.getTotalscore(examid, userid);
        return totalscoresList;
    }


    public boolean delTotalscoresByExamid(String examid) throws Exception {
        totalscoresMapper.delTotalscoresByExamid(examid);
        return true;
    }
}
