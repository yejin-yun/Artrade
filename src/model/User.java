package model;

import java.io.Serializable;

public class User implements Serializable{
	private int userNo;
	private String userId;
	private String password;
	private String name;
    private String nickname;
    private String email;
    private String phone;
    

	public User() { }		//�⺻ ������
	
	public User(String userId, String password, String name, String nickname, String email, String phone) {
		this.userId = userId;
		this.password = password;
        this.name = name;
        this.nickname = nickname;
		this.email = email;
		this.phone = phone;
	}

	public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.nickname = updateUser.nickname;
        this.email = updateUser.email;
        this.phone = updateUser.phone;
    }
	
	public int getUserNo() {
		return this.userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
    }
    
    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/* ��й�ȣ �˻� */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
	}
	
	//id, �г��� �ߺ��˻� �����~

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", nickname=" + nickname + ", email=" + email + ", phone="
				+ phone + "]";
	}	
}
