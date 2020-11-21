package cn.zlmisme.prophet.sentinel.common.config;

import java.util.HashMap;

/**
 * @author liming zeng
 * @create 2020/11/21 6:15 下午
 */
public class DefaultFallbackClass {

    public static Object defaultFallback(Throwable throwable) {
        throwable.printStackTrace();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }
}
