package edu.mum.wap.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.wap.model.Users;
import edu.mum.wap.service.ILoginService;
import edu.mum.wap.service.IUserService;
import edu.mum.wap.service.impl.LoginServiceImpl;
import edu.mum.wap.service.impl.UserServiceImpl;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isUserAuthenticated = false;
		ILoginService loginService = new LoginServiceImpl();
		IUserService  userService = new UserServiceImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberme = request.getParameter("rememberme");
		Cookie[]cookies = request.getCookies();

		try {
			isUserAuthenticated = loginService.IsUserRegistered(username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isUserAuthenticated) {
			int userId;
			Users user = null;
			try {
				user = userService.findUserByUsername(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ((request.getParameter("rememberme") != null) &&(cookies.length < 4)) {
				Cookie streetCookie = new Cookie("street", URLEncoder.encode(user.getStreet(), "UTF-8"));
				Cookie fullnameCookie = new Cookie("fullname", URLEncoder.encode(user.getFullName(),"UTF-8"));
				Cookie rememberCookie = new Cookie("rem", URLEncoder.encode(rememberme.trim(),"UTF-8") );
				Cookie userIdCookie = new Cookie("userId", URLEncoder.encode(String.valueOf(user.getUserId()),"UTF-8"));
				userIdCookie.setMaxAge(60 * 60 * 24 * 15);// 15 days
				streetCookie.setMaxAge(60 * 60 * 24 * 15);
				rememberCookie.setMaxAge(60 * 60 * 24 * 15);
				fullnameCookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(userIdCookie);
				response.addCookie(streetCookie);
				response.addCookie(fullnameCookie);
				response.addCookie(rememberCookie);
			}
			
			// set session, and session will expire in 15 minutes
			HttpSession httpSession = request.getSession();
			response.sendRedirect("index.jsp");
			httpSession.setAttribute("userId", user.getUserId());
			httpSession.setAttribute("fullname", user.getFullName());
			httpSession.setAttribute("city", user.getCity());
			/* RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/profile.jsp");
			requestDispatcher.forward(request, response);*/
		} else {
			request.setAttribute("msg", "Authentication failure.");
			response.sendRedirect("login.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie usernameCookie = new Cookie("user", null);
		Cookie passwordCookie = new Cookie("pass", null);
		Cookie rememberCookie = new Cookie("rem", null);
		usernameCookie.setMaxAge(0);
		passwordCookie.setMaxAge(0);
		rememberCookie.setMaxAge(0);
		resp.addCookie(usernameCookie);
		resp.addCookie(passwordCookie);
		resp.addCookie(rememberCookie);
		HttpSession httpSession = req.getSession();
		httpSession.invalidate();
		req.setAttribute("msg", "You have successfully logged out.");
		/*RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(req, resp);*/
		resp.sendRedirect("index.jsp");
	}

}
