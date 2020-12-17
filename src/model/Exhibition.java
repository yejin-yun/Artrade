package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Exhibition implements Serializable{
    private int exhibitionNo;
    private String title;
    private String description;
    private String period; 
    private ArrayList<Artwork> works;
    private int price; //free일 경우 가격 0으로 설정할 것.
    private int visitor;
    private String image;

	public Exhibition(){ //default
       
    }

    public Exhibition(int exhibitionNo, String title, String description, String period, ArrayList<Artwork> works,
		 int price, int visitor, String image) {
		super();
		this.exhibitionNo = exhibitionNo;
		this.title = title;
		this.description = description;
		this.period = period;
		this.works = works;
		this.price = price;
		this.visitor = visitor;
		this.image = image;
	}


	public int getExhibitionNo() {
		return exhibitionNo;
	}

	public void setExhibitionNo(int exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public ArrayList<Artwork> getWorks() {
		return this.works;
	}

	public void setWorks(ArrayList<Artwork> works) {
		this.works = works;
	}


	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
    }
    
    public int getVisitor() {
		return this.visitor;
	}

	public void setVisitor(int visitor) {
		this.visitor = visitor;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
    
}