package cn.zlmisme.prophet.sentinel.common.rule;

import cn.zlmisme.prophet.sentinel.common.properties.RuleConfig;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author liming zeng
 * @create 2020/11/21 10:24 下午
 */
@Slf4j
public class SystemRuleDataSourceRegistry implements CommandLineRunner {

    @Resource
    private RuleConfig ruleConfig;

    @Value("${spring.cloud.nacos.config.server-addr:'null'}")
    private String serverAddr;

    @Override
    public void run(String... args) throws Exception {
        if (!StringUtils.isEmpty(ruleConfig.getSystemDataId()) || StringUtils.isEmpty(serverAddr)) {
            ReadableDataSource<String, List<SystemRule>> degradeRuleDatasource = new NacosDataSource<>(serverAddr,
                    ruleConfig.getGroupId(),ruleConfig.getSystemDataId(),
                    source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {}));
            SystemRuleManager.register2Property(degradeRuleDatasource.getProperty());
            List<SystemRule> systemRules = JSON.parseArray(degradeRuleDatasource.readSource(), SystemRule.class);
            log.debug("systemRules: {}", systemRules);
            if (Objects.nonNull(systemRules)) {
                SystemRuleManager.loadRules(systemRules);
            }
        }
    }
}
