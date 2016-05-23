package edu.exam.online.professional.controller;
import edu.exam.online.professional.domain.Totalscores;
import edu.exam.online.professional.domain.TotalscoresDTO;
import edu.exam.online.professional.service.TotalscoresService;
import edu.exam.online.professional.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licy13 on 2016/4/15.
 */
@RestController
@RequestMapping("/scores")
public class TotalscoresController extends BaseController {
    @Autowired
    private TotalscoresService totalscoresService;

    /**
     * 添加或更新成绩
     * @param totalscores
     */
    @RequestMapping(value = "/addorupdate" ,method = RequestMethod.POST)
    public DataResult insertorUpdateTotalscores(@RequestBody Totalscores totalscores) throws Exception {
        checkAuth();//校验是否登录
        List<Totalscores> totalscoresList=new ArrayList<Totalscores>();
        totalscoresList.add(totalscores);
        try {
           totalscoresService.batchAddOrUpdateTotalscore(totalscoresList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(totalscores);
    }
    /**
     * 添加成绩
     * @param totalscores
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public DataResult insertTotalscores(@RequestBody Totalscores totalscores) throws Exception {
        checkAuth();//校验是否登录
        Totalscores rc_totalscores = null;
        try {
            rc_totalscores = totalscoresService.insertTotalscores(totalscores);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_totalscores);
    }

    /**
     * 更新成绩
     * @param totalscores
     */
    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public DataResult updateTotalscores(@RequestBody Totalscores totalscores) throws Exception {
        checkAuth();//校验是否登录
        Totalscores rc_totalscores = null;
        try {
            rc_totalscores = totalscoresService.updateTotalscores(totalscores);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(rc_totalscores);

    }

    /**删除成绩（根据id）
     * @param id
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public DataResult deleteTotalscores(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        try {
            totalscoresService.deleteTotalscores(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult();
    }



    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public DataResult getTotalscoresById(@PathVariable String id) throws Exception {
        checkAuth();//校验是否登录
        Totalscores totalscores=null;
        try {
            totalscores = totalscoresService.getTotalscoresById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(totalscores);

    }

    /**
     * 获取所有成绩
     * @param type
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public DataResult getTotalscores()  throws Exception {
        checkAuth();//校验是否登录
        List<TotalscoresDTO> totalscoress=null;
        try {
            totalscoress = totalscoresService.getTotalscore(null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(totalscoress);
    }

    /**
     * 根据考试信息获取该次考试所有学生的成绩
     * @param examid
     * @return
     */
    @RequestMapping(value = "/examid/{examid}",method = RequestMethod.GET)
    public DataResult getTotalscoresByExamId(@PathVariable String examid)  throws Exception {
        checkAuth();//校验是否登录
        List<TotalscoresDTO> totalscoress=null;
        try {
            totalscoress = totalscoresService.getTotalscore(examid, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(totalscoress);
    }

    /**
     * 根据考生信息获取该考生所有的考试成绩
     * @param userid
     * @return
     */
    @RequestMapping(value = "/userid/{userid}",method = RequestMethod.GET)
    public DataResult getTotalscoresByUserId(@PathVariable String userid) throws Exception {
        checkAuth();//校验是否登录
        List<TotalscoresDTO> totalscoress=null;
        try {
            totalscoress = totalscoresService.getTotalscore(null, userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DataResult(totalscoress);
    }

}
