package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Records;
import edu.exam.online.professional.mapper.provider.RecordsProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by licy13 on 2016/4/12.
 */
public interface RecordsService {
    /**
     * 初始化考试内容
     * @param userid
     * @param examid
     * @throws Exception
     */
    public Map<String,List<Records>> initRecords(String userid,String examid)throws Exception;

    /**
     * 获取历史数据
     * @param userid
     * @param examid
     * @return
     * @throws Exception
     */
    public Map<String,List<Records>> getHistoryRecords(String userid, String examid) throws  Exception;
    /**
     * 批量添加或更新记录
     * @param records
     */
    public void batchAddOrUpdateRecords( List<Records> records)throws Exception;

    /**
     * 添加考试记录
     *
     * @param records
     */
    public Records insertRecords(Records records)throws Exception;

    /**
     * 更新考试记录
     * @param records
     */
    public Records updateRecords(Records records)throws Exception;

    /**删除考试记录
     * @param id
     */
    public boolean deleteRecords(String id)throws Exception;

    /**
     * 根据id查询考试记录
     * @param id
     * @return
     */
    public Records getRecordsById(String id)throws Exception;

    /**
     * 根据用户id和考试id，获取所有考试记录
     * @param type
     * @return
     */
    public List<Records> getRecords(String examid,String userid) throws Exception;

    /**
     * 判断是否有考试记录
     * @param examid
     * @param userid
     * @return
     * @throws Exception
     */
    public int getRecordsCount(String examid,String userid) throws Exception;
}
