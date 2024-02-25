package com.fbla.parters.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        final String[] AUTH_WHITELIST = {

                "webjars/**",
                "css/**",
                "images/**",
                "js/**",
                "/",
                "home",
                "login",
                "/h2-ui/**",
                "/h2-console/**",
                // -- Swagger UI v3
                "/v3/api-docs/**",
                "v3/api-docs/**",
                "/swagger-ui/**",
                "swagger-ui/**",
                // CSA Controllers
                "/csa/api/token",
                // Actuators
                "/actuator/**",
                "/health/**",
        };

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureForwardUrl("/login-error").permitAll())
                .logout(form -> form.logoutSuccessUrl("/home").permitAll())
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("user1"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("xinge")
                .password(passwordEncoder().encode("xinge"))
                .roles("USER", "ADMIN")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles( "USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
