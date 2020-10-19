package cn.zlmisme.prophet.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liming zeng
 * @create 2020-10-19 14:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProphetAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProphetAuthorityApplication.class, args);
    }
}
