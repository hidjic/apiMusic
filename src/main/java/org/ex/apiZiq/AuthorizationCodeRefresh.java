package org.ex.apiZiq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ex.apiZiq.models.SpotifyApiSingleton;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;

@Service
public class AuthorizationCodeRefresh {
	
	private static final Logger log = LogManager.getLogger(AuthorizationCodeRefresh.class);

	public static void authorizationCodeRefresh() {
		log.info("authorizationCodeRefresh...");

		SpotifyApiSingleton.getsApi().setRefreshToken("");
		AuthorizationCodeCredentials acc = SpotifyApiSingleton.getAuthorizationCodeRefreshRequest();
		SpotifyApiSingleton.getsApi().setAccessToken(acc.getAccessToken());
		SpotifyApiSingleton.getsApi().setRefreshToken(acc.getRefreshToken());
		log.info("accesToken => " + acc.getAccessToken());
		log.info("refreshToken => " + acc.getRefreshToken());
		log.info("expire in => " + acc.getExpiresIn());
	}
	
}
