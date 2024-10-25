package com.irojas.demojwt.Auth.ModelDTO;

public class UserRegisterDTO {
	// ATRIBUTES
			private Long id;
			private String email;
			private String firstName;
			private String lastName;
			private String country;
			private String password1;
			private String password2;


		// GETTERS AND SETTERS
			
			
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
			public String getFirstName() {
				return firstName;
			}
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			
			public String getLastName() {
				return lastName;
			}
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
			public String getCountry() {
				return country;
			}
			public void setCountry(String country) {
				this.country = country;
			}
			public String getPassword1() {
				return password1;
			}
			public void setPassword1(String password1) {
				this.password1 = password1;
			}
			public String getPassword2() {
				return password2;
			}
			public void setPassword2(String password2) {
				this.password2 = password2;
			}
			public UserRegisterDTO(Long id) {
				super();
				this.id = id;
			}
			
			
			public UserRegisterDTO(String email, String password1) {
				super();
				this.email = email;
				this.password1 = password1;
			}
			

			public UserRegisterDTO() {
				super();
			}
			
		
			
			// CONTRUSCTOR
			public UserRegisterDTO(String email, String firstName, String lastName, String country, String password1, String password2) {
							super();
							this.email = email;
							this.firstName = firstName;
							this.lastName = lastName;
							this.country = country;
							this.password1 = password1;
							this.password2 = password2;
					
						}
			
			
			public int checkPasswd(){
				
				boolean upper = false;
				boolean lower = false;
				boolean digit = false;
				
				if(this.password1.equalsIgnoreCase(this.password2)) {
					
					if(this.password1.length() < 8) {
						return 4;
					}
					
					
					
					for(char c: this.password1.toCharArray()) {
						if(Character.isLowerCase(c)) {
							lower = true;
						}else if(Character.isUpperCase(c)) {
							upper = true;
						}else if(Character.isDigit(c)) {
							digit = true;
						}
						
						if(lower && upper && digit) {
							return 0;			
						}
					}
					
					if(lower == false) {
						return 1;
					}else if(upper == false){
						return 2;
					}else {
						return 3;
					}
					
				}else {
					return 5;
				}
			}
}
