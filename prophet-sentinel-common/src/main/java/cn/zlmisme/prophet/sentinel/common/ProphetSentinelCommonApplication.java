package cn.zlmisme.prophet.sentinel.common;

import cn.zlmisme.prophet.sentinel.common.properties.RuleConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author liming zeng
 * @create 2020/11/21 10:22 下午
 */
@EnableConfigurationProperties(RuleConfig.class)
@SpringBootApplication
public class ProphetSentinelCommonApplication {

    public static void main(String[] args) {

    }
}
