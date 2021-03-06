package cn.zlmisme.prophet.sentinel.common.rule;

import cn.zlmisme.prophet.sentinel.common.properties.RuleConfig;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liming zeng
 * @create 2020/11/21 10:24 下午
 */
@Slf4j
public class ParamFlowRuleDataSourceRegistry implements CommandLineRunner {

    @Resource
    private RuleConfig ruleConfig;

    @Value("${spring.cloud.nacos.config.server-addr:'null'}")
    private String serverAddr;

    @Override
    public void run(String... args) throws Exception {
        if (!StringUtils.isEmpty(ruleConfig.getParamFlowDataId()) || StringUtils.isEmpty(serverAddr)) {
            ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new NacosDataSource<>(serverAddr,
                    ruleConfig.getGroupId(), ruleConfig.getAuthorityDataId(),
                    source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                    }));
            ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());
            List<ParamFlowRuleDTO> paramFlowResult = JSON.parseArray(paramFlowRuleDataSource.readSource(), ParamFlowRuleDTO.class);
            log.debug("paramFlowResult: {}", paramFlowResult);
            if (Objects.nonNull(paramFlowResult)) {
                ParamFlowRuleManager.loadRules(paramFlowResult.stream().map(ParamFlowRuleDTO::getRule).collect(Collectors.toList()));
            }
        }
    }

    private static class ParamFlowRuleDTO extends BaseRule<ParamFlowRule> {

    }
}
