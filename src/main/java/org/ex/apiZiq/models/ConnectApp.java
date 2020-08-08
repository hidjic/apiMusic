package org.ex.apiZiq.models;

public class ConnectApp {

	private int id;
	private String etat;
	private String uri;
	
	public ConnectApp(int id, String etat, String uri) {
		this.id = id;
		this.etat = etat;
		this.uri = uri;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "ConnectApp [id=" + id + ", etat=" + etat + ", uri=" + uri + "]";
	}
}
