package com.demo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@EnableWebFluxSecurity
public class WebClientConfig {

  @Bean
  public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
      ReactiveClientRegistrationRepository clientRegistrationRepository,
      ReactiveOAuth2AuthorizedClientService authorizedClientService) {

    ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
        ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
            .clientCredentials()
            .build();

    AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager =
        new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
            clientRegistrationRepository, authorizedClientService);

    authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

    return authorizedClientManager;
  }

  @Bean
  public WebClient webClient(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
    String registrationId = "custom";

    ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
        authorizedClientManager);

    oauth.setDefaultClientRegistrationId(registrationId);
    return WebClient.builder()
        .baseUrl("http://localhost:8888")
        .filter(oauth).build();
  }

  @Bean
  public SecurityWebFilterChain configure(ServerHttpSecurity http) {
    return http
        .oauth2Client()
        .and()
        .build();
  }

//  @Bean
//  WebClient webClient(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
//    ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
//        new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
//    oauth2Client.setDefaultClientRegistrationId("custom");
//    return WebClient.builder()
//        .filter(oauth2Client)
//        .build();
//  }
}
