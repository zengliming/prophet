package cn.zlmisme.prophet.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liming zeng
 * @create 2021-02-24 16:39
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class ProphetMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProphetMonitorApplication.class, args);
    }
}
