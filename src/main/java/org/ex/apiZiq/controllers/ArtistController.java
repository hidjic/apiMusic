package org.ex.apiZiq.controllers;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.ex.apiZiq.ApiZiqApplication;
import org.ex.apiZiq.AuthorizationCodeRefresh;
import org.ex.apiZiq.models.SpotifyApiSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.ex.apiZiq.model.Artist;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;

@Service
@RestController
@RequestMapping("/artist")
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistController {
	
	private static final Logger log = LogManager.getLogger(ApiZiqApplication.class);
	
	private static SearchArtistsRequest searchArtistReq;
	private static GetArtistRequest getArtistReq;
	private ObjectMapper pagingMapper = new ObjectMapper();
	
	private String index = "spotifyMusic";	// spotify
	
//	private static SpotifyApi sApi = SpotifyApiSingleton.getInstance();
	
	RestHighLevelClient c = new RestHighLevelClient(
			RestClient.builder(new HttpHost("localhost", 9200, "http")));

	@PostMapping("/create")
	public String save(@RequestBody org.ex.apiZiq.models.Artist artist) {

		IndexRequest req = new IndexRequest(this.index).source(artist);

		try {
			IndexResponse rep = this.c.index(req, RequestOptions.DEFAULT);
			return rep.getId();
		} catch (IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/searchByName")
	public String searchByName(@RequestParam("name") String name) {	
		searchArtistReq = SpotifyApiSingleton.getInstance().searchArtists(name).build();
		try {
			Paging<Artist> ap = searchArtistReq.execute();
//			ObjectMapper pagingMapper = new ObjectMapper();
//			String jsonRes = this.pagingMapper.writeValueAsString(ap.getItems());
//			return jsonRes;
			return this.pagingMapper.writeValueAsString(ap.getItems());
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/searchById")
	public String searchById(@RequestParam("id") String id) {
		log.info("call findById");
		getArtistReq = SpotifyApiSingleton.getInstance().getArtist(id).build();
		try {
			final Artist artist = getArtistReq.execute();
			return this.pagingMapper.writeValueAsString(artist);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
//	@GetMapping("/callback")
//	public void getCode(@RequestParam(value="code") String code) {
//		log.info("callback code => "+ code);
//	}
	
}
