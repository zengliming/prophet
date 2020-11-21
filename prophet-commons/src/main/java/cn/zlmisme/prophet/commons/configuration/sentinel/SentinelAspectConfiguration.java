package cn.zlmisme.prophet.commons.configuration.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liming zeng
 * @create 2020/11/20 11:52 下午
 */
@Configuration
public class SentinelAspectConfiguration {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @Bean
    public RequestOriginParser requestOriginParser() {
        RequestOriginParserDefinition requestOriginParserDefinition = new RequestOriginParserDefinition();
        WebCallbackManager.setRequestOriginParser(requestOriginParserDefinition);
        return requestOriginParserDefinition;
    }
}

