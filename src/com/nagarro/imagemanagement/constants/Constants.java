package com.nagarro.imagemanagement.constants;

/**
 * Constants class contains all the constants which we uses in the application
 * 
 * @author ashishaggarwal
 *
 */
public class Constants {
	/*
	 * valid image size
	 */
	public static final long VALID_IMAGE_SIZE = (long) (1024 * 1024); // valid size
	/*
	 * valid total size of images uploaded by user
	 */
	public static final long VALID_TOTAL_USER_IMAGES_SIZE = (long) (1024 * 1024 * 10);
	/*
	 * Path to store the images
	 */
	public static final String PATH = "C:\\Users\\ashishaggarwal\\Desktop\\Assignment4\\ImageManagementSystem\\WebContent\\images";
	public static final String USER_NOT_MATCH = "Username and Password does not match";
	public static final String IMAGE_DETAILS_NULL = "Please enter the image details! Enter Again";
	public static final String INVALID_IMAGE_TYPE = "Invalid Image type! Enter Again";
	public static final String SIZE_LIMIT_EXCEED = "Size Limit Exceeded! Enter Again (Under 1MB size)";
	public static final String TOTALSIZE_LIMIT_EXCEED = "Maximum size limit Exceeded! Enter again";

	private Constants() {
		super();
	}
}
