package cn.zlmisme.prophet.sentinel.common.rule;

import cn.zlmisme.prophet.sentinel.common.properties.RuleConfig;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
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
public class FluxRuleDataSourceRegistry implements CommandLineRunner {

    @Resource
    private RuleConfig ruleConfig;

    @Value("${spring.cloud.nacos.config.server-addr:'null'}")
    private String serverAddr;

    @Override
    public void run(String... args) throws Exception {
        if (!StringUtils.isEmpty(ruleConfig.getFlowDataId())|| StringUtils.isEmpty(serverAddr)) {
            ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(serverAddr,
                    ruleConfig.getGroupId(),ruleConfig.getFlowDataId(),
                    source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
            FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
            List<FlowRuleDTO> flowRuleDTOS = JSON.parseArray(flowRuleDataSource.readSource(), FlowRuleDTO.class);
            log.debug("flowRules: {}", JSON.toJSONString(flowRuleDTOS));
            if (Objects.nonNull(flowRuleDTOS)) {
                FlowRuleManager.loadRules(flowRuleDTOS.stream().map(FlowRuleDTO::getRule).collect(Collectors.toList()));
            }
        }
    }

    private static class FlowRuleDTO extends BaseRule<FlowRule> {}
}
