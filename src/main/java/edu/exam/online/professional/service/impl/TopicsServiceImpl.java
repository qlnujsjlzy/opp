package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.Topics;
import edu.exam.online.professional.mapper.TopicsMapper;
import edu.exam.online.professional.service.TopicsService;
import edu.exam.online.professional.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guodandan on 2016/3/29.
 */
@Service
public class TopicsServiceImpl implements TopicsService {
    @Autowired
    private TopicsMapper topicsMapper;

    public Topics insertorUpdateTopics(Topics topics) throws Exception {
        if (StringUtils.isNotBlank(topics.getId())) {
            topics = updateTopics(topics);
        } else {
            topics = insertTopics(topics);
        }
        return topics;
    }
    public Topics insertTopics(Topics topics) throws Exception {
        topics.setId(ObjectId.get().toStringMongod());
        topicsMapper.insertTopics(topics);
        return topics;
    }

    public Topics updateTopics(Topics topics) throws Exception {
        topicsMapper.updateTopics(topics);
        return topics;
    }

    public boolean deleteTopics(String id) throws Exception {
        topicsMapper.deleteTopics(id);
        return true;
    }

    public Topics getTopicsById(String id) throws Exception {
        Topics topics = topicsMapper.getTopicsById(id);
        return topics;
    }

    public List<Topics> getTopicsAll() throws Exception {
        List<Topics> topicsAll = topicsMapper.getTopicsAll();
        return topicsAll;
    }
}
