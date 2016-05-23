package edu.exam.online.professional.service;

import edu.exam.online.professional.domain.Examlib;

import java.util.List;

/**
 * Created by guodandan on 2016/3/28.
 */
public interface ExamlibService {
    /**
     * 新增或更新试题库
     * @param examlib
     * @return
     * @throws Exception
     */
    public Examlib insertorUpdateExamlib(Examlib examlib) throws Exception;

    /**
     * 添加试题库
     * @param examlib
     */
    public Examlib insertExamlib(Examlib examlib) throws Exception;

    /**
     * 更新试题库（条件id、试题类型单选、多选、判断）
     * @param examlib
     */
    public Examlib updateExamlib(Examlib examlib) throws Exception;

    /**删除试题库（根据id）
     * @param id
     */
    public void deleteExamlib( String id) throws Exception;

    /**
     * 根据id查询试题
     * @param id
     * @return
     */
    public Examlib getExamlibById(String id) throws Exception;

    /**
     * 根据试题类型，获取所有该类型的试题
     * @param type
     * @return
     */
    public List<Examlib> getExamlibsByType(String type) throws Exception;
}
