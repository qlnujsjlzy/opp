package edu.exam.online.professional.utils;

import java.util.*;

/**
 * Created by licy13 on 2016/4/19.
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        List list = new ArrayList<Object>();
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");

        //  List<Object> ll=   test.randomTopic(list, 2);
       // Collections.shuffle(list);
       List arrayList = RandomUtils.randomList(list,6);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }

    public static List randomList(List sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }
        ArrayList randomList = new ArrayList(3);
        System.out.println(randomList.size());
        while (sourceList.size() > 0&&randomList.size()<3){
            System.out.println(randomList.size());
            int randomIndex = Math.abs(new Random().nextInt(sourceList.size()));
            System.out.println("randomIndex:"+randomIndex);
            Object remove = sourceList.remove(randomIndex);
            System.out.println("remove:"+remove);
            randomList.add(remove);
        }
        return randomList;
    }

    public static boolean isEmpty(List sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }


    // 从List中随机出count个对象
    private List<Object> randomTopic(List<Object> list, int count) {
        // 创建一个长度为count(count<=list)的数组,用于存随机数
        int[] a = new int[count];
        // 利于此数组产生随机数
        int[] b = new int[list.size()];
        int size = list.size();
        // 取样填充至数组a满
        for (int i = 0; i < count; i++) {
            int num = (int) (Math.random() * size); // [0,size)
            int where = -1;
            for (int j = 0; j < b.length; j++) {
                if (b[j] != -1) {
                    where++;
                    if (where == num) {
                        b[j] = -1;
                        a[i] = j;
                    }
                }
            }
            size = size - 1;
        }
        // a填满后 将数据加载到rslist
        List<Object> rslist = new ArrayList<Object>();
        for (int i = 0; i < count; i++) {
            Object df = (Object) list.get(a[i]);
            rslist.add(df);
            System.out.println(df);
        }
        return rslist;
    }
}
