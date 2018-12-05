package com.nagarro.imagemanagement.daoimplementation;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.utility.HibernateUtil;

@SuppressWarnings("unchecked")
/**
 * This ImagoDaoService class which implements the ImageDao interface's methods
 * @author ashishaggarwal
 *
 */
public class ImageDaoImpl implements ImageDao {
	Logger log = Logger.getLogger(ImageDaoImpl.class.getName());
	Transaction transaction = null;

	/**
	 * update the image
	 */
	public void updateImage(int imageId, final Image image) {
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			Image retrievedImage = session.load(Image.class, imageId);
			retrievedImage.setImageName(image.getImageName());
			if (image.getImageSize() != 0) {
				retrievedImage.setImageSize(image.getImageSize());
				retrievedImage.setImageSource(image.getImageSource());
				retrievedImage.setImagePath(image.getImagePath());
			}
			session.saveOrUpdate(retrievedImage);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			log.error(e.getMessage());
		}
	}

	/**
	 * save the image
	 */
	public void saveImage(final Image image) {
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			session.save(image);
			transaction.commit();
		} catch (HibernateException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Delete the image
	 */
	public void deleteImage(final Image image) {
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			session.delete(image);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			log.error(e.getMessage());
			
		}
	}

	/**
	 * get the images which are uploaded by user
	 */
	public List<Image> getImagesByUser(final User user) {
		List<Image> images = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			final Query query = session.createQuery("from Image i where i.user =:currentUser");
			query.setParameter("currentUser", user);
			images = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			log.error(e.getMessage());
		}
		return images;
	}

	/**
	 * Get the image by imageId
	 */
	public Image getImageById(final int imageId) {
		Image getImage = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			getImage = session.get(Image.class, imageId);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			log.error(e.getMessage());
		}
		return getImage;
	}

	/**
	 * get the total size of the images for particular user
	 */
	public double getTotalSizeOfUserImages(User user) {
		double totalSize = 0;
		try (Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("select sum(i.imageSize) from Image i WHERE i.user =:currentUser");
			query.setParameter("currentUser", user);
			totalSize = (double) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			log.error(e.getMessage());
		}
		return totalSize;
	}

	public ImageDaoImpl() {
		super();
	}

}
