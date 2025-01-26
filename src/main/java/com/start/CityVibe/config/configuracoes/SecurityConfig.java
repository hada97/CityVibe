package com.start.CityVibe.config.configuracoes;

import com.start.CityVibe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Configuration
public class SecurityConfig  {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    // Permitir o acesso a arquivos estáticos (JS, CSS, Imagens)
                    registry.requestMatchers(
                            "/",
                            "/login",
                            "/users/register",
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/js/**",
                            "/css/**",
                            "/images/**",
                            "/static/**"
                    ).permitAll();

                    // Permitir acesso público ao endpoint GET /api/eventos
                    registry.requestMatchers(HttpMethod.GET, "/eventos/**").permitAll();

                    // Qualquer outra URL precisa ser autenticada
                    registry.anyRequest().authenticated();
                })
                .oauth2Login(oauth2login -> {
                    oauth2login
                            .loginPage("/login")
                            .successHandler((request, response, authentication) -> {
                                OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
                                OAuth2User oAuth2User = oauth2Token.getPrincipal();
                                String email = oAuth2User.getAttribute("email");
                                String name = oAuth2User.getAttribute("name");

                                if (!userService.existsByEmail(email)) {
                                    // Registrar o usuário automaticamente caso não exista
                                    userService.registerNewUser(email, name);
                                }
                                // Após o registro (ou não), redireciona para a página:
                                response.sendRedirect("/index");
                            });
                })
                .build();
    }
}
