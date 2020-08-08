package org.ex.apiZiq.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("artist")
public class Artist {
	
	@Id
	private String id;
	private String name;
	
	public Artist(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + "]";
	}
}
