package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.*;
import edu.exam.online.professional.mapper.*;
import edu.exam.online.professional.service.RecordsService;
import edu.exam.online.professional.utils.ObjectId;
import edu.exam.online.professional.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by licy13 on 2016/4/12.
 */
@Service
public class RecordsServiceImpl implements RecordsService {
    private  static Logger log= LogManager.getLogger(RecordsServiceImpl.class.getName());
    @Autowired
    private RecordsMapper recordsMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private TopicsMapper topicsMapper;
    @Autowired
    private ExamlibMapper examlibMapper;
    @Autowired
    private TotalscoresMapper totalscoresMapper;

    /**
     * 初始化考试内容
     *
     * @param userid
     * @param examid
     * @throws Exception
     */
    public Map<String,List<Records>> initRecords(String userid, String examid) throws Exception {
        Totalscores ts = totalscoresMapper.getTotalscoresByUserIdExamId(userid, examid);
        Map<String,List<Records>> map=new HashMap<String, List<Records>>();
        if ("0".equals(ts.getStatus())){//成绩状态为0 表示未考试--则生成试卷
            Exam exam = examMapper.getExamById(examid);
            Topics topics = topicsMapper.getTopicsById(exam.getTopicid());//根据套题id获取套题信息
            totalscoresMapper.updatestatus(examid,userid,"1");
            returnMap(userid, examid, topics.getSinglenumber(), topics.getSingleweight(),"single", map);
            returnMap(userid, examid, topics.getMultiplenumber(), topics.getMultipleweight(),"multiple", map);
            returnMap(userid, examid, topics.getJudgenumber(), topics.getJudgeweight(),"judge", map);
        }
        return map;
    }

    private void returnMap(String userid, String examid, String number, String weight,String type, Map<String, List<Records>> map) {
        List<Examlib> examlibs = examlibMapper.getExamlibsByType(type);
        List<Examlib> examlibList = RandomUtils.randomList(examlibs, Integer.parseInt(number));
        final List<Records> records=new ArrayList<Records>();
        int i=1;
        for (Examlib examlib: examlibList){
            Records record = new Records();
            record.setId(ObjectId.get().toStringMongod());
            record.setUserid(userid);
            record.setExamid(examid);
            record.setType(examlib.getType());
            record.setAnswer(examlib.getAnswer());
            record.setOptions(examlib.getOptions());
            record.setTestid(examlib.getId());
            record.setTitle(examlib.getTitle());
            record.setSort(String.valueOf(i));i++;
            record.setWeight(weight);
            records.add(record);
            log.info("-----initRecords--record:{}",record);
        }
        map.put(type,records);
        //开多线程
        new Thread(new Runnable() {
            public void run() {
                recordsMapper.batchAddOrUpdateRecords(records);
            }
        }).start();

    }
    public Map<String,List<Records>> getHistoryRecords(String userid, String examid) throws  Exception{
        Map<String,List<Records>> map=new HashMap<String, List<Records>>();
        List<Records> single = recordsMapper.getRecordsByType(examid, userid, "single");
        map.put("single",single);
        List<Records> multiple = recordsMapper.getRecordsByType(examid, userid, "multiple");
        map.put("multiple",multiple);
        List<Records> judge = recordsMapper.getRecordsByType(examid, userid, "judge");
        map.put("judge",judge);
        return map;
    }

    /**
     * 批量添加或更新记录
     *
     * @param records
     */
    public void batchAddOrUpdateRecords(List<Records> records) throws Exception {
        //更新记录
        List<Records> recordlist=new ArrayList<Records>();
        for (Records record :records){
            if (StringUtils.isNotBlank(record.getId())){
                Records re=recordsMapper.getRecordsById(record.getId());
                re.setMyanswer(record.getMyanswer());
                recordlist.add(re);
            }
        }
        recordsMapper.batchAddOrUpdateRecords(recordlist==null?records:recordlist);
        //更新总成绩
        List<Records> recordlist_n=new ArrayList<Records>();
        int totalscore=0;
        String userid="";
        String examid="";
        for (Records record :records){
            if (StringUtils.isNotBlank(record.getId())){
                Records re=recordsMapper.getRecordsById(record.getId());
                totalscore+=Integer.parseInt(re.getWeight())*Integer.parseInt(re.getScore());
                userid=re.getUserid();
                examid=re.getExamid();
            }
        }
        Totalscores totalscores = new Totalscores();
        totalscores.setUserid(userid);
        totalscores.setExamid(examid);
        totalscores.setScore(String.valueOf(totalscore));
        totalscoresMapper.updatescores(totalscores);

    }

    /**
     * 添加考试记录
     *
     * @param records
     */
    public Records insertRecords(Records records) throws Exception {
        records.setId(ObjectId.get().toStringMongod());
        recordsMapper.insertRecords(records);
        return records;
    }

    /**
     * 更新考试记录
     *
     * @param records
     */
    public Records updateRecords(Records records) throws Exception {
        recordsMapper.updateRecords(records);
        return records;
    }

    /**
     * 删除考试记录
     *
     * @param id
     */
    public boolean deleteRecords(String id) throws Exception {
        recordsMapper.deleteRecords(id);
        return true;
    }

    /**
     * 根据id查询考试记录
     *
     * @param id
     * @return
     */
    public Records getRecordsById(String id) throws Exception {
        Records records=recordsMapper.getRecordsById(id);
        return records;
    }

    /**
     * 根据用户id和考试id，获取所有考试记录
     *
     * @param examid
     * @param userid
     * @return
     */
    public List<Records> getRecords(String examid, String userid) throws Exception {
        List<Records> recordsList=recordsMapper.getRecords(examid,userid);
        return recordsList;
    }

    /**
     * 判断是否有考试记录
     *
     * @param examid
     * @param userid
     * @return
     * @throws Exception
     */
    public int getRecordsCount(String examid, String userid) throws Exception {
        int count=recordsMapper.getRecordsCount(examid,userid);
        return count;
    }
}
