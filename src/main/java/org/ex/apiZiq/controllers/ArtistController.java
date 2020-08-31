package org.ex.apiZiq.controllers;

import org.ex.apiZiq.interfaceServicesSpotify.ArtistIntServSpotify;
import org.ex.apiZiq.servicesSpotify.ArtistServSpotifyImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/artist")
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistController {
	
//	private static final Logger log = LogManager.getLogger(ApiZiqApplication.class);
	
	private ArtistIntServSpotify artistSpotify = new ArtistServSpotifyImpl();
	
//	private static SearchArtistsRequest searchArtistReq;
//	private static GetArtistRequest getArtistReq;
//	private static GetArtistsAlbumsRequest getArtistAlbumReq;
	
//	private ObjectMapper pagingMapper = new ObjectMapper();
	
//	private String index = "spotifyMusic";	// spotify
	
//	private static SpotifyApi sApi = SpotifyApiSingleton.getInstance();
	
//	RestHighLevelClient c = new RestHighLevelClient(
//			RestClient.builder(new HttpHost("localhost", 9200, "http")));

//	@PostMapping("/create")
//	public String save(@RequestBody org.ex.apiZiq.models.Artist artist) {
//
//		IndexRequest req = new IndexRequest(this.index).source(artist);
//
//		try {
//			IndexResponse rep = this.c.index(req, RequestOptions.DEFAULT);
//			return rep.getId();
//		} catch (IOException e) {
//			log.debug(e.getMessage());
//			return null;
//		}
//	}
	
	@GetMapping("/searchByName")
	public String searchByName(@RequestParam("name") String name) {
		return this.artistSpotify.searchArtistByName(name);
	}
	
	@GetMapping("/searchById")
	public String searchById(@RequestParam("id") String id) {
		return this.artistSpotify.searchArtistById(id);
	}
	
	@GetMapping("/getAlbumsByIdArtist")
	public String getAlbumByIdArtist(@RequestParam("id") String id) {
		return this.artistSpotify.getAllAbumsByIdArtist(id);
	}
	
}
