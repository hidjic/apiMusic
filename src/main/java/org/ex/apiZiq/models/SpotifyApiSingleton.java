package org.ex.apiZiq.models;

import java.io.IOException;
import java.net.URI;
import java.util.Timer;

import org.apache.hc.core5.http.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

public final class SpotifyApiSingleton {
	
	private static final Logger log = LogManager.getLogger(SpotifyApiSingleton.class);
	
	private static SpotifyApi sApi;
	private static String clientId;
	private static String clientSecret;
	private static URI redirectUri;
	private static AuthorizationCodeCredentials acc;
	private static AuthorizationCodeRefreshRequest acrr;
	private static Timer timer;
	
	private SpotifyApiSingleton() {
//		log.info("SpotifyApiSingleton...");
//		log.info("clientId => " + clientId);
//		log.info("clientSecret => " + clientSecret);
//		log.info("redirectUri => " + redirectUri);
		setsApi(new SpotifyApi.Builder()
				.setClientId(clientId)
				.setClientSecret(clientSecret)
				.setRedirectUri(redirectUri)
				.build());
	}
	
	public static SpotifyApi getInstance() {
		if(getsApi() == null && clientId != null) {
			new SpotifyApiSingleton();
			log.info("instanciation du singleton...");
		}else {
			if(clientId == null) {
				log.info("clientId => null...");
				getAuthorizationCodeRefreshRequest();
			}else {
				log.info("singleton deja existante...");
			}
		}
		return getsApi();
	}

	public static SpotifyApi getsApi() {
		return sApi;
	}

	public static void setsApi(SpotifyApi sApi) {
		SpotifyApiSingleton.sApi = sApi;
	}

	public static String getClientId() {
		return clientId;
	}

	public static void setClientId(String clientId) {
		SpotifyApiSingleton.clientId = clientId;
	}

	public static String getClientSecret() {
		return clientSecret;
	}

	public static void setClientSecret(String clientSecret) {
		SpotifyApiSingleton.clientSecret = clientSecret;
	}

	public static URI getRedirectUri() {
		return redirectUri;
	}

	public static void setRedirectUri(URI redirectUri) {
		SpotifyApiSingleton.redirectUri = redirectUri;
	}

	public static Logger getLog() {
		return log;
	}
	
	public static AuthorizationCodeCredentials getAcc() {
		return acc;
	}

	public static void setAcc(AuthorizationCodeCredentials acc) {
		SpotifyApiSingleton.acc = acc;
	}
	
	public static AuthorizationCodeRefreshRequest getAcrr() {
		return acrr;
	}

	public static void setAcrr(AuthorizationCodeRefreshRequest acrr) {
		SpotifyApiSingleton.acrr = acrr;
	}
	
	public static Timer getTimer() {
		return timer;
	}

	public static void setTimer(Timer timer) {
		SpotifyApiSingleton.timer = timer;
	}
	
	// service

	public static AuthorizationCodeCredentials getAuthorizationCodeCredentials(String code) {
//		log.info("getAuthorizationCodeCredentials...");
		AuthorizationCodeRequest acr = sApi.authorizationCode(code).build();
		try {
			acc = acr.execute();
			setAcc(acc);
//			setTimer(new Timer());
//			getTimer().schedule(managementOfTime, 3500000);;
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.error(e.getMessage());
		}
//		log.info("acc => " + acc);
		return getAcc();
	}
	
	public static AuthorizationCodeCredentials getAuthorizationCodeRefreshRequest() {
//		log.info("getAuthorizationCodeRefreshRequest...");
		AuthorizationCodeRefreshRequest acrr = sApi.authorizationCodeRefresh().build();
		
		try {
			acc = acrr.execute();
			setAcc(acc);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.error(e.getMessage());
		}
		return getAcc();
	}
	
//	static TimerTask managementOfTime = new TimerTask() {
//		@Override
//		public void run() {
//			AuthorizationCodeRefresh.authorizationCodeRefresh();
//		}
//	};

	

	
	
}
