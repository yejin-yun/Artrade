package model;

import java.io.Serializable;

public class SimpleArtworkInfo implements Serializable {

	private int artworkNo;
	private String image;
	private String title;
	private String artistName;
	private int price;
	private int isInWishlist;
	
	
	public SimpleArtworkInfo() {
		super();
	}
	
	

	public SimpleArtworkInfo(int artworkNo, String image, String title, String artistName, int price, int isInWishlist) {
		super();
		this.artworkNo = artworkNo;
		this.image = image;
		this.title = title;
		this.artistName = artistName;
		this.price = price;
		this.isInWishlist = isInWishlist;
	}



	public int getArtworkNo() {
		return artworkNo;
	}
	public void setArtworkNo(int artworkNo) {
		this.artworkNo = artworkNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getIsInWishlist() {
		return isInWishlist;
	}
	public void setIsInWishlist(int isInWishlist) {
		this.isInWishlist = isInWishlist;
	}
	
	
	
}
