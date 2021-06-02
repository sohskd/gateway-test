//package com.demo.gateway.filter;
//
//import com.demo.gateway.filter.MyFilter.Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class MyFilter extends AbstractGatewayFilterFactory<Config> {
//
//  @Autowired
//  private WebClient webClient;
//
//  public MyFilter() {
//    super(Config.class);
//  }
//
//  private boolean isAuthorizationValid(String authorizationHeader) {
//    boolean isValid = true;
//
//    // Logic for checking the value
//
//    return isValid;
//  }
//
//  private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus)  {
//    ServerHttpResponse response = exchange.getResponse();
//    response.setStatusCode(httpStatus);
//
//    return response.setComplete();
//  }
//
//  @Override
//  public GatewayFilter apply(Config config) {
//    return (exchange, chain) -> {
////      ServerHttpRequest request = exchange.getRequest();
//
////      if (!request.getHeaders().containsKey("Authorization")) {
////        return this.onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
////      };
////
////      String authorizationHeader = request.getHeaders().get("Authorization").get(0);
////
////      if (!this.isAuthorizationValid(authorizationHeader)) {
////        return this.onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
////      }
//
//      System.out.println("here");
//
//      Mono<String> s = webClient.get()
//          .exchange()
//          .flatMap(r -> r.bodyToMono(String.class));
////          .retrieve().bodyToMono(String.class);
//
//      ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().
//          header("secret", "some_secret").
//          build();
//
//      return chain.filter(exchange.mutate().request(modifiedRequest).build());
//    };
//  }
//
//  public static class Config {
//    // Put the configuration properties
//  }
//}