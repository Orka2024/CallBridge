package com.orka.callbridge.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class Helper {

	public static String getEmailOfSignedInUser(Authentication authentication) {

		// AuthenticationPrincipal principal = (AuthenticationPrincipal)
		// authentication.getPrincipal();

		if (authentication instanceof OAuth2AuthenticationToken) {

			var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
			String authorizedClientRegistrationId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

			return "";

		} else {

			System.out.println("Getting Data from Local Database ");
			return authentication.getName();
		}

	}

}
