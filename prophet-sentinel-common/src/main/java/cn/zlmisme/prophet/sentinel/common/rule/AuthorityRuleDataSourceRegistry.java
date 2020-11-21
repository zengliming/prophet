package cn.zlmisme.prophet.sentinel.common.rule;

import cn.zlmisme.prophet.sentinel.common.properties.RuleConfig;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
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
public class AuthorityRuleDataSourceRegistry implements CommandLineRunner {

    @Resource
    private RuleConfig ruleConfig;

    @Value("${spring.cloud.nacos.config.server-addr:'null'}")
    private String serverAddr;

    @Override
    public void run(String... args) throws Exception {
        if (!StringUtils.isEmpty(ruleConfig.getAuthorityDataId()) || StringUtils.isEmpty(serverAddr)) {
            ReadableDataSource<String, List<AuthorityRule>> authorityRuleDatasource = new NacosDataSource<>(serverAddr,
                    ruleConfig.getGroupId(),ruleConfig.getAuthorityDataId(),
                    source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {}));
            AuthorityRuleManager.register2Property(authorityRuleDatasource.getProperty());
            List<AuthorityRuleDTO> authorityRules = JSON.parseArray(authorityRuleDatasource.readSource(), AuthorityRuleDTO.class);
            log.debug("authorityRules: {}", authorityRules);
            if (Objects.nonNull(authorityRules)) {
                AuthorityRuleManager.loadRules(authorityRules.stream().map(AuthorityRuleDTO::getRule).collect(Collectors.toList()));
            }
        }
    }

    private static class AuthorityRuleDTO extends BaseRule<AuthorityRule> {

    }
}
