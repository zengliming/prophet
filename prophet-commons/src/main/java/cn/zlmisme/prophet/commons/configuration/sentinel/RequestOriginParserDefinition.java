package cn.zlmisme.prophet.commons.configuration.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liming zeng
 * @create 2020/11/21 1:11 上午
 */
public class RequestOriginParserDefinition implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        return request.getHeader("source-name");
    }
}
