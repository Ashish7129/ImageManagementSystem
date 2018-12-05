package com.nagarro.imagemanagement.servlet;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.service.ImageService;

/**
 * Servlet implementation class UpdateImage
 * It helps to carry the particular image to the edit page with the attributes like image name , image file
 *  @author ashishaggarwal
 */
@WebServlet("/EditImage")
public class EditImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	{
		Logger log = Logger.getLogger(EditImage.class.getName());
		ImageService imageService = new ImageService();
		HttpSession httpSession = request.getSession();
		try {
		final int imageId = Integer.parseInt(request.getParameter("editImage"));
		Image image = imageService.getImageById(imageId);
		httpSession.setAttribute("selectedImage", (Serializable) image);
		RequestDispatcher requestDispatch = request.getRequestDispatcher("edit.jsp");
		requestDispatch.forward(request, response);
	}catch (NumberFormatException |ServletException | IllegalStateException | IOException e) {
		log.error(e.getMessage());
	}
		}

}
