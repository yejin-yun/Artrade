package model;

import java.io.Serializable;

public class Exhibition implements Serializable{
    private int exhibitionNo;
    private String title;
    private String description;
    private String period; 
    private int price;
    private int visitor;
    private String image;
	private int isHaveTicket; //안샀으면  0, 샀으면 ticketNo값이 들어감

	public Exhibition(){ //default
       
    }

	public Exhibition(int exhibitionNo, String title, String description, String period, int price, int visitor,
			String image, int isHaveTicket) {
		super();
		this.exhibitionNo = exhibitionNo;
		this.title = title;
		this.description = description;
		this.period = period;
		this.price = price;
		this.visitor = visitor;
		this.image = image;
		this.isHaveTicket = isHaveTicket;
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
	
	public int getIsHaveTicket() {
		return isHaveTicket;
	}

	public void setIsHaveTicket(int isHaveTicket) {
		this.isHaveTicket = isHaveTicket;
	}
    
}