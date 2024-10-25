package com.irojas.demojwt.Auth.ModelDTO;

public class RequestChangePasswordOutOfSession {

	
	String confirmPassword;
	String newPassword;
	String token;


	

	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public RequestChangePasswordOutOfSession(String token, String newPassword, String confirmPassword) {
		super();
		this.confirmPassword = confirmPassword;
		this.newPassword = newPassword;
		this.token = token;


	}
	
	
}
