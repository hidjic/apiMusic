package org.ex.apiZiq;

import java.net.URI;

import org.ex.apiZiq.models.SpotifyApiSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

@Service
public class AuthorizationCodeUri {
	
//	private static final Logger log = LogManager.getLogger(AuthorizationCodeUri.class);
	
	private static String clientId;
	private static String clientSecret;
	private static URI redirectUri;
	@Autowired
	public AuthorizationCodeUri(@Value("${spotify.clientId}") String _clientId, 
								@Value("${spotify.clientSecret}") String _clientSecret,
								@Value("${spotify.redirectUri}") URI _redirectUri) {
		clientId = _clientId;
		clientSecret = _clientSecret;
		redirectUri = _redirectUri;
	}

	public static String authorizationCodeUri() {
		SpotifyApiSingleton.setClientId(clientId);
		SpotifyApiSingleton.setClientSecret(clientSecret);
		SpotifyApiSingleton.setRedirectUri(redirectUri);
		
		SpotifyApi sApi = SpotifyApiSingleton.getInstance();
		
		final AuthorizationCodeUriRequest acur = sApi.authorizationCodeUri()
//				.state("")
				.scope("user-read-private,user-read-email")
//				.show_dialog(true)
				.build();
		
		URI uri = acur.execute();
		return uri.toString();
	}
	
}
