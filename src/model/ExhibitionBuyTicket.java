package model;

public class ExhibitionBuyTicket{
	
	private int ticketNo;
	private int userNo;
	private int exhibitionNo;
	
	public ExhibitionBuyTicket() {}
	
	public ExhibitionBuyTicket(int ticketNo, int userNo, int exhibitionNo) {
		super();
		this.ticketNo = ticketNo;
		this.userNo = userNo;
		this.exhibitionNo = exhibitionNo;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getExhibitionNo() {
		return exhibitionNo;
	}

	public void setExhibitionNo(int exhibitionNo) {
		this.exhibitionNo = exhibitionNo;
	}
	
	
}