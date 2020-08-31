package org.ex.apiZiq.controllers;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ex.apiZiq.models.SpotifyApiSingleton;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Category;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.requests.data.browse.GetCategoryRequest;
import com.wrapper.spotify.requests.data.browse.GetListOfCategoriesRequest;
import com.wrapper.spotify.requests.data.browse.GetListOfFeaturedPlaylistsRequest;
import com.wrapper.spotify.requests.data.browse.GetListOfNewReleasesRequest;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
import com.wrapper.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;

@Service
@RestController
@RequestMapping("/browse")
@CrossOrigin(origins = "http://localhost:4200")
public class BrowseController {

	private static final Logger log = LogManager.getLogger(BrowseController.class);
	
	private static GetAvailableGenreSeedsRequest getGenreSeedsReq;
	private static GetCategoryRequest getCategoryReq;
	private static GetListOfCategoriesRequest getlistOfCatReq;
	private static GetListOfFeaturedPlaylistsRequest getListOfFeatPlaylistReq;
	private static GetListOfNewReleasesRequest getNewReleaseReq;
	private static GetRecommendationsRequest getRecommendationReq;
	
	private ObjectMapper pagingMapper = new ObjectMapper();
	
	@GetMapping("/genreseeds")
	public String genre() {	
		getGenreSeedsReq = SpotifyApiSingleton.getInstance().getAvailableGenreSeeds().build();
		try {
			final String[] s = getGenreSeedsReq.execute();
			return this.pagingMapper.writeValueAsString(s);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/getCategories") // country-limit-local
	public String getCategories() {
		getlistOfCatReq = SpotifyApiSingleton.getInstance().getListOfCategories().build();
		try {
			final Paging<Category> catPaging = getlistOfCatReq.execute();
			return this.pagingMapper.writeValueAsString(catPaging);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/category")
	public String category() { // besoin des id categories???
//		getCategoryReq = SpotifyApiSingleton.getInstance().getcat
		return null;
	}
	
	@GetMapping("/categoryplaylist")
	public String categoryplaylist() { // besoin des id categories playlist???
		return null;
	}
	
	@GetMapping("/getlistoffeatplaylist") // country-limit-offset-timestamp
	public String getListOfFeatPlaylist() {
		getListOfFeatPlaylistReq = SpotifyApiSingleton.getInstance().getListOfFeaturedPlaylists().build();
		try {
			final FeaturedPlaylists fp = getListOfFeatPlaylistReq.execute();
			return this.pagingMapper.writeValueAsString(fp);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/getNewRelease")
	public String getNewRelease() {
		getNewReleaseReq = SpotifyApiSingleton.getInstance().getListOfNewReleases().build();
		try {
			final Paging<AlbumSimplified> asp = getNewReleaseReq.execute();
			return this.pagingMapper.writeValueAsString(asp);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/getRecommendation")
	public String getRecommendation() {
//      .limit(10)
//      .market(CountryCode.SE)
//      .max_popularity(50)
//      .min_popularity(10)
//      .seed_artists("0LcJLqbBmaGUft1e9Mm8HV")
//      .seed_genres("electro")
//      .seed_tracks("01iyCAUm8EvOFqVWYJ3dVX")
//      .target_popularity(20)
		getRecommendationReq = SpotifyApiSingleton.getInstance().getRecommendations()
				.seed_genres("metal").build();
		try {
			final Recommendations r = getRecommendationReq.execute();
			return this.pagingMapper.writeValueAsString(r);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
}
