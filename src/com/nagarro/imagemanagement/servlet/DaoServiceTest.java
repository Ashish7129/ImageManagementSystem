package com.nagarro.imagemanagement.servlet;

import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.dao.UserDao;
import com.nagarro.imagemanagement.daoimplementation.ImageDaoImpl;
import com.nagarro.imagemanagement.daoimplementation.UserDaoImpl;
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author ashishaggarwal
 *
 */
/**
 * @throws IOException
 */
class DaoServiceTest {
	//private static Session session = HibernateUtil.getSessionFactory().openSession();
	private static Image image = null;
	private static User user = new User();
	static ImageDao imageDao = new ImageDaoImpl();
	static UserDao userDao = new UserDaoImpl();
	static List<Image> imageList = null;

	@BeforeEach
	public void init() {
		user.setUsername("abc");
		user.setPassword("abc");
		image = imageDao.getImageById(122);
		imageList = imageDao.getImagesByUser(user);
		System.out.println("@beforeAll");
	}
	
	@Test
	public void GetUserSuccess() {
		assertTrue(userDao.getUser(user));
	}
	
	@Test
	public void GetUserFailure() {
		User failUser = new User(); 
		failUser.setUsername("ashish");
		failUser.setPassword("12345678");
		assertFalse(userDao.getUser(failUser));
	}
	@Test 
	public void GetImageByIdSuccess() {
		assertEquals(image,imageDao.getImageById(122));
	}
	
	@Test 
	public void GetImageByIdFailure() {
		assertNotNull(imageDao.getImageById(98));
		assertEquals(image,imageDao.getImageById(98));
	}
	
	@Test
	public void GetImagesByUserSuccess() {
		assertNotNull(imageDao.getImagesByUser(user));
		assertEquals(imageDao.getImagesByUser(user).size(), imageList.size());
	}
	@Test
	public void GetImagesByUserFailure() {
		assertNull(imageDao.getImagesByUser(user));
		assertNotEquals(imageDao.getImagesByUser(user).size(), imageList.size());
	}
//	@Test
//	public void testDeleteImage() {
//		imageDao.deleteImage(image);
//		List<Image> newImageList = imageDao.getImagesByUser(user);
//		assertEquals(imageList.size()-1, newImageList.size(), "success");
//		System.out.println("@testDeleteImage");
//	}
//	@Test
//	public void testUpdateImage() {
//		Image updateImage = new Image();
//		image.setImageName("new1 name");
//		updateImage = image;
//		imageDao.updateImage(119, updateImage);
//		List<Image> newImageList = imageDao.getImagesByUser(user);
//		assertEquals(imageList.size(), newImageList.size(), "success");
//		System.out.println("@testUpdateImage");
//	}
//	@Test
//	public void testSaveImage() throws IOException {
//		Image newImage = new Image();
//		newImage.setImageName("my name");
//		newImage.setImagePath("85CCC5D9.PNG");
//		newImage.setImageSize(809474.0);
//		newImage.setUser(user);
//		newImage.setImageSource(getByte(Constants.PATH + File.separator + "85CCC5D9.PNG"));
//		imageDao.saveImage(newImage);
//		List<Image> newImageList = imageDao.getImagesByUser(user);
//		assertNotNull(imageList);
//		assertEquals(imageList.size()+1, newImageList.size(), "success");
//	}

	@AfterEach
	public void destroy() {
		System.out.println("@afterAll");
		//session.close();
	}

	private byte[] getByte(String path) {
		byte[] getBytes = {};
		try {
			File file = new File(path);
			getBytes = new byte[(int) file.length()];
			InputStream is = new FileInputStream(file);
			is.read(getBytes);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getBytes;
	}
}
