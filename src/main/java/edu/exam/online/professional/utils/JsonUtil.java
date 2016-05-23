package edu.exam.online.professional.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * json 与对象之间的转换
 * Created by guodandan on 2016/3/21.
 */
public class JsonUtil {
    public static Logger log =  LogManager.getLogger(JsonUtil.class.getName());

    public static final ObjectMapper OM = new ObjectMapper();
    static{
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        OM.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        OM.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        OM.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static JavaType assignList(Class<? extends Collection> collection, Class<? extends Object> object) {
        return JsonUtil.OM.getTypeFactory().constructParametricType(collection, object);
    }


    public static <T> ArrayList<T> readValuesAsArrayList(String key, Class<T> object) {
        ArrayList<T> list = null;
        try {
            list = OM.readValue(key, assignList(ArrayList.class, object));
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }


    /**
     * 对象转换成json String
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        if(obj == null){
            return "";
        }
        try {
            return OM.writeValueAsString(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * json String 转换成对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz){
        try {
            return OM.readValue(json, clazz);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 重构 fromJson，设置返回时间类型
     * @param sdf
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(SimpleDateFormat sdf,String json, Class<T> clazz){
        try {
            OM.setDateFormat(sdf);
            T t =  OM.readValue(json, clazz);
            OM.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            return t;
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }


}
