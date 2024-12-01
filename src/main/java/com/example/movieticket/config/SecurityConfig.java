package com.example.movieticket.config;

import com.example.movieticket.service.CustomOAuth2UserService;
import com.example.movieticket.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
        @Autowired
        private CustomSuccessHandler customSuccessHandler;

        @Bean
        public UserDetailsService userDetailsService() {
                return new UserService();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
                var auth = new DaoAuthenticationProvider();
                auth.setUserDetailsService(userDetailsService());
                auth.setPasswordEncoder(passwordEncoder());
                return auth;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
                return http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/css/**", "/js/**", "/", "/oauth/**", "/register",
                                                                "/error",
                                                                "/products", "/favicon.icon", "/cart", "/cart/**")
                                                .permitAll()
                                                .requestMatchers("/products/edit/**", "/products/add",
                                                                "/products/delete", "admin")
                                                .hasAnyAuthority("ADMIN")
                                                .requestMatchers("/api/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login")
                                                .deleteCookies("JSESSIONID")
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)
                                                .permitAll())
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .successHandler(customSuccessHandler)
                                                .failureUrl("/login?error")
                                                .permitAll())
                                .oauth2Login(oauth2 -> oauth2 // Thêm cấu hình OAuth2 cho đăng nhập bằng Google
                                                .loginPage("/login") // Trang đăng nhập OAuth2
                                                .defaultSuccessUrl("/secured") // Điều hướng sau đăng nhập thành công
                                //.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService.customOAuth2UserService()))
                                )
                                .rememberMe(rememberMe -> rememberMe
                                                .key("example")
                                                .rememberMeCookieName("example")
                                                .tokenValiditySeconds(24 * 60 * 60)
                                                .userDetailsService(userDetailsService()))
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                .accessDeniedPage("/403"))
                                .sessionManagement(sessionManagement -> sessionManagement
                                                .maximumSessions(1)
                                                .expiredUrl("/login"))
                                .build();
        }
}
