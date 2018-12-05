package com.nagarro.imagemanagement.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nagarro.imagemanagement.model.Image;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.service.ImageService;
import com.nagarro.imagemanagement.service.UserService;

/**
 * Servlet implementation class LoginServlet
 * Its helps to login the user and redirects to the Welcome page after authentication
 *  @author ashishaggarwal 
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger log = Logger.getLogger(LoginServlet.class.getName());
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		ImageService imageService = new ImageService();
		UserService userService =  new UserService();
		final HttpSession session = request.getSession();
		
		try {
		if (userService.getUser(user)) {
			List<Image> imageList = imageService.getImagesByUser(user);
			double totalSize = imageService.getTotalSizeOfUserImages(user);
			session.setAttribute("totalSize", totalSize);
			session.setAttribute("currentUser", (Serializable) user);
			session.setAttribute("imageList", (Serializable) imageList);
			response.sendRedirect("Welcome.jsp");
		} else {
			response.sendRedirect("LoginPage.jsp?UserNotFound=true");
		}
		}catch(IllegalStateException | IOException e) {
			log.error(e.getMessage());
			session.invalidate();
		}
	}

}
