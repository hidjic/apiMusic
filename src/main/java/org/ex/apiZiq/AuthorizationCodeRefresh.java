package org.ex.apiZiq;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ex.apiZiq.models.SpotifyApiSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;

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
