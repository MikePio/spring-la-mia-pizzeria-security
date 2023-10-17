package org.java.app.db.auth.conf;

import org.java.app.db.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// * STEP 6.1 - AUTHENTICATION - Creare il file AuthConf in db.auth.conf e compilarlo con filterChain, userDetailsService, passwordEncoder, authenticationProvider
@Configuration
public class AuthConf {
  
  // * l'unico da modificare è sono i requestMatchers in filterChain mentre userDetailsService, passwordEncoder, authenticationProvider sono simili per tutti
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    
    // todo da configurare le rotte
    http.authorizeHttpRequests()
    .requestMatchers("/**").permitAll()
    .and().formLogin()
    .and().logout();

    return http.build();
	}
	
	@Bean
	UserService userDetailsService() {
		return new UserService();
	}

  @Bean
  PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

}