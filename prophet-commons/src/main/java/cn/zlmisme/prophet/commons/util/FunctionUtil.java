package cn.zlmisme.prophet.commons.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @author liming zeng
 * @create 2020-10-19 14:39
 */
public class FunctionUtil {

    private FunctionUtil() throws IllegalAccessException {
        throw new IllegalAccessException("工具类，不能初始化");
    }

    public static <T> void exec(Consumer<T> consumer, T t) {
        if (Objects.isNull(consumer)) {
            return;
        }
        consumer.accept(t);
    }

    public static <T> T exec(UnaryOperator<T> unaryOperator, T t) {
        if (Objects.isNull(unaryOperator)) {
            return t;
        }
        return unaryOperator.apply(t);
    }

    public static <T> T exec(Supplier<T> supplier) {
        if (Objects.isNull(supplier)) {
            return null;
        }
        return supplier.get();
    }
}
