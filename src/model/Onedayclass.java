package model;

import java.io.Serializable;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Onedayclass implements Serializable {
	private int onedayclassNo;
	private User host; 
	private String image;
    private String title; 
    private int price;
    private String date; 
    private int maximum; 
    private int runningTime;
    private String deadline; 
    private int applicant; 
    private String place;
    private String description;

    
    public boolean isFull() { 
        if(maximum == applicant) {
            return true;
        }
        return false; 
    }
    
    public boolean isFinish() {    
        TimeZone tz;
        Calendar cal = Calendar.getInstance();
        
        tz = TimeZone.getTimeZone("GMT+09:00"); 

        cal.setTimeZone(tz);

        SimpleDateFormat curFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        
        
        String curTime = curFm.format(cal);  

        
        int compare = curTime.compareTo(deadline); 
        
        if(compare == 0 || compare > 0) {
            return true;
        }
        return false;

    }

    public Onedayclass() { }//defualt constructor

	public Onedayclass(int onedayclassNo, User host, String image, String title, int price, String date, int maximum,
			int runningTime, String deadline, int applicant, String place, String description) {
		super();
		this.onedayclassNo = onedayclassNo;
		this.host = host;
		this.image = image;
		this.title = title;
		this.price = price;
		this.date = date;
		this.maximum = maximum;
		this.runningTime = runningTime;
		this.deadline = deadline;
		this.applicant = applicant;
		this.place = place;
		this.description = description;
	}

	public int getOnedayclassNo() {
		return this.onedayclassNo;
	}
	
	public void setOnedayclassNo(int onedayclassNo) {
		this.onedayclassNo = onedayclassNo;
	}
    
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getHost() {
		return this.host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMaximum() {
		return this.maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getRunningTime() {
		return this.runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getDeadline() {
		return this.deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
    }
    
    public int getApplicant() {
		return this.applicant;
	}

	public void setApplicant(int applicant) {
		this.applicant = applicant;
	}

    public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

   
}