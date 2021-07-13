package com.lyl.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.auth0.jwt.JWT;
import java.util.List;

@Component
public class UserFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        if(request.getMethod().name().equals("OPTIONS")) {
            return chain.filter(exchange);
        }
        System.out.println(request.getMethod().name());
        HttpHeaders headers = request.getHeaders();
        List<String> list = headers.get("token");
//        if(list.size() > 0) {
//            String token = list.get(0);
//            JWTVerifier jwt = JWT.require(Algorithm.HMAC256("liuyanliang")).build();
//            DecodedJWT verify = jwt.verify(token);
//            String username = verify.getClaim("username").asString();
//            System.out.println("当前用户是:" + username);
//            List<String> backurls = verify.getClaim("backurls").asList(String.class);
//            System.out.println(backurls);
//            // 从request拿到当前请求地址
//            System.out.println(request.getURI().getPath());
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }


}
