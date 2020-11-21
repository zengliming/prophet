package cn.zlmisme.prophet.authority;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liming zeng
 * @create 2020-10-19 14:09
 */
@MapperScans({
        @MapperScan(basePackages = "cn.zlmisme.prophet.authority.dao.mapper")
})
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
public class ProphetAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProphetAuthorityApplication.class, args);
    }
}
