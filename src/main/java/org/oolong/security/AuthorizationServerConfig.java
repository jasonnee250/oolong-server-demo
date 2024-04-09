package org.oolong.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    private final DataSource dataSource;
    
    private final RedisConnectionFactory redisConnectionFactory;
    
    private final AuthenticationManager authenticationManager;
    
    private final UserDetailsService userDetailsService;
    
    private final PlatformTokenEnhancer platformTokenEnhancer;
    
    private final OAuthProperties oauthProperties;
    
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetails());
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(platformTokenEnhancer, jwtAccessTokenConverter()));
        endpoints.authenticationManager(authenticationManager).tokenEnhancer(tokenEnhancerChain)
                .tokenStore(redisTokenStore()).tokenGranter(new CompositeTokenGranter(this.getTokenGranters()))
                .accessTokenConverter(jwtAccessTokenConverter()).authorizationCodeServices(authorizationCodeServices())
                .userApprovalHandler(new DefaultUserApprovalHandler()).userDetailsService(userDetailsService);
    }
    
    @Bean
    @Primary
    public SecurityUnionEndpoint securityUnionEndpoint(AuthorizationServerEndpointsConfiguration config) {
        SecurityUnionEndpoint tokenEndpoint = new SecurityUnionEndpoint();
        tokenEndpoint.setClientDetailsService(config.getEndpointsConfigurer().getClientDetailsService());
        tokenEndpoint.setProviderExceptionHandler(config.getEndpointsConfigurer().getExceptionTranslator());
        tokenEndpoint.setTokenGranter(config.getEndpointsConfigurer().getTokenGranter());
        tokenEndpoint.setOAuth2RequestFactory(config.getEndpointsConfigurer().getOAuth2RequestFactory());
        tokenEndpoint.setoAuth2RequestValidator(config.getEndpointsConfigurer().getOAuth2RequestValidator());
        tokenEndpoint.setPasswordEncoder(passwordEncoder());
        tokenEndpoint.setOauthProperties(oauthProperties);
        return tokenEndpoint;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }
    
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(oauthProperties.getSigningKey());
        return accessTokenConverter;
    }
    
    @Bean
    public TokenEnhancer tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(platformTokenEnhancer, jwtAccessTokenConverter()));
        return tokenEnhancerChain;
    }
    
    @Bean
    public OAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(jdbcClientDetails());
    }
    
    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices() {
        PlatformTokenServices tokenServices = new PlatformTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setClientDetailsService(jdbcClientDetails());
        tokenServices.setTokenEnhancer(tokenEnhancerChain());
        tokenServices.setSupportRefreshToken(Boolean.TRUE);
        tokenServices.setReuseRefreshToken(Boolean.TRUE);
        return tokenServices;
    }
    
    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
    
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    
    @Bean
    public DefaultUserAuthenticationConverter defaultUserAuthenticationConverter() {
        return new DefaultUserAuthenticationConverter();
    }
    
    @Bean
    public ClientDetailsService jdbcClientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    
    @Bean
    public OAuth2RequestFactory requestFactory() {
        return new DefaultOAuth2RequestFactory(jdbcClientDetails());
    }
    
    @Bean
    public OAuth2RequestValidator oAuth2RequestValidator() {
        return new DefaultOAuth2RequestValidator();
    }
    
    private List<TokenGranter> getTokenGranters() {
        return Stream.of(new ClientCredentialsTokenGranter(tokenServices(), jdbcClientDetails(), requestFactory()),
                        new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices(), jdbcClientDetails(),
                                requestFactory()),
                        new AuthorizationCodeTokenGranter(tokenServices(), authorizationCodeServices(), jdbcClientDetails(),
                                requestFactory()),
                        new ImplicitTokenGranter(tokenServices(), jdbcClientDetails(), requestFactory()),
                        new RefreshTokenGranter(tokenServices(), jdbcClientDetails(), requestFactory()))
                .collect(Collectors.toList());
    }
}
