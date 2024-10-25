package com.irojas.demojwt.Auth.Model;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="temporary_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class TemporaryUser {
	
	
	// ATRIBUTES
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private Long id;
			
			@Column(name = "email")
			private String email;
			
			@Column(nullable = false)
		    private String lastname;
		    
			private String firstname;
		    
			private String country;
			
			@Column(name = "password")
			private String password;
			
			@Column(name="token")
			private String token;
			
			@Column(name = "is_enabled")
		    private boolean isEnabled;

		    @Column(name = "account_No_Expired")
		    private boolean accountNoExpired;

		    @Column(name = "account_No_Locked")
		    private boolean accountNoLocked;

		    @Column(name = "credential_No_Expired")
		    private boolean credentialNoExpired;
			
			
		    @Enumerated(EnumType.STRING) 
		    List<Role> roles;
		    
		    
		   
		    
			
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

			public String getLastname() {
				return lastname;
			}

			public void setLastname(String lastname) {
				this.lastname = lastname;
			}

			public String getFirstname() {
				return firstname;
			}

			public void setFirstname(String firstname) {
				this.firstname = firstname;
			}

			public String getCountry() {
				return country;
			}

			public void setCountry(String country) {
				this.country = country;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public boolean isEnabled() {
				return isEnabled;
			}

			public void setEnabled(boolean isEnabled) {
				this.isEnabled = isEnabled;
			}

			public boolean isAccountNoExpired() {
				return accountNoExpired;
			}

			public void setAccountNoExpired(boolean accountNoExpired) {
				this.accountNoExpired = accountNoExpired;
			}

			public boolean isAccountNoLocked() {
				return accountNoLocked;
			}

			public void setAccountNoLocked(boolean accountNoLocked) {
				this.accountNoLocked = accountNoLocked;
			}

			public boolean isCredentialNoExpired() {
				return credentialNoExpired;
			}

			public void setCredentialNoExpired(boolean credentialNoExpired) {
				this.credentialNoExpired = credentialNoExpired;
			}

			public List<Role> getRoles() {
				return roles;
			}

			public void setRoles(List<Role> roles) {
				this.roles = roles;
			}

			public String getToken() {
				return token;
			}

			public void setToken(String token) {
				this.token = token;
			}
			
			// CONSTRUCTOR
			
			

			public TemporaryUser() {
				super();
			}

			public TemporaryUser(Long id, String email, String lastname, String firstname, String country,
					String password, String token, List<Role> roles) {
				super();
				this.id = id;
				this.email = email;
				this.lastname = lastname;
				this.firstname = firstname;
				this.country = country;
				this.password = password;
				this.token = token;
				this.roles = roles;
			}
			
			
			public TemporaryUser(String email, String lastname, String firstname, String country,
					String password, String token, List<Role> roles) {
				super();
				this.email = email;
				this.lastname = lastname;
				this.firstname = firstname;
				this.country = country;
				this.password = password;
				this.token = token;
				this.roles = roles;
			}

			public TemporaryUser(String email, String lastname, String firstname, String country,
					String password, List<Role> roles) {
				super();
				this.email = email;
				this.lastname = lastname;
				this.firstname = firstname;
				this.country = country;
				this.password = password;
				this.roles = roles;
			}
			
			public TemporaryUser(String email, String token) {
				super();
				this.email = email;
				this.token = token;
			}

	
	
}
