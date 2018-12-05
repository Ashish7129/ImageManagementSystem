package com.nagarro.imagemanagement.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * Image Class Model
 * 
 * @author ashishaggarwal
 *
 */
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	
	@Column(nullable=false)
	private String imageName;
	
	@Column(nullable=false)
	private double imageSize;
	
	@Column(nullable=false)
	private String imagePath;
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(nullable=false)
	private byte[] imageSource;
	
	@ManyToOne
	private User user;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public double getImageSize() {
		return imageSize;
	}

	public void setImageSize(double d) {
		this.imageSize = d;
	}

	public byte[] getImageSource() {
		return imageSource;
	}

	public void setImageSource(byte[] imageSource) {
		this.imageSource = imageSource;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imageName=" + imageName + ", imageSize=" + imageSize + ", imagePath="
				+ imagePath + ", imageSource=" + Arrays.toString(imageSource) + ", user=" + user + "]";
	}

}
