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
 * Servlet implementation class UpdateImage
 * It helps to update the edit attributes of the image
 * @author ashishaggarwal
 */
@WebServlet("/UpdateImage")
@MultipartConfig
public class UpdateImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Logger log = Logger.getLogger(UpdateImage.class.getName());
		ImageService imageService = new ImageService();
		HttpSession httpSession = request.getSession();
		User currentUser = (User) httpSession.getAttribute("currentUser");
		try {
			int imageId = Integer.parseInt(request.getParameter("updateImage"));
			Image newImage = imageService.getImageById(imageId);
			String newImageName = request.getParameter("newImageName");
			Part newImageSrc = request.getPart("newImageSrc");

			if (newImageSrc.getSize() != 0) {
				newImage = imageService.readFromFormData(newImageSrc, newImageName, currentUser);
				if (!Validator.validateImageType(newImageSrc)) {
					response.sendRedirect("edit.jsp?invalidImageType=true");
					return;
				} else if (!Validator.validateImageSize(newImage)) {
					response.sendRedirect("edit.jsp?invalidImageSize=true");
					return;
				} else if (!Validator.validateUserTotalImagesSize(newImage, currentUser)) {
					response.sendRedirect("edit.jsp?invalidTotalImagesSize=true");
					return;
				}

			}
			newImage.setImageName(newImageName);
			newImage.setUser(currentUser);
			imageService.updateImage(imageId, newImage);
			
			//Remove the attributes from session 
			httpSession.removeAttribute("selectedImage");
			List<Image> imageList =imageService.getImagesByUser(currentUser);
			httpSession.setAttribute("imageList", (Serializable) imageList);
			double totalSize = imageService.getTotalSizeOfUserImages(currentUser);
			httpSession.setAttribute("totalSize", totalSize);
			response.sendRedirect("Welcome.jsp?UpdateSuccess=true");
		} catch (NumberFormatException |ServletException | IllegalStateException | IOException e) {
			log.error(e.getMessage());
		}
	}

}
