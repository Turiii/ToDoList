package com.spring.configuration;


import com.spring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/tasks/**").authenticated()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET).authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/logUser").permitAll()
                .antMatchers(HttpMethod.GET, "/logout").permitAll()
                .antMatchers(HttpMethod.GET, "/ToDoLists/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/ToDoLists/").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/tasks/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/tasks/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/tasks").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/comments/all").permitAll()
                .antMatchers(HttpMethod.DELETE, "/comments/delete").permitAll()
                .antMatchers(HttpMethod.PUT, "/comments/edit").permitAll()
                .antMatchers(HttpMethod.POST, "/comments/add").permitAll()
                .antMatchers("/home").permitAll()
                .and().formLogin().permitAll()
                .and().httpBasic()
                .and().headers().frameOptions().sameOrigin();


        httpSecurity.csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
}
