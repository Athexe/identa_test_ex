package com.identa.spring.identa_test_ex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class IdentaTestExApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentaTestExApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args ->{
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("Ihor");
            if(u1==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("Ihor").password(passwordEncoder.encode("ihor")).roles("USER").build()
            );
            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("Oleg");
            if(u2==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("Oleg").password(passwordEncoder.encode("oleg")).roles("USER","EMPLOYEE").build()
            );
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }
}
