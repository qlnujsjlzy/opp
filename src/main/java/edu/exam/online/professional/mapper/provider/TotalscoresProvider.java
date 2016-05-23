package edu.exam.online.professional.mapper.provider;

import edu.exam.online.professional.domain.Totalscores;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by licy13 on 2016/4/12.
 */
public class TotalscoresProvider {
    public static Logger log = LogManager.getLogger(TotalscoresProvider.class.getName());

    /**
     * 批量添加或更新分组
     *
     * @param map
     * @return
     */
    public String batchAddOrUpdateTotalscore(Map map) {
        log.info("批量添加或更新分组-----进入");
        log.info("获取传入的参数--map-{}",map);
        List<Totalscores> list = (List<Totalscores>) map.get("list");
        log.info("map转换成List<Totalscores>-{}",list);
        StringBuilder sb = new StringBuilder("insert into totalscores ")
                .append(" ( `id`, `examid`, `userid`, `score`, `createtime`,status) ")
                .append(" values ");
        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].examid},#'{'list[{0}].userid},#'{'list[{0}].score},#'{'list[{0}].createtime},'0')");
        for (int i = 0; i < list.size(); i++) {
            sb.append(messageFormat.format(new Object[]{i}));
            if (i < list.size() - 1)
                sb.append(",");
        }
        /*使用 ON DUPLICATE KEY UPDATE 有则更新指定列，无则添加*/
        sb.append(" ON DUPLICATE KEY UPDATE `score`=VALUES(score), `createtime`="+new Date().getTime());
        log.info("批量添加或更新分组-----结束-返回值{}",sb.toString());
        return sb.toString();
    }

    /**
     * 批量删除
     * @param map
     * @return
     */
    public String batchdeleteTotalscore(Map map) {
        log.info("获取传入的参数--map-{}",map);
        List<String> ids = (List<String>) map.get("list");
        StringBuilder sb = new StringBuilder("Delete from totalscores where id in （ ");
        for (int i = 0; i < ids.size(); i++) {
            sb.append(ids.get(i));
            if (i < ids.size() - 1)
                sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }
    /**
     * 根据分组id或是用户id，生成sql
     *
     * @param groupid
     * @param userid
     * @return
     */
    public String getTotalscore(Map map) {
        final String examid=(String)map.get("examid");
        final String userid=(String)map.get("userid");
        log.info("根据分组id或是用户id，生成sql-----进入");
        log.info("获取传入的参数--examid[{}]---userid[{}]",examid,userid);
        return new SQL() {
            {
                SELECT("ts.`id`, ts.`examid`,e.examname, ts.`userid`,ee.username,ee.idcard, ts.`score`, ts.`createtime`,e.begintime,e.endtime,ts.status ");
                FROM("`totalscores` ts ");
                INNER_JOIN("exam e ON e.id=ts.examid");
                INNER_JOIN("examinee ee ON ee.userid=ts.userid");
                if (StringUtils.isNotBlank(examid)) {
                    WHERE("ts.examid= #{examid}");
                }
                if (StringUtils.isNotBlank(userid)) {
                    WHERE("ts.userid = #{userid}");
                }
            }
        }.toString();
    }
}
