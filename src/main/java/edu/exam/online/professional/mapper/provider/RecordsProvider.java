package edu.exam.online.professional.mapper.provider;

import edu.exam.online.professional.domain.Records;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by licy13 on 2016/4/11.
 */
public class RecordsProvider {
    public static Logger log = LogManager.getLogger(RecordsProvider.class.getName());

    /**
     * 批量添加或更新分组
     *
     * @param map
     * @return
     */
    public String batchAddOrUpdateRecords(Map map) {
        log.info("批量添加或更新分组-----进入");
        log.info("获取传入的参数--map-{}",map);
        List<Records> list = (List<Records>) map.get("list");
        log.info("map转换成List<Records>-{}",list);
        StringBuilder sb = new StringBuilder("insert  into records ")
                .append(" ( `id`, `examid`, `weight`, `userid`, `type`, `sort`, `testid`, `title`, `options`, `answer`, `myanswer`, `score`) ")
                .append(" values ");
        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].examid},#'{'list[{0}].weight},#'{'list[{0}].userid},#'{'list[{0}].type},#'{'list[{0}].sort},#'{'list[{0}].testid},#'{'list[{0}].title},#'{'list[{0}].options},#'{'list[{0}].answer},#'{'list[{0}].myanswer},#'{'list[{0}].score})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(messageFormat.format(new Object[]{i}));
            if (i < list.size() - 1)
                sb.append(",");
        }
        /*使用 ON DUPLICATE KEY UPDATE 有则更新指定列，无则添加*/
        sb.append(" ON DUPLICATE KEY UPDATE `myanswer`=VALUES(myanswer),`score`= (case when VALUES(myanswer)=VALUES(answer) then '1' else '0' end) ");
        log.info("批量添加或更新分组-----结束-返回值{}",sb.toString());
        return sb.toString();
    }
}
