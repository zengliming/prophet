package cn.zlmisme.prophet.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liming zeng
 * @create 2020-11-05 19:39
 */
@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "gateway")
public class BlacklistProperties {

    private List<Blacklist> blacklists;

    @Data
    public static class Blacklist {

        private String uri;

        private List<String> ips;
    }
}
