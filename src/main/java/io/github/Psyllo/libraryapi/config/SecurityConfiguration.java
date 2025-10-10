package io.github.Psyllo.libraryapi.config;

import io.github.Psyllo.libraryapi.Security.CustomUserDetailsService;
import io.github.Psyllo.libraryapi.Security.LoginSocialSucessHandler;
import io.github.Psyllo.libraryapi.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSocialSucessHandler sucessHandler) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(configurer -> {
//                    configurer.loginPage("/login");
//                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();

                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
                    oauth2.successHandler(sucessHandler);
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService) {

//        UserDetails user1 = User.builder()
//                .username("F")
//                .password(encoder.encode("opa"))
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("FF")
//                .password(encoder.encode("opaa"))
//                .roles("USER")
//                .build();
//
//        UserDetails user3 = User.builder()
//                .username("FFF")
//                .password(encoder.encode("opaaa"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2, user3);

        return new CustomUserDetailsService(usuarioService);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}
