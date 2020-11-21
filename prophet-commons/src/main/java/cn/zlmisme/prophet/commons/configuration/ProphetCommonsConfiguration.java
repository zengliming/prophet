package cn.zlmisme.prophet.commons.configuration;

import cn.zlmisme.prophet.commons.configuration.mybatisplus.MybatisPlusConfiguration;
import cn.zlmisme.prophet.commons.configuration.sentinel.SentinelAspectConfiguration;
import cn.zlmisme.prophet.commons.util.SpringUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author liming zeng
 * @create 2020-10-19 14:18
 */
@EnableConfigurationProperties
@Import({MybatisPlusConfiguration.class, SentinelAspectConfiguration.class})
public class ProphetCommonsConfiguration {

    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

}
