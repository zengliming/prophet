package cn.zlmisme.prophet.gateway.runner;

import cn.zlmisme.prophet.commons.util.JsonUtil;
import cn.zlmisme.prophet.gateway.properties.DynamicRouteConfigProperties;
import cn.zlmisme.prophet.gateway.service.DynamicRouterService;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * @author liming zeng
 * @create 2020-10-19 18:24
 */
@Component
public class DynamicRouterRunner implements CommandLineRunner {


    @Resource
    private DynamicRouterService dynamicRouterService;

    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @Resource
    private DynamicRouteConfigProperties dynamicRouteConfigProperties;

    @Override
    public void run(String... args) throws Exception {
        String dataId = dynamicRouteConfigProperties.getDataId();
        String groupId = dynamicRouteConfigProperties.getGroupId();
        try {
            final ConfigService configService = NacosFactory.createConfigService(nacosConfigProperties.getServerAddr());
            String content = configService.getConfig(dataId, groupId, 5000L);
            final List<RouteDefinition> definitions = JsonUtil.toList(content, RouteDefinition.class);
            definitions.forEach(definition -> {
                dynamicRouterService.update(definition);
            });
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    try {
                        Optional.ofNullable(JsonUtil.toList(configInfo, RouteDefinition.class)).ifPresent(routes ->
                                routes.forEach(definition -> {
                                    System.out.println("definition = " + definition);
                                    dynamicRouterService.update(definition);
                                })
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
