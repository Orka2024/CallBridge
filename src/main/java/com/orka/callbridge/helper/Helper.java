package com.orka.callbridge.helper;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class Helper {

	public static String getEmailOfSignedInUser(Authentication authentication) {

		Principal principal = (Principal) authentication.getPrincipal();

		if (principal instanceof OAuth2AuthenticatedPrincipal) {

			return "";

		} else {

			return principal.getName();
		}

	}

}
