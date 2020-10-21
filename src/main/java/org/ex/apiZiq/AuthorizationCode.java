package org.ex.apiZiq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ex.apiZiq.models.SpotifyApiSingleton;

import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;

//@Service
public class AuthorizationCode {
	
	private static final Logger log = LogManager.getLogger(AuthorizationCode.class);
		
	public static void authorizationCode(String code) {
		AuthorizationCodeCredentials acc = SpotifyApiSingleton.getAuthorizationCodeCredentials(code);
		log.info("acc.getAccessToken() => " + acc.getAccessToken());
		SpotifyApiSingleton.getsApi().setAccessToken(acc.getAccessToken());
		SpotifyApiSingleton.getsApi().setRefreshToken(acc.getRefreshToken());
	}
}
