package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author liming zeng
 * @create 2020-11-23 9:34
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ProphetZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProphetZipkinServerApplication.class, args);
    }
}
