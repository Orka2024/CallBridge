package com.orka.callbridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {

	/*
	 * @Bean public UserDetailsService getUserDetailsService() { return new
	 * UserDetailsServiceImpl(); }
	 * 
	 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * @Bean public DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider daoAuthenticationProvider = new
	 * DaoAuthenticationProvider();
	 * daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService())
	 * ; daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	 * 
	 * return daoAuthenticationProvider; }
	 * 
	 * @Bean public AuthenticationManager
	 * authenticationManager(AuthenticationConfiguration
	 * authenticationConfiguration) throws Exception { return
	 * authenticationConfiguration.getAuthenticationManager(); }
	 * 
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http.authorizeHttpRequests((requests) -> requests
	 * .requestMatchers("/admin/**").hasRole("ADMIN")
	 * .requestMatchers("/user/**").hasRole("USER")
	 * .requestMatchers("/**").permitAll() ) .formLogin((form) -> form
	 * .loginPage("/signin") .loginProcessingUrl("/dologin")
	 * .defaultSuccessUrl("/user/index") ) .csrf((csrf) -> csrf.disable());
	 * 
	 * return http.build(); }
	 */
}
