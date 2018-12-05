package com.nagarro.imagemanagement.dao;

import java.util.List;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
/**
 * This is a Image DAO Interface which contains unimplemented methods
 * @author ashishaggarwal
 *
 */
public interface ImageDao {
	/**
	 * 
	 * @param image
	 */
	 void updateImage(int imageId , Image image);
	 /**
	  * 
	  * @param image
	  */
	 void saveImage(Image image);
	 /**
	  * 
	  * @param image
	  */
	 void deleteImage(Image image);
	 /**
	  * 
	  * @param user
	  * @return
	  */
	 List<Image> getImagesByUser(User user);
	 /**
	  * 
	  * @param imageId
	  * @return
	  */
	 Image getImageById(int imageId); 
	 /**
	  * 
	  * @param user
	  * @return
	  */
	double getTotalSizeOfUserImages(User user);
}
