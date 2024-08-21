package com.security.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityCoonfig  {

//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//             http.csrf(csrf -> csrf.disable())
//                    .authorizeHttpRequests(authorize -> authorize.requestMatchers("/user/welcome").hasRole("USER")
//                            .requestMatchers("/user/add").permitAll()
//                            .requestMatchers("/user/userGetById").hasRole("USER")
//                            .requestMatchers("/user/getAll").hasRole("ADMIN")
//                            .anyRequest().authenticated()
//                    )
//                    .formLogin(Customizer.withDefaults());
//             return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/user/welcome").permitAll()
                    .requestMatchers("/user/add").permitAll()
                    .requestMatchers("/user/userGetById/{userid}").hasRole("USER")
                    .requestMatchers("/user/getAll").hasAnyRole("ADMIN", "USER") // This allows both ADMIN and USER roles to access
                    .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults()
            );


    return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }
}
