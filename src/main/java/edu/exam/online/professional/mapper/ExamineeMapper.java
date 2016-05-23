package edu.exam.online.professional.mapper;


import edu.exam.online.professional.domain.Examinee;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**对于数据库考生信息的增删改查
 * Created by guodandan on 2016/3/22.
 */
public interface ExamineeMapper {
    /**
     * 根据考生ID获取考生信息
     *
     * @param userid
     * @return
     */
    @Select("select * from examinee where userid=#{userid}")
    public Examinee getExamineeByUserid(@Param("userid") String userid);

    /**
     * 获取考生总数
     * @return
     */
    @Select("SELECT count(0) as count FROM `examinee` where actstatus<> 999999;")
    public int getExamineeCount();

    /**
     * 根据身份证号获取考生信息
     * @param idcard
     * @return
     */
    @Select("select * from examinee where idcard=#{idcard}")
    public Examinee getExamineeByIdCard(@Param("idcard") String idcard);

    /**
     * 根据考生ID和考生状态，获取考生信息
     *
     * @param userid
     * @param actstatus //actstatus 1: 正常 0: 锁定 -1：删除
     * @return
     */
    @Select("select * from examinee where userid=#{userid} and actstatus=#{actstatus}")
    public Examinee getExamineeByUseridAndActStatus(@Param("userid") String userid, @Param("actstatus") int actstatus);

    /**
     * 获取所有考生信息
     *
     * @return
     */
    @Select("select * from examinee where actstatus<> 999999")
    public List<Examinee> getExamineeAll();

    /**
     * 根据考生状态，获取所有考生信息
     *
     * @param actstatus //actstatus 1: 正常 0: 锁定 -1：删除
     * @return
     */
    @Select("select * from examinee where actstatus=#{actstatus} and actstatus<> 999999")
    public List<Examinee> getExamineeAllByActStatus(@Param("actstatus")  String actstatus);

    /**
     * 录入考生信息
     *
     * @param examinee
     */
    @Insert("INSERT INTO examinee (userid, username, ticketnumber, idcard, password, actstatus, phone, email, address, sex) VALUES (#{examinee.userid}, #{examinee.username}, #{examinee.ticketnumber}, #{examinee.idcard},#{ examinee.password},#{ examinee.actstatus}, #{examinee.phone}, #{examinee.email}, #{examinee.address}, #{examinee.sex})")
    public void insertExaminee(@Param("examinee") Examinee examinee);

    /**
     * 更新考生信息
     *
     * @param examinee
     */
    @Update("UPDATE examinee SET  username=#{examinee.username}, ticketnumber=#{examinee.ticketnumber}, idcard=#{examinee.idcard}, password=#{examinee.password}, actstatus=#{examinee.actstatus}, phone=#{examinee.phone}, email=#{examinee.email}, address=#{examinee.address}, sex=#{examinee.sex} where userid=#{examinee.userid}")
    public void updateExaminee(@Param("examinee") Examinee examinee);

    /**个人中心--考生自己更新信息
     * @param examinee
     */
    @Update("UPDATE examinee SET  username=#{examinee.username}, password=#{examinee.password},  phone=#{examinee.phone}, email=#{examinee.email}, address=#{examinee.address}, sex=#{examinee.sex} where userid=#{examinee.userid}")
    public void updateExamineeByExaminee(@Param("examinee") Examinee examinee);
    /**
     * 更新考生密码
     *
     * @param examinee
     */
    @Update("UPDATE examinee SET  password=#{examinee.password} where userid=#{examinee.userid}")
    public void updateExamineePassword(@Param("examinee") Examinee examinee);
    /**
     * 真删考生信息
     *
     * @param userid
     */
    @Delete("delete from examinee where userid=#{userid}")
    public void deleteExamineeByUseridT(@Param("userid") String userid);

    /**
     * 假删考生信息（只是修改状态）-----假删改为锁定，取消删除状态
     *
     * @param userid
     */
    @Update("UPDATE examinee SET  actstatus='0' where userid=#{userid}")
    public void deleteExamineeByUseridF(@Param("userid") String userid);

    /**更新考生状态
     * @param useridA
     * @param actstatus
     */
    @Update("UPDATE examinee SET  actstatus=#{actstatus} where userid=#{userid}")
    public void updateExamineectstatusByUserid(@Param("userid") String useridA,@Param("actstatus") String actstatus);
}
