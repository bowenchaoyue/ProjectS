package utils;

import com.google.common.collect.Lists;
import com.ibatis.common.beans.Probe;
import com.ibatis.common.beans.ProbeFactory;
import jodd.datetime.JDateTime;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chaowen on 2017/8/13.
 */
public class CommonUtil {

    public static final Collection NULL_COLLECTION = new NullCollection();

    private static final Probe PROBE = ProbeFactory.getProbe();

    /**
     * @author chaowen
     *
     *         <pre>
     * 批量获取map中的值
     * </pre>
     *
     * @param <K>
     * @param <V>
     * @param keys
     * @param source
     * @return
     */
    public static <K, V> List<V> getAllFormMap(List<K> keys, Map<K, V> source) {
        List<V> values = Collections.emptyList();
        if (keys != null && !keys.isEmpty() && source != null
                && !source.isEmpty()) {
            values = new ArrayList<V>();
            for (K k : keys) {
                values.add(source.get(k));
            }
        }
        return values;
    }


    /**
     * @author chaowen
     *
     *         <pre>
     * 从List中获取valueProp组成一个新的list
     * </pre>
     *
     * @param <E>
     * @param list
     * @param valueProp
     * @return
     */
    public static <E, K> List<K> getValueList(List<E> list, String valueProp) {
        List<K> valueList = Collections.emptyList();
        if (CollectionUtils.isNotEmpty(list)) {
            list.removeAll(nullCollection());
            valueList = new ArrayList<K>(list.size());
            for (E e : list) {
                @SuppressWarnings("unchecked")
                K value = (K) PROBE.getObject(e, valueProp);
                valueList.add(value);
            }
        }
        return valueList;
    }


    /**
     * @author chaowen
     *
     *         <pre>
     * 用list生成一个map,keyProp 为指定的key,valueProp 为指定是value
     * </pre>
     *
     * @param <K>
     * @param <V>
     * @param <E>
     * @param list
     * @param keyProp
     * @param valueProp
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <K, V, E> Map<K, V> listforMap(List<E> list, String keyProp,
                                                 String valueProp) {
        Map<K, V> rs = new HashMap<K,V>();

        if (CollectionUtils.isNotEmpty(list)) {

            ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();

            list.removeAll(nullCollection());
            for (E object : list) {
                K key = (K) PROBE.getObject(object, keyProp);
                Object value = null;
                if (StringUtils.isEmpty(valueProp)) {
                    value = object;
                } else {
                    value = PROBE.getObject(object, valueProp);
                }
                map.put(key, (V) value);
            }
            rs = map;
        }
        return rs;
    }


    /**
     * @author chaowen
     *
     *         <pre>
     * list 生成一个Map<K,List<V>>
     * </pre>
     *
     * @param <K>
     * @param <V>
     * @param <E>
     * @param list
     * @param keyProp
     * @param valueProp
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <K, V, E> Map<K, List<V>> listforListMap(List<E> list,
                                                           String keyProp, String valueProp) {
        Map<K, List<V>> map = Collections.emptyMap();
        if (CollectionUtils.isNotEmpty(list)) {
            list.removeAll(nullCollection());
            map = new HashMap<K, List<V>>(list.size());
            V value = null;
            for (E object : list) {
                K key = (K) PROBE.getObject(object, keyProp);
                if (StringUtils.isEmpty(valueProp)) {
                    value = (V) object;
                } else {
                    value = (V) PROBE.getObject(object, valueProp);
                }
                List<V> values = map.get(key);
                if (values == null) {
                    values = new ArrayList<V>();
                }
                values.add(value);
                map.put(key, values);
            }
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static final <T> Collection<T> nullCollection() {
        return (List<T>) NULL_COLLECTION;
    }

    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static List<String> stringToList(String in){
        String[] ids = in.split(",");
        return Lists.newArrayList(ids);
    }

    public static void main(String[] args) {
        JDateTime jDateTime = new JDateTime(new Date());
        System.out.println(jDateTime.toString());
        jDateTime.setDay(1);
        jDateTime.setHour(0);
        jDateTime.setMinute(0);
        jDateTime.setSecond(0);
        System.out.println(jDateTime.toString());
        jDateTime.addMonth(1);
        System.out.println(jDateTime.toString());
    }



}
