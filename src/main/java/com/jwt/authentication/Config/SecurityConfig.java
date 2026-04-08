package com.jwt.authentication.Config;

import com.jwt.authentication.Service.MyUserDetailsService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // this is configuration class
@EnableWebSecurity // use these configuration instead of default one
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    // Customising FilterChain

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
////        disable csrf
//        http.csrf(customizer -> customizer.disable());
////        authenticate
//        http.authorizeHttpRequests(request ->
//                request
//                .anyRequest()
//                .authenticated());
//
////        get login form: chrome
////        http.formLogin(Customizer.withDefaults());
////        for postman
//        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session ->
//                session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


//        HOW LAMBDA WORKS IN BACKEND.
//        Customizer<CsrfConfigurer<HttpSecurity>> customizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//                customizer.disable();
//            }
//        };
//
//        http.csrf(customizer);

//        customise - build a pattern

        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request

//                        permit these request without authentication
                        .requestMatchers("/register", "/login")
                        .permitAll()

//                        any other requests will need basic auth
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    manual users
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("meet")
//                .password("meet")
//                .roles("ADMIN")
//                .build();
//
//       return new InMemoryUserDetailsManager(user1);
//    }

//    working with database
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }
}
