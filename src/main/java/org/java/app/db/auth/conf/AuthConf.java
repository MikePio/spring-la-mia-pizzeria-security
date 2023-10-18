package org.java.app.db.auth.conf;

import org.java.app.db.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

// * STEP 6.1 - AUTHENTICATION - Creare il file AuthConf in db.auth.conf e compilarlo con filterChain, userDetailsService, passwordEncoder, authenticationProvider
@Configuration
public class AuthConf {
  
  // * l'unico da modificare è sono i requestMatchers in filterChain mentre userDetailsService, passwordEncoder, authenticationProvider sono simili per tutti
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  
    // * STEP 6.8 - AUTHENTICATION - aggiunte le rotte di accesso nel file di configurazione AuthConf 
			http.csrf().disable().authorizeHttpRequests()
      // ! questa rotta è necessaria oppure le immagini, lo style, bootstrap, fontawesome ed altro potrebbero non funzionare 
      .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**", "/font-awesome/**").permitAll()
          // * .permitAll() fa accedere tutti: USER, ADMIN o anche un utente che non ha fatto il login
          .requestMatchers("/login").permitAll()
          .requestMatchers("/credits").permitAll()
          // * protegge le rotte e ti fa accedere solo se sei USER o ADMIN
          .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
          .requestMatchers("/pizzas").hasAnyAuthority("USER", "ADMIN")
          // .requestMatchers("/pizzas/**").hasAnyAuthority("USER", "ADMIN")
          // OPPURE (Soluzione + precisa)
          .requestMatchers(new RegexRequestMatcher("/pizzas/[0-9]+", null)).hasAnyAuthority("USER", "ADMIN")
          // * protegge le rotte e ti fa accedere solo se sei ADMIN
          .requestMatchers("/ingredients").hasAuthority("ADMIN")
          .requestMatchers("/ingredients/**").hasAuthority("ADMIN")
          .and().formLogin().defaultSuccessUrl("/")
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