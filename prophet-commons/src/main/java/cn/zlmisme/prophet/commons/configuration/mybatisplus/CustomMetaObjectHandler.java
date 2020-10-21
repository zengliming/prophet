package cn.zlmisme.prophet.commons.configuration.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author liming zeng
 * @create 2020-10-20 15:04
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {

    private final Set<String> updateTimeKeySet;
    private final Set<String> createTimeKeySet;

    public CustomMetaObjectHandler() {
        updateTimeKeySet = new HashSet<>();
        createTimeKeySet = new HashSet<>();
        init();
    }

    private void init() {
        updateTimeKeySet.add("updateTime");
        createTimeKeySet.add("createTime");
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Stream.concat(createTimeKeySet.stream(), updateTimeKeySet.stream()).filter(Objects::isNull).forEach(key -> {
            this.strictInsertFill(metaObject, key, LocalDateTime.class, LocalDateTime.now());
            this.strictInsertFill(metaObject, key, Date.class, new Date());
        });
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        updateTimeKeySet.forEach(key ->
                Optional.ofNullable(key).ifPresent(k -> {
                    this.strictUpdateFill(metaObject, k, LocalDateTime.class, LocalDateTime.now());
                    this.strictUpdateFill(metaObject, k, Date.class, new Date());
                })
        );
    }
}
