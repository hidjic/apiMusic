package org.ex.apiZiq.servicesSpotify;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ex.apiZiq.interfaceServicesSpotify.ArtistIntServSpotify;
import org.ex.apiZiq.models.SpotifyApiSingleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;

public class ArtistServSpotifyImpl implements ArtistIntServSpotify {

	private static final Logger log = LogManager.getLogger(ArtistServSpotifyImpl.class);
	
	private static SearchArtistsRequest searchArtistReq;
	private static GetArtistRequest getArtistReq;
	private static GetArtistsAlbumsRequest getArtistAlbumReq;
	
	private int limitAlbum = 50;	// limit => min = 1; max = 50
	private int firstElement = 0;	// offset => array commence a 0
	
	private ObjectMapper pagingMapper = new ObjectMapper();
	
	@Override
	public String searchArtistByName(String name) {
		searchArtistReq = SpotifyApiSingleton.getInstance().searchArtists(name).build();
		try {
			final Paging<Artist> ap = searchArtistReq.execute();
			return this.pagingMapper.writeValueAsString(ap.getItems());
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}

	@Override
	public String searchArtistById(String id) {
		getArtistReq = SpotifyApiSingleton.getInstance().getArtist(id).build();
		try {
			final Artist artist = getArtistReq.execute();
			return this.pagingMapper.writeValueAsString(artist);
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}

	@Override
	public String getAllAbumsByIdArtist(String id, String typeAlbum) {
		getArtistAlbumReq = SpotifyApiSingleton.getInstance().getArtistsAlbums(id)
				.market(CountryCode.FR)
				.limit(limitAlbum)	// limit => min = 1; max = 50
				.offset(firstElement)	// offset => array commence a 0
				.album_type(typeAlbum)	// album - single - appears_on - compilation
				.build();
		try {
			final Paging<AlbumSimplified> asp = getArtistAlbumReq.execute();
			log.info("getTotal => " + asp.getTotal());
			return this.pagingMapper.writeValueAsString(asp.getItems());
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			log.debug(e.getMessage());
			return null;
		}
	}

}
