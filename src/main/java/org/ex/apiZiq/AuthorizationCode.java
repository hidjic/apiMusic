package org.ex.apiZiq;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.core5.http.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ex.apiZiq.models.SpotifyApiSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyApi.Builder;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

//@Service
public class AuthorizationCode {
	
	private static final Logger log = LogManager.getLogger(AuthorizationCode.class);
		
	public static void authorizationCode(String code) {
		AuthorizationCodeCredentials acc = SpotifyApiSingleton.getAuthorizationCodeCredentials(code);
		SpotifyApiSingleton.getsApi().setAccessToken(acc.getAccessToken());
		SpotifyApiSingleton.getsApi().setRefreshToken(acc.getRefreshToken());
	}
}
