package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.domain.Examlib;
import edu.exam.online.professional.mapper.ExamlibMapper;
import edu.exam.online.professional.service.ExamlibService;
import edu.exam.online.professional.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by guodandan on 2016/3/28.
 */
@Service
public class ExamlibServiceImpl implements ExamlibService {
    @Autowired
    private ExamlibMapper examlibMapper;

    public Examlib insertorUpdateExamlib(Examlib examlib) throws Exception {
        if (StringUtils.isNotBlank(examlib.getId())) {
            examlib = updateExamlib(examlib);
        } else {
            examlib = insertExamlib(examlib);
        }
        return examlib;
    }

    public Examlib insertExamlib(Examlib examlib) throws Exception {
        examlib.setId(ObjectId.get().toStringMongod());
        examlib.setCreatetime(new Date().getTime());
        examlibMapper.insertExamlib(examlib);
        return examlib;
    }

    public Examlib updateExamlib(Examlib examlib) throws Exception {
        examlibMapper.updateExamlib(examlib);
        return examlib;
    }

    public void deleteExamlib(String id) throws Exception {
        examlibMapper.deleteExamlib(id);
    }

    public Examlib getExamlibById(String id) throws Exception {
        Examlib examlib = examlibMapper.getExamlibById(id);
        return examlib;
    }

    public List<Examlib> getExamlibsByType(String type) throws Exception {
        List<Examlib> examlibs = examlibMapper.getExamlibsByType(type);
        return examlibs;
    }
}
