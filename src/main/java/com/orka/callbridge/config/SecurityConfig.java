package com.orka.callbridge.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.Customizer;
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

	
	@Autowired
	private SecurityCustomUserDetailService userDetailService;

	// Configuration of authentication provider for spring security
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
            .userDetailsService(userDetailService)
            .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
	
	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider daoAuthenticationProvider = new
	 * DaoAuthenticationProvider();
	 * daoAuthenticationProvider.setUserDetailsService(userDetailService); //
	 * UserDetailsService object
	 * daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); //
	 * PasswordEncoder object return daoAuthenticationProvider; }
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// Configuration
		// Configure urls for public and private
		httpSecurity.authorizeHttpRequests(authorize -> {
			// Role-based access
            authorize.requestMatchers("/user/**").hasRole("CALLER");
            authorize.requestMatchers("/operations/**").hasRole("OPERATIONS");
            authorize.requestMatchers("/TL/**").hasRole("TL");
            authorize.requestMatchers("/So/**").hasRole("SALESOFFICER");
            authorize.requestMatchers("/HR/**").hasRole("HR");
            authorize.requestMatchers("/Admin/**").hasRole("ADMIN");
            authorize.anyRequest().permitAll(); // All other URLs are accessible	
            });
		// Form Login
		// If we need to change anything : Related to Form Login

		httpSecurity.formLogin(formLogin -> {
			formLogin.loginPage("/signin");
			formLogin.loginProcessingUrl("/authenticate");
//			formLogin.successForwardUrl("/user/dashboard");
//			formLogin.failureForwardUrl("/signin?error=true");
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
				var authorities=authentication.getAuthorities().toString();
				
				if (authorities.contains("ROLE_CALLER"))
				 {
					response.sendRedirect("/user/dashboard");
				 }
				 else if (authorities.contains("ROLE_OPERATIONS"))
				 {
			            response.sendRedirect("/operations/dashboard");
			     } 
 
				 else if (authorities.contains("ROLE_SALESOFFICER"))
				 {
			            response.sendRedirect("/So/dashboard");
			     } 				
				 else if (authorities.contains("ROLE_TL"))
				 {
			            response.sendRedirect("/TL/dashboard");
			     } 
				 else if (authorities.contains("ROLE_ADMIN")) 
				 {
			            response.sendRedirect("/Admin/dashboard");
			     }
				 else if (authorities.contains("ROLE_HR")) 
				 {
			            response.sendRedirect("/HR/dashboard");
			     }
				 else 
				 {
			            // Default redirect for any other roles
			            response.sendRedirect("/signin?error=true");
			     }
			}
		});
			
			
			

//			formLogin.failureHandler(new AuthenticationFailureHandler() {
//
//				@Override
//				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//						AuthenticationException exception) throws IOException, ServletException {
//					response.sendRedirect("/signin?error=true");
//				}
//			});
//
//			formLogin.successHandler(new AuthenticationSuccessHandler() {
//				@Override
//				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//						Authentication authentication) throws IOException, ServletException {
//					response.sendRedirect("/user/dashboard");
//				}
//			});

		});

		httpSecurity.logout(logoutForm -> {
			logoutForm.logoutUrl("/signout");
			logoutForm.logoutSuccessUrl("/signin?logout=true");
		});

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Use BCrypt for better security
	}

}