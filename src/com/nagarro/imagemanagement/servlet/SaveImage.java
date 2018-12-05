package com.nagarro.imagemanagement.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.service.ImageService;
import com.nagarro.imagemanagement.utility.Validator;

/**
 * Servlet implementation class SaveImage It helps to store the image into the
 * database after submission
 * 
 * @author ashishaggarwal
 *
 */
@WebServlet("/saveImage")
@MultipartConfig
public class SaveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Logger log = Logger.getLogger(SaveImage.class.getName());
		HttpSession httpSession = request.getSession();
		ImageService imageService = new ImageService();
		Image loadedImage = null;
		User currentUser = (User) httpSession.getAttribute("currentUser");
		try {
			Part filePart = request.getPart("image");
			String imageName = request.getParameter("imageName");
			if (filePart.getSize() == 0 || imageName == "") {
				response.sendRedirect("Welcome.jsp?IsNullImageDetails=true");
				return;
			} else {
				loadedImage = imageService.readFromFormData(filePart, imageName, currentUser);
				if (!Validator.validateImageType(filePart)) {
					response.sendRedirect("Welcome.jsp?invalidImageType=true");
					return;
				} else if (!Validator.validateImageSize(loadedImage)) {
					response.sendRedirect("Welcome.jsp?invalidImageSize=true");
					return;
				} else if (!Validator.validateUserTotalImagesSize(loadedImage, currentUser)) {
					response.sendRedirect("Welcome.jsp?invalidTotalImagesSize=true");
					return;
				}
				imageService.saveImage(loadedImage);
				List<Image> imageList = imageService.getImagesByUser(currentUser);
				double totalSize = imageService.getTotalSizeOfUserImages(currentUser);
				httpSession.setAttribute("imageList", (Serializable) imageList);
				httpSession.setAttribute("totalSize", totalSize);
			}
			response.sendRedirect("Welcome.jsp?SaveSuccess=true");

		} catch (NumberFormatException | ServletException | IllegalStateException | IOException e) {
			log.error(e.getMessage());
		}
	}
}
