package cn.zlmisme.prophet.sentinel.common.config;

import cn.zlmisme.prophet.commons.constants.GlobalConstants;
import cn.zlmisme.prophet.commons.util.JsonUtil;
import cn.zlmisme.prophet.commons.vo.BaseResponse;
import cn.zlmisme.prophet.sentinel.common.properties.RuleConfig;
import cn.zlmisme.prophet.sentinel.common.rule.*;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author liming zeng
 * @create 2020/11/20 11:52 下午
 */
@Slf4j
@Configuration
public class SentinelCommonConfiguration {

    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return (request, response, e) -> {
            PrintWriter writer = response.getWriter();
            response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            response.setContentType("application/json");
            writer.println(JsonUtil.toStr(BaseResponse.flowLimit()));
            writer.flush();
            writer.close();
        };
    }

    /**
     * 注意引入的路径需要是webmvc下的
     * @see com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser
     * @return
     */
    @Bean
    public RequestOriginParser requestOriginParser() {
        return request -> {
            String header = request.getHeader(GlobalConstants.ORIGIN_SOURCE_KEY);
            return StringUtils.isEmpty(header) ? GlobalConstants.DEFAULT_ORIGIN_SOURCE_VALUE : header;
        };
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @Bean
    public AuthorityRuleDataSourceRegistry authorityRuleDataSourceRegistry() {
        return new AuthorityRuleDataSourceRegistry();
    }

    @Bean
    public FluxRuleDataSourceRegistry fluxRuleDataSourceRegistry() {
        return new FluxRuleDataSourceRegistry();
    }


    @Bean
    public DegradeRuleDataSourceRegistry degradeRuleDataSourceRegistry() {
        return new DegradeRuleDataSourceRegistry();
    }

    @Bean
    public ParamFlowRuleDataSourceRegistry paramFlowRuleDataSourceRegistry() {
        return new ParamFlowRuleDataSourceRegistry();
    }

    @Bean
    public SystemRuleDataSourceRegistry systemRuleDataSourceRegistry() {
        return new SystemRuleDataSourceRegistry();
    }

    @Bean
    public RuleConfig ruleConfig() {
        return new RuleConfig();
    }
}

