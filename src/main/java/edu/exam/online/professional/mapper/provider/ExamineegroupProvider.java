package edu.exam.online.professional.mapper.provider;

import edu.exam.online.professional.domain.Examineegroup;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by licy13 on 2016/4/8.
 */
public class ExamineegroupProvider {
    public static Logger log = LogManager.getLogger(ExamineegroupProvider.class.getName());

    /**
     * 批量添加或更新分组
     * @param map
     * @return
     */
    public String batchAddOrUpdateExamineegroup(Map map) {
        log.info("批量添加或更新分组-----进入");
        log.info("获取传入的参数--map-{}",map);
        List<Examineegroup> list = (List<Examineegroup>) map.get("list");
        log.info("map转换成List<Examineegroup>-{}",list);
        /*使用ignore 无则添加，有则忽略*/
        StringBuilder sb = new StringBuilder("insert ignore into examineegroup ")
                .append(" ( `groupid`, `userid`) ")
                .append(" values ");
        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].groupid},#'{'list[{0}].userid})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(messageFormat.format(new Object[]{i}));
            if (i < list.size() - 1)
                sb.append(",");
        }
        /*使用 ON DUPLICATE KEY UPDATE 有则更新指定列，无则添加*/
        //sb.append(" ON DUPLICATE KEY UPDATE `updateTime`="+new Date().getTime());
        log.info("批量添加或更新分组-----结束-返回值{}",sb.toString());
        return sb.toString();
    }

    /**
     * 根据分组id或是用户id，生成sql
     *
     * @param groupid
     * @param userid
     * @return
     */
    public String getExamineegroup(Map map) {
        final String groupid=(String)map.get("groupid");
        final String userid=(String)map.get("userid");
        log.info("根据分组id或是用户id，生成sql-----进入");
        log.info("获取传入的参数--groupid[{}]---userid[{}]",groupid,userid);
        return new SQL() {
            {
                SELECT("`groupid`, `groupname`, `createtime`,e.`userid` , `username`, `ticketnumber`, `idcard`, `password`, `actstatus`, `phone`, `email`, `address`, `sex` ");
                FROM("`examineegroup` eg ");
                INNER_JOIN("examinee e ON e.userid=eg.userid");
                INNER_JOIN("groupinfo gi ON gi.id = eg.groupid");
                if (StringUtils.isNotBlank(groupid)) {
                    WHERE("groupid = #{groupid}");
                }
                if (StringUtils.isNotBlank(userid)) {
                    WHERE("userid = #{userid}");
                }
            }
        }.toString();
    }
}
