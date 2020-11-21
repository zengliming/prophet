package cn.zlmisme.prophet.sentinel.common.rule;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import lombok.Data;

/**
 * @author liming zeng
 * @create 2020/11/21 11:57 下午
 */
@Data
public class BaseRule<T extends AbstractRule> {

    private String app;

    private Long gmtCreate;

    private Long gmtModified;

    private Long id;

    private String ip;

    private Integer port;

    private T rule;
}
