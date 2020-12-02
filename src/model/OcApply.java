package model;

import java.io.Serializable;

public class OcApply implements Serializable{
	
	private int applyNo;
	private int userNo;
	private int onedayclassNo;
	
	public OcApply() {}
	
	public OcApply(int applyNo, int userNo, int onedayclassNo) {
		super();
		this.applyNo = applyNo;
		this.userNo = userNo;
		this.onedayclassNo = onedayclassNo;
	}

	public int getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(int applyNo) {
		this.applyNo = applyNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getOnedayclassNo() {
		return onedayclassNo;
	}

	public void setOnedayclassNo(int onedayclassNo) {
		this.onedayclassNo = onedayclassNo;
	}
	
	
	
}