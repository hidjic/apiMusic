package org.ex.apiZiq;

import org.ex.apiZiq.models.SpotifyApiSingleton;

import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;

//@Service
public class AuthorizationCode {
	
//	private static final Logger log = LogManager.getLogger(AuthorizationCode.class);
		
	public static void authorizationCode(String code) {
		AuthorizationCodeCredentials acc = SpotifyApiSingleton.getAuthorizationCodeCredentials(code);
		SpotifyApiSingleton.getsApi().setAccessToken(acc.getAccessToken());
		SpotifyApiSingleton.getsApi().setRefreshToken(acc.getRefreshToken());
	}
}
