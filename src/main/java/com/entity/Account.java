package com.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
//	@Column(name = "Username")
	@NotBlank
	String username;
//	@Column(name = "Passwrod")
	@NotBlank
	String password;
//	@Column(name = "Fullname")
	String fullname;
//	@Column(name = "Email")
	String email;
//	@Column(name = "Photo")
	String photo = "";
//	@Column(name = "Activated")
	boolean activated = true;
//	@Column(name = "Role")
	boolean Role = false;
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public boolean isRole() {
		return Role;
	}
	public void setRole(boolean role) {
		Role = role;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
