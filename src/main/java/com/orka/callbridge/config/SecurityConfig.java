package com.orka.callbridge.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.orka.callbridge.service.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	// User create and login using java code with memory service
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails userAccess = User
//				.withDefaultPasswordEncoder()
//				.username("admin123")
//				.password("admin123")
//				.roles("ADMIN","USER")
//				.build();
//		
//		UserDetails userCaller = User
//				.withDefaultPasswordEncoder()
//				.username("user")
//				.password("user123")
//				.build();
//				
//		var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(userAccess, userCaller);
//		return inMemoryUserDetailsManager;
//	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); // Use BCrypt for better security }
	 */

	@Autowired
	private SecurityCustomUserDetailService userDetailService;

	// Configuration of authentication provider for spring security
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailService); // UserDetailsService object
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // PasswordEncoder object

		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		// Configuration
		// Configure urls for public and private
		httpSecurity.authorizeHttpRequests(authorize -> {
			// authorize.requestMatchers("/home","signin","/signup").permitAll();
			authorize.requestMatchers("/user/**").authenticated();
			authorize.anyRequest().permitAll();
		});

		// Form Default Login
		// If we need to change anything : Related to Form Login
	    httpSecurity.formLogin(formLogin -> {
	        formLogin.loginPage("/signin");
	        formLogin.loginProcessingUrl("/authenticate");
	        formLogin.successForwardUrl("/user/dashboard");
	        formLogin.failureForwardUrl("/signin?error=true");
	        formLogin.usernameParameter("uEmail");
	        formLogin.passwordParameter("uPassword");
	        formLogin.failureHandler(new AuthenticationFailureHandler() {
				
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					response.sendRedirect("/signin?error=true");					
				}
			});
	        
	        formLogin.successHandler(new AuthenticationSuccessHandler() {
				
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					response.sendRedirect("/user/dashboard");					
				}
			});
	        
	        
	        });
	    
	    httpSecurity.logout(logoutForm->{
	    	logoutForm.logoutUrl("/signout");
	    	logoutForm.logoutSuccessUrl("/signin?logout=true");
	    });

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
