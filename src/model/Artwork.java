package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Artwork implements Serializable{
	
	private int artworkNo;
	private String image; 
    private String workSize;
    private String title;
    private String artistName;
    private String description; 
    private int price;
	private int likeCnt;
	private int isSoldOut;	
	///추가된 애들...
	private int isInWishList;
	private int isInCart;

    public Artwork() { //defualt 
    }
    

	public int getArtworkNo() {
		return artworkNo;
	}

	public void setArtworkNo(int artworkNo) {
		this.artworkNo = artworkNo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getWorkSize() {
		return workSize;
	}

	public void setWorkSize(String workSize) {
		this.workSize = workSize;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getIsSoldOut() {
		return isSoldOut;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public void setIsSoldOut(int isSoldOut) {
		this.isSoldOut = isSoldOut;
	}


	public int getIsInWishList() {
		return isInWishList;
	}


	public void setIsInWishList(int isInWishList) {
		this.isInWishList = isInWishList;
	}


	public int getIsInCart() {
		return isInCart;
	}


	public void setIsInCart(int isInCart) {
		this.isInCart = isInCart;
	}


	
	
	

}