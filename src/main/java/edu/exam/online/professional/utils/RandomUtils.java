package edu.exam.online.professional.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机重组list
 * Created by licy13 on 2016/4/19.
 */
public class RandomUtils {
    /**
     * 随机获取List
     * @param sourceList List数据源
     * @param count 获取条数
     * @return
     */
    public static List randomList(List sourceList, int count) {
        if (sourceList == null || sourceList.size() == 0) {
            return sourceList;
        }
        //获取条数超过源列表大小
        if (count > sourceList.size()) {
            count = sourceList.size();
        }
        List randomList = new ArrayList(count);
        while (sourceList.size() > 0 && randomList.size() < count) {
            int randomIndex = Math.abs(new Random().nextInt(sourceList.size()));
            Object remove = sourceList.remove(randomIndex);
            randomList.add(remove);
        }
        return randomList;
    }
}
