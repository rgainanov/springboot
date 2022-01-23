//package ru.geekbrains.springboot.springboot.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//
//import javax.sql.DataSource;
//
//@EnableWebSecurity
//@Profile("jdbc")
//public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {
//    private static final Logger logger = LoggerFactory.getLogger(JdbcSecurityConfig.class);
//
//    @Bean
//    public JdbcUserDetailsManager users(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        logger.info("JDBC Authentication Manager");
//
//        http.authorizeRequests()
////                .antMatchers("/auth_page/**").authenticated()
////                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin();
//
//    }
//}
