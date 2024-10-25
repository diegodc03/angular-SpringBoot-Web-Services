package com.irojas.demojwt.Auth.ModelDTO;

public class RequestChangePasswordOutOfSession {

	
	String currentPassword;
	String newPassword;
	String email;


	

	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public RequestChangePasswordOutOfSession(String email, String currentPassword, String newPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.email = email;


	}
	
	
}
