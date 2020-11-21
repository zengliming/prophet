package cn.zlmisme.prophet.authority.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author liming zeng
 * @create 2020/11/21 6:08 下午
 */
@Service
public class HelloService {

    @SentinelResource(value = "hello")
    public void hello() {

    }
}
