package cn.zlmisme.prophet.authority.controller;

import cn.zlmisme.prophet.authority.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liming zeng
 * @create 2020-10-19 16:14
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping(value = "/hello")
    public Map<String, String> hello() {
        helloService.hello();
        return new HashMap<>();
    }
}
