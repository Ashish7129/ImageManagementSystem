package com.nagarro.imagemanagement.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.nagarro.imagemanagement.constants.Constants;
import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.daoimplementation.ImageDaoImpl;
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;

/**
 * ImageService class helps the servlet classes to communicate with the Dao
 * implementation methods
 * 
 * @author ashishaggarwal
 *
 */
public class ImageService {
	Logger log = Logger.getLogger(ImageService.class.getName());
	ImageDao imageDao = new ImageDaoImpl();

	public Image readFromFormData(Part filePart, String imageName, User currentUser) throws FileNotFoundException {
		Image image = new Image();
		String path = Constants.PATH + File.separator + filePart.getSubmittedFileName().replaceAll(" ", "");
		InputStream inputStream;
		try (OutputStream out = new FileOutputStream(new File(path))) {
			inputStream = filePart.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[(int) filePart.getSize()];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);

			}
			image.setImageName(imageName);
			image.setImageSize((float) filePart.getSize());
			image.setUser(currentUser);
			image.setImagePath(filePart.getSubmittedFileName().replaceAll(" ", ""));
			image.setImageSource(bytes);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return image;
	}

	public Image getImageById(int imageId) {
		Image getImage = null;
		getImage = imageDao.getImageById(imageId);
		return getImage;
	}

	public void deleteImage(Image image) {
		imageDao.deleteImage(image);

	}

	public List<Image> getImagesByUser(User currentUser) {
		return imageDao.getImagesByUser(currentUser);
	}

	public double getTotalSizeOfUserImages(User currentUser) {
		return imageDao.getTotalSizeOfUserImages(currentUser);
	}

	public void saveImage(Image loadedImage) {
		imageDao.saveImage(loadedImage);

	}

	public void updateImage(int imageId, Image newImage) {
		imageDao.updateImage(imageId, newImage);
	}
}
