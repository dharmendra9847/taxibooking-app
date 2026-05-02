package com.app.taxi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private CustomLogoutHandler customLogoutHandler;

	@Autowired
	public void setCustomLogoutHandler(CustomLogoutHandler customLogoutHandler) {
		this.customLogoutHandler = customLogoutHandler;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) {

		http
	    .csrf(csrf -> csrf.disable())
	    .authorizeHttpRequests(auth -> auth
	        
	        // 1. ALLOW LOCAL STATIC FOLDERS
	        .requestMatchers("/css/**", "/js/**", "/images/**", "/fragments/**").permitAll()
            
	        // 2. ADMIN PROTECTION
	        .requestMatchers("/readAllContacts").hasRole("ADMIN")
	        .requestMatchers("/admin/**").hasRole("ADMIN")
	        
	        // 3. PUBLIC PAGES
	        .requestMatchers("/", "/login", "/dologout", "/logout/**").permitAll()
	        .anyRequest().permitAll()
	    )
	    .formLogin(form -> form
	        .loginPage("/login")
	        .defaultSuccessUrl("/admin/dashboard", true)
	        .permitAll()
	    )
	    .logout(logout -> logout
	        .logoutUrl("/dologout")
	        .addLogoutHandler(customLogoutHandler)
	        .logoutSuccessUrl("/logout?success=true")
	        .invalidateHttpSession(true)
	        .clearAuthentication(true)
	        .deleteCookies("JSESSIONID")
	        .permitAll()
	    );
	
	return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
