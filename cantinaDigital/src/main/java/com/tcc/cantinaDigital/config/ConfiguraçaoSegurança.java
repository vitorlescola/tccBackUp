package com.tcc.cantinaDigital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tcc.cantinaDigital.service.DetalhesUsuarioService;

@Configuration
public class ConfiguraçaoSegurança {

    private final DetalhesUsuarioService detalhesUsuarioServico;

    public ConfiguraçaoSegurança(DetalhesUsuarioService detalhesUsuarioServico) {
        this.detalhesUsuarioServico = detalhesUsuarioServico;
    }

    @Bean
    public BCryptPasswordEncoder encoderSenha() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> 
                authorize
                    .requestMatchers("/login", "/criarConta", "/error", "/h2-console/**").permitAll()
                    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                    .requestMatchers("/cadastrarProduto","/menuAdm","/perfilAdm", "/LanchesAdm", "/bebidasAdm", "/docesAdm").hasAuthority("ROLE_ADM")
                    .anyRequest().authenticated()
            )
            .formLogin((form) -> 
                form
                    .loginPage("/login")
                    .successHandler(customSuccessHandler())
                    .permitAll()
            )
            .logout((logout) -> logout
                    .logoutUrl("/logout")  
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
            );

        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new Autenticaçao();
    }
}