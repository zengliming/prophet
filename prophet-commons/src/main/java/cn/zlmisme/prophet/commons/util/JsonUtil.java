package cn.zlmisme.prophet.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author liming zeng
 * @create 2020-10-19 18:48
 */
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtil() { throw new IllegalStateException("Utility class");}

    public static String toStr(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) throws IOException {
        if(Objects.isNull(json) || json.isEmpty()) {
            return null;
        }
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("type can not be null!");
        }
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> List<T> toList(String json, Class<T> clazz) throws IOException {
        if(Objects.isNull(json) || json.isEmpty()) {
            return Collections.emptyList();
        }
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("type can not be null!");
        }
        final JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
        return OBJECT_MAPPER.readValue(json, javaType);
    }

}
