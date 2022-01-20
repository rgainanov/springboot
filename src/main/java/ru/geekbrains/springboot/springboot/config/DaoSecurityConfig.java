package ru.geekbrains.springboot.springboot.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.geekbrains.springboot.springboot.services.UserService;

@EnableWebSecurity(debug = true)
@Profile("dao")
@RequiredArgsConstructor
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class);

    @Override
    public void configure(HttpSecurity http) throws Exception {
        logger.info("DAO Authentication Provider");

        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/dao").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
