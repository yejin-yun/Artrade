package model;

import java.io.Serializable;
import java.util.List;

public class OrderArtworkInfo implements Serializable {
	int userNo;
	List<SimpleArtworkInfo> artworks;
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
	public List<SimpleArtworkInfo> getArtworks() {
		return artworks;
	}
	public void setArtworks(List<SimpleArtworkInfo> artworks) {
		this.artworks = artworks;
	}
	
}
