package cn.zlmisme.prophet.sentinel.common.properties;

import cn.zlmisme.prophet.commons.constants.GlobalConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

import java.util.Objects;

/**
 * @author liming zeng
 * @create 2020/11/21 10:39 下午
 */
@ConfigurationProperties(prefix = "spring.cloud.sentinel.rule")
public class RuleConfig {

    @Autowired
    @JsonIgnore
    private Environment environment;

    private String flowDataId;

    private String authorityDataId;

    private String gatewayDataId;

    private String degradeDataId;

    private String paramFlowDataId;

    private String systemDataId;

    private String groupId = "SENTINEL_GROUP";

    public String getFlowDataId() {
        if (Objects.isNull(flowDataId)) {
            this.flowDataId = environment.getProperty("spring.application.name") + GlobalConstants.FLOW_DATA_ID_POSTFIX;
        }
        return flowDataId;
    }

    public void setFlowDataId(String flowDataId) {
        this.flowDataId = flowDataId;
    }

    public String getAuthorityDataId() {
        if (Objects.isNull(authorityDataId)) {
            this.authorityDataId = environment.getProperty("spring.application.name") + GlobalConstants.AUTHORITY_DATA_ID_POSTFIX;
        }
        return authorityDataId;
    }

    public void setAuthorityDataId(String authorityDataId) {
        this.authorityDataId = authorityDataId;
    }

    public String getGatewayDataId() {
        if (Objects.isNull(gatewayDataId)) {
            this.gatewayDataId = environment.getProperty("spring.application.name") + GlobalConstants.GATEWAY_DATA_ID_POSTFIX;
        }
        return gatewayDataId;
    }

    public void setGatewayDataId(String gatewayDataId) {
        this.gatewayDataId = gatewayDataId;
    }

    public String getDegradeDataId() {
        if (Objects.isNull(degradeDataId)) {
            this.degradeDataId = environment.getProperty("spring.application.name") + GlobalConstants.DEGRADE_DATA_ID_POSTFIX;
        }
        return degradeDataId;
    }

    public void setDegradeDataId(String degradeDataId) {
        this.degradeDataId = degradeDataId;
    }

    public String getParamFlowDataId() {
        if (Objects.isNull(paramFlowDataId)) {
            this.paramFlowDataId = environment.getProperty("spring.application.name") + GlobalConstants.PARAM_FLOW_DATA_ID_POSTFIX;
        }
        return paramFlowDataId;
    }

    public void setParamFlowDataId(String paramFlowDataId) {
        this.paramFlowDataId = paramFlowDataId;
    }

    public String getSystemDataId() {
        if (Objects.isNull(systemDataId)) {
            this.systemDataId = environment.getProperty("spring.application.name") + GlobalConstants.SYSTEM_FLOW_DATA_ID_POSTFIX;
        }
        return systemDataId;
    }

    public void setSystemDataId(String systemDataId) {
        this.systemDataId = systemDataId;
    }

    public String getGroupId() {
        if (Objects.isNull(groupId)) {
            this.groupId = environment.getProperty("spring.application.name") + GlobalConstants.GROUP_ID;
        }
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "RuleConfig{" +
                "flowDataId='" + flowDataId + '\'' +
                ", authorityDataId='" + authorityDataId + '\'' +
                ", gatewayDataId='" + gatewayDataId + '\'' +
                ", degradeDataId='" + degradeDataId + '\'' +
                ", paramFlowDataId='" + paramFlowDataId + '\'' +
                ", systemDataId='" + systemDataId + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
