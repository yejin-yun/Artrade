package model;

import java.io.Serializable;

public class ArtworkOrder implements Serializable{
    // 주문 번호(primary key가 될 것.)
    // 상품정보(ArrayList<ArtWorks>, 여러개 구매할 수 있으니까)
    // 배송받을 주소, 수취자, 전화번호, 결제수단(카드결제, 무통장입금(입금자명, 입금은행), 휴대폰결제, 실시간 계좌이체(예금주명)) 
    // 결재금액 

    private int ArtworkOrderNo;
    private int userNo;
    private String destination;
    private String receiver;
    private String phone;

    public ArtworkOrder(){
    	
    }
    
    public ArtworkOrder(int ArtworkOrderNo, int userNo, String destination, String receiver, String phone) {
        this.ArtworkOrderNo = ArtworkOrderNo;
        this.userNo = userNo;
        this.destination = destination;
        this.receiver = receiver;
        this.phone = phone;
    }

	public int getArtworkOrderNo() {
		return this.ArtworkOrderNo;
	}

	public void setArtworkOrderNo(int ArtworkOrderNo) {
		this.ArtworkOrderNo = ArtworkOrderNo;
	}

	public int getUserNo() {
		return this.userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    

}