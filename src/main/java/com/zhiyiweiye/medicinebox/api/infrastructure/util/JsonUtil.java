package com.zhiyiweiye.medicinebox.api.infrastructure.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @since 17-5-15.
 */
public class JsonUtil {
    private JsonUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final ObjectMapper mapper =
            new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static String beanToString(Object obj) {

        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 转化json string 到对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String json, Class<T> clz) {
        try {
            if (clz.isInstance(Collection.class)) {
                return mapper.readValue(json, new TypeReference<Collection<T>>() {
                });
            }
            if (clz.isInstance(Map.class)) {
                return mapper.readValue(json, new TypeReference<Map>() {
                });
            }
            return mapper.readValue(json, clz);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T stringToBean(String json, TypeReference<T> valueTypeRef) {
        ObjectMapper mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * javabean 转成 map
     *
     * @param t
     * @return
     */
    public static Map<String, String> beanToMap(final Object t) {
        Map<String, Object> objectAsMap = mapper.convertValue(t, Map.class);

        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : objectAsMap.entrySet()) {
            String key = entry.getKey();
            map.put(key, objectAsMap.get(key) != null ? objectAsMap.get(key).toString() : "");
        }

        return map;
    }

    /**
     * map 转成 javabean
     * writeValueAsString
     *
     * @param t
     * @return
     */
    public static Object mapToBean(Map t, Object c) {

        return mapper.convertValue(t, c.getClass());
    }
}
