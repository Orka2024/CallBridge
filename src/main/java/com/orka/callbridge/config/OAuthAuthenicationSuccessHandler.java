package com.orka.callbridge.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

//import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Providers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.orka.callbridge.dao.UserRepository;
import com.orka.callbridge.entities.User;
import com.orka.callbridge.helper.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

	Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("OAuthAuthenicationSuccessHandler");

		// identify the provider

		var oauth2AuthenicationToken = (OAuth2AuthenticationToken) authentication;

		String authorizedClientRegistrationId = oauth2AuthenicationToken.getAuthorizedClientRegistrationId();

		logger.info(authorizedClientRegistrationId);

		var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

		oauthUser.getAttributes().forEach((key, value) -> {
			logger.info(key + " : " + value);
		});

		User user = new User();
		user.setuId(UUID.randomUUID().toString());
		user.setURoleList(List.of(AppConstants.ROLE_CALLER));
		user.setuEmailVerified(true);
		user.setuEnabled(true);
		user.setuPassword("dummy");

		User userEmailLogin = userRepository.findByUEmail(user.getuEmail()).orElse(null);
		if (userEmailLogin == null) {
			userRepository.save(user);
			System.out.println("user saved:" + user.getuEmail());
		}

		new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");

	}

}