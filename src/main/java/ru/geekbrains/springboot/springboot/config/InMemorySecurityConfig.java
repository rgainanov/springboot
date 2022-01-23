//package ru.geekbrains.springboot.springboot.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@EnableWebSecurity
//@Profile("inmemory")
//public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {
//    private static final Logger logger = LoggerFactory.getLogger(InMemorySecurityConfig.class);
//
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$12$AshxHV0h4OCW1qkxwHT2I.MX65PCo4hnHbAg9zburadXi6YJSp8hu")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$AshxHV0h4OCW1qkxwHT2I.MX65PCo4hnHbAg9zburadXi6YJSp8hu")
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        logger.info("In-memory Security Configuration");
//
//        http.authorizeRequests()
////                .antMatchers("/auth_page/**").authenticated()
////                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin();
//    }
//}
