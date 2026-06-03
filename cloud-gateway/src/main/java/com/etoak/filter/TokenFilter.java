package com.etoak.filter;

import cn.hutool.json.JSONUtil;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.common.vo.ResultVO;
import com.etoak.properties.WhiteListProperties;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TokenFilter implements GlobalFilter {

    WhiteListProperties whiteListProperties;

    public TokenFilter(WhiteListProperties whiteListProperties) {
        this.whiteListProperties = whiteListProperties;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String uri = request.getPath().value();
        log.info("uri: {}", uri);
        if (whiteListProperties.getUris().contains(uri)) {

            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if(StringUtils.isEmpty( token)){
            return noAuth( response,"请传入令牌");
        }
        try{
            JwtUtil.parse( token);
        }catch (ExpiredJwtException e){
            log.error(e.getMessage(), e);
            return noAuth( response,"令牌已过期");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return noAuth( response,"令牌错误");
        }

        return chain.filter(exchange);
    }

    private Mono<Void> noAuth(ServerHttpResponse response, String message) {
       response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
       ResultVO<Object> failed = ResultVO.failed(ResultVO.FORBIDDEN_CODE, message);
       String jsonStr = JSONUtil.toJsonStr( failed);
       DataBuffer dataBuffer = response.bufferFactory().wrap(jsonStr.getBytes());
       return response.writeWith(Mono.just(dataBuffer));
    }
}
