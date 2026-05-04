package com.backend.servicios.Configuracion;

import com.backend.servicios.Component.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;



@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔓 Seguridad (temporal abierta)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        http
                .csrf(csrf -> csrf.disable()) // importante para API REST
                .cors(cors -> {}) // habilitar CORS
                .authorizeHttpRequests(auth -> auth

                        //SOLO GET público
                        .requestMatchers(HttpMethod.GET, "/servicios/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categorias/**").permitAll()

                        // auth libre
                        .requestMatchers("/auth/**").permitAll()

                        //TODO lo demás requiere login
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    // 🌐 CORS global
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:4200"); // Angular
        config.addAllowedOrigin("https://moonlit-kringle-d82d5f.netlify.app");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }
}
