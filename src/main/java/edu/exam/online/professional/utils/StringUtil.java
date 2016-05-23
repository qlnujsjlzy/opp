package edu.exam.online.professional.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guodandan on 2016/3/23.
 */
public class StringUtil {
    public static Logger log =  LogManager.getLogger(StringUtil.class.getName());

    public static boolean isEmpty(String key) {
        if (key != null && !"".equals(key.trim())) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String... keys) {
        for (String key : keys) {
            if (StringUtil.isEmpty(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllEmpty(List<String> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        for (String key : list) {
            if (!StringUtil.isEmpty(key)) {
                return false;
            }
        }
        return true;
    }


    public static String decimalFormatPrice(String param) {
        if (param == null || "".equals(param)) {
            param = "0.00";
        }
        double tmp = Double.parseDouble(param);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(tmp);
    }

    public static String cleanXss(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        str = str.replaceAll(" ", "");
        return str;
    }

    /**
     * @param str
     * @param trim
     * @return
     * @description 以*(分号)分隔的字符串，去除首尾的*(分号)。
     * @createTime 2015上午11:05:00
     */
    public static String StrRemoveTrim(String str, String trim) {
        // str=;2;;
        String resultStr = "";
        String[] strList = str.split(trim);
        for (String s : strList) {
            if (!"".equals(s) && null != s) {
                resultStr = resultStr + s + trim;
            }
        }
        if (resultStr.length() > 0) {
            resultStr = resultStr.substring(0, resultStr.length() - 1);
        }
        return resultStr;
    }


    /**
     * 是否手机号 简单校验11位数字
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        Matcher m = NUM_11.matcher(str);
        return m.matches();
    }

    static Pattern NUM_11 = Pattern.compile("\\d{11}");


    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * @param temp
     * @return String
     * @throws
     * @Title: 空字符串转0
     * @Description: null或空字符串转0
     */
    public static String conVertNull2Zero(String temp) {
        if (null == temp || StringUtil.isEmpty(temp.trim())) {
            return "0";
        }
        return temp;
    }
}
