package edu.exam.online.professional.mapper;

import edu.exam.online.professional.domain.Records;
import edu.exam.online.professional.domain.Totalscores;
import edu.exam.online.professional.domain.TotalscoresDTO;
import edu.exam.online.professional.mapper.provider.RecordsProvider;
import edu.exam.online.professional.mapper.provider.TotalscoresProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by licy13 on 2016/4/11.
 */
public interface TotalscoresMapper {

    /**
     * 批量添加或更新考生成绩信息
     * @param totalscores
     */
    @InsertProvider(type=TotalscoresProvider.class,method="batchAddOrUpdateTotalscore")
    public void batchAddOrUpdateTotalscore( List<Totalscores> totalscores);

    /**
     * 添加考生考试成绩
     *
     * @param totalscores
     */
    @Insert("INSERT INTO `totalscores` (`id`, `examid`, `userid`, `score`, `createtime`) VALUES (#{totalscores.id}, #{totalscores.examid}, #{totalscores.userid}, #{totalscores.score}, #{totalscores.createtime})")
    public int insertTotalscores(@Param("totalscores") Totalscores totalscores);

    /**
     *
     * @param examid
     * @param userid
     * @param status
     * @return
     */
    @Insert("UPDATE `totalscores` SET status=#{status} where examid=#{examid} and userid=#{userid}")
    public int updatestatus(@Param("examid") String examid,@Param("userid") String userid,@Param("status") String status);

    /**
     * 更新考生考试成绩
     *
     * @param totalscores
     */
    @Update("UPDATE `totalscores` SET  `examid`=#{totalscores.examid}, `userid`=#{totalscores.userid}, `score`=#{totalscores.score}, `createtime`=#{totalscores.createtime} WHERE `id`=#{totalscores.id}")
    public int updateTotalscores(@Param("totalscores") Totalscores totalscores);

    /**
     * 更新成绩
     * @param totalscores
     * @return
     */
    @Update("UPDATE `totalscores` SET   `score`=#{totalscores.score} WHERE `examid`=#{totalscores.examid} and `userid`=#{totalscores.userid}")
    public int updatescores(@Param("totalscores") Totalscores totalscores);

    /**
     * 删除考生考试成绩
     *
     * @param id
     */
    @Delete("Delete from totalscores where id=#{id}")
    public int deleteTotalscores(@Param("id") String id);

    /**
     * 根据考试id删除成绩
     * @param examid
     * @return
     */
    @Delete("Delete from totalscores where examid=#{examid}")
    public int delTotalscoresByExamid(String examid);

    /**
     * 根据ids批量删除考试成绩
     * @param ids
     */
    @DeleteProvider(type=TotalscoresProvider.class,method="batchdeleteTotalscore")
    public void batchdeleteTotalscore( List<String> ids);
    /**
     * 根据id查询考生考试成绩
     *
     * @param id
     * @return
     */
    @Select("Select * from totalscores where id=#{id}")
    public Totalscores getTotalscoresById(@Param("id") String id);

    /**
     * 获取所有考生考试成绩
     *
     * @return
     */
    @Select("Select * from totalscores  order by createtime desc")
    public List<Totalscores> getTotalscores();
    @SelectProvider(type=TotalscoresProvider.class,method="getTotalscore")
    public List<TotalscoresDTO> getTotalscore(@Param("examid") String examid,@Param("userid") String userid);

    /**
     * 根据考试信息获取该次考试所有学生的成绩
     *
     * @param examid
     * @return
     */
    @Select("Select * from totalscores  where examid=#{examid} order by createtime desc")
    public List<Totalscores> getTotalscoresByExamId(@Param("examid") String examid);
    /**
     * 根据考生信息获取该考生所有的考试成绩
     *
     * @param userid
     * @return
     */
    @Select("Select * from totalscores  where userid=#{userid} order by createtime desc")
    public List<Totalscores> getTotalscoresByUserId(@Param("userid") String userid);

    /**
     *
     * @param userid
     * @param examid
     * @return
     */
    @Select("Select * from totalscores  where userid=#{userid} and examid=#{examid}")
    public Totalscores getTotalscoresByUserIdExamId(@Param("userid") String userid,@Param("examid") String examid);

}
