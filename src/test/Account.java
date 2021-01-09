package test;

import java.util.ArrayList;

public class Account {
	private String user; // 登入账户的名字
	private String password; // 密码
	private int maxScore; // 最高分

	public Account() {
		super();
	}

	public Account(String user, String password, int maxScore) {
		super();
		this.user = user;
		this.password = password;
		this.maxScore = maxScore;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

}
