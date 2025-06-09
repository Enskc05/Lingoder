package com.lingoder.v1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x.requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/auth/forgot").permitAll()
                                .requestMatchers(HttpMethod.POST,"/auth/reset").permitAll()
                                .requestMatchers(HttpMethod.POST, "/word/add").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/word/delete/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/word").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/statistic").hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/quiz/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/quiz/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET,"/person/info").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/person/update/**").hasAnyRole("USER","ADMIN")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return security.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
