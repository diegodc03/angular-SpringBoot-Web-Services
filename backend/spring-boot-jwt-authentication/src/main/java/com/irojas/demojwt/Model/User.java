package com.irojas.demojwt.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.irojas.demojwt.ModelInventary.Product;
import com.irojas.demojwt.ModelInventary.ProductSale;
import com.irojas.demojwt.ModelInventary.SaleList;
import com.irojas.demojwt.workHours.Model.UserMatch;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails {
    
	@Id
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String lastname;
    private String firstname;
    private String country;
    private String password;
    
    @Enumerated(EnumType.STRING) 
    Role role;
    
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
	@JsonIgnoreProperties(value="user")
    private List<Product> products = new ArrayList<>();
    
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnoreProperties(value="user")
    private List<SaleList> sales = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    //para que no coja otra vez los valores en el JSON
    @JsonIgnoreProperties(value = "user")
    private List<UserMatch> userMatch = new ArrayList<>();
    
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	


	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public List<UserMatch> getUserMatch() {
		return userMatch;
	}
	public void setUserMatch(List<UserMatch> userMatch) {
		this.userMatch = userMatch;
	}
	
	
	
	
	public User() {
		super();
	}
	
	public User(String email, String firstname, String lastname, String country) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.country = country;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	
    
    
}
