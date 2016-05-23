package edu.exam.online.professional.service.impl;

import edu.exam.online.professional.mapper.ExamineeMapper;
import edu.exam.online.professional.utils.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by licy13 on 2016/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml","classpath:springmvc.xml"})
public class ExamineeServiceImplTest {
    @Resource
    private ExamineeMapper examineeMapper;

    @Test
    public void getExamineeCount() throws BusinessException {
       int count= examineeMapper.getExamineeCount();
        System.out.println(count);
    }

    @Test
    public void getExamineeAllByActStatus() throws Exception {

    }

    @Test
    public void getExamineeByIdCard() throws Exception {

    }

}