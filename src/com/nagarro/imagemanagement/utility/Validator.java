package com.nagarro.imagemanagement.utility;

import java.util.Arrays;
import com.nagarro.imagemanagement.constants.*;
import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.daoimplementation.ImageDaoImpl;
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;

import javax.servlet.http.Part;
/**
 * Validator class which validates the image size, total images size or image type
 * @author ashishaggarwal
 *
 */
public class Validator {
	/**
	 * It validates the image size
	 */
	public static boolean validateImageSize(Image image) {
		double imageSize = image.getImageSize();
		return imageSize <= Constants.VALID_IMAGE_SIZE;
	}
	/**
	 * It validates the all images size by particular user
	 */
	public static boolean validateUserTotalImagesSize(Image image, User user) {
		ImageDao imageDAO = new ImageDaoImpl();
		long totalUserImagesSize = (long) (imageDAO.getTotalSizeOfUserImages(user) + image.getImageSize());
		return totalUserImagesSize < Constants.VALID_TOTAL_USER_IMAGES_SIZE;
	}
	/**
	 * It validates the image type
	 */
	public static boolean validateImageType(Part image) {
		return Arrays.asList("image/jpeg", "image/png", "image/jpg").contains(image.getContentType());

	}
	private Validator() {
		super();
	}
}