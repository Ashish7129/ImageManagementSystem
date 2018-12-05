package com.nagarro.imagemanagement.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.service.ImageService;

/**
 * Servlet implementation class DeleteImage 
 * Its helps to delete the particular image and redirects to welcome page 
 *  @author ashishaggarwal
 */

@WebServlet("/DeleteImage")
public class DeleteImage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Logger log = Logger.getLogger(DeleteImage.class.getName());
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		ImageService imageService = new ImageService();
		List<Image> imageList = null;
		try {
			session.removeAttribute("imageList");
			session.removeAttribute("totalSize");
			int imageId = Integer.parseInt(request.getParameter("deleteImage"));
			
			Image getImage = imageService.getImageById(imageId);
			imageService.deleteImage(getImage);
			imageList = imageService.getImagesByUser(currentUser);
			
			session.setAttribute("imageList", (Serializable)imageList);
			double totalSize = imageService.getTotalSizeOfUserImages(currentUser);
			session.setAttribute("totalSize", totalSize);
			response.sendRedirect("Welcome.jsp");
		} catch (NumberFormatException | IllegalStateException | IOException e) {
			log.error(e.getMessage());
			session.invalidate();
		}
	}

}
