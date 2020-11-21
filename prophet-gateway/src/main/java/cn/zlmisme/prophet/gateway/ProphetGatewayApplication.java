package cn.zlmisme.prophet.gateway;

import com.alibaba.cloud.sentinel.SentinelWebFluxAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liming zeng
 * @create 2020-10-19 14:34
 */
@SpringBootApplication(exclude = SentinelWebFluxAutoConfiguration.class)
public class ProphetGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProphetGatewayApplication.class, args);
    }
}
