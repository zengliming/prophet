package cn.zlmisme.prophet.gateway.filter;

import cn.zlmisme.prophet.commons.util.NetUtil;
import cn.zlmisme.prophet.gateway.properties.BlacklistProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author liming zeng
 * @create 2020-11-05 19:31
 */
@Slf4j
@Component
public class IpBlacklistFilter implements GlobalFilter, Ordered {

    @Resource
    private BlacklistProperties blacklistProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final String uri = request.getPath().toString();
        if (Objects.nonNull(blacklistProperties)) {
            final Optional<String> optional = NetUtil.getIp(request);
            if (optional.isPresent()) {
                // 根据url匹配黑名单列表
                final Optional<BlacklistProperties.Blacklist> first = blacklistProperties.getBlacklists()
                        .stream().filter(blacklist -> Objects.equals(blacklist.getUri(), uri)).findFirst();
                // 是否找到对应的规则
                if (first.isPresent()) {
                    final List<String> ips = first.get().getIps();
                    final Optional<String> ipOptional = NetUtil.getIp(request);
                    if (ipOptional.isPresent()) {
                        if (ips.contains(ipOptional.get())) {
                            // 符合黑名单
                            log.info("uri: {},ip: {}存在黑名单", uri, ipOptional.get());
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return Mono.empty();
                        }

                    } else {
                        // 无法获取ip
                        log.info("无法获取ip");
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return Mono.empty();
                    }
                }
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
