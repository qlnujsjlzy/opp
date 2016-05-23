package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Topics;

import java.util.List;


/**
 * Created by guodandan on 2016/3/29.
 */
public interface TopicsService {
    /**
     * 添加或更新套题
     * @param topics
     */
    public Topics insertorUpdateTopics(Topics topics) throws Exception;
    /**
     * 添加套题
     * @param topics
     */
    public Topics insertTopics(Topics topics) throws Exception;

    /**
     * 更新套题
     * @param topics
     */
    public Topics updateTopics( Topics topics) throws Exception;

    /**删除套题（根据id）
     * @param id
     */
    public boolean deleteTopics(String id) throws Exception;

    /**
     * 根据id查询套题
     * @param id
     * @return
     */
    public Topics getTopicsById( String id) throws Exception;

    /**
     * 获取所有套题
     * @return
     */
    public List<Topics> getTopicsAll() throws Exception;
}
