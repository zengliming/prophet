package cn.zlmisme.prophet.authority.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author liming zeng
 * @create 2020/11/21 6:37 下午
 */
@RestController
@RestControllerAdvice
@RequestMapping("/")
public class ErrorController {

    @GetMapping("/error")
    public Object error() {
        System.out.println("error");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }

    @ExceptionHandler(FlowException.class)
    public Object handlerFlowException(FlowException throwable) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }

    @ExceptionHandler(BlockException.class)
    public Object handlerBlockException(BlockException throwable) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handlerERuntimeException(RuntimeException throwable) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }

    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception throwable) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }

    @ExceptionHandler(Throwable.class)
    public Object handlerThrowable(Throwable throwable) {
        throwable.printStackTrace();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", false);
        return map;
    }
}
