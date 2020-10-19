package cn.zlmisme.prophet.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liming zeng
 * @create 2020-10-19 20:23
 */
@Component
@ConfigurationProperties(prefix = "nacos.config.gateway.route")
public class DynamicRouteConfigProperties {

    private String dataId;

    private String groupId;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
