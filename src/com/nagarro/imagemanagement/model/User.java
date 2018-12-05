package com.nagarro.imagemanagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * User Model Class
 * @author ashishaggarwal
 *
 */
@Entity
public class User {
	@Id
	private String username;
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Image> images;

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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Welcome "  + username +"";
	}
}
