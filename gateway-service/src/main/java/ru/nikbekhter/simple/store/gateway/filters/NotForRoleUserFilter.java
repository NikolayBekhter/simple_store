package ru.nikbekhter.simple.store.gateway.filters;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.nikbekhter.simple.store.gateway.utils.JwtUtil;

import java.util.List;

@Component
public class NotForRoleUserFilter extends AbstractGatewayFilterFactory<NotForRoleUserFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    public NotForRoleUserFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            final String token = getAuthHeader(request);
            for (String role : getRoles(token)) {
                if (role.equals("ROLE_ADMIN")) {
                    return chain.filter(exchange);
                }
            }
            return this.onError(exchange, "Не достаточно прав!", HttpStatus.FORBIDDEN);
        };
    }

    public static class Config {}

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
    }

    private List<String> getRoles(String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        return (List) claims.get("roles");
    }

}
