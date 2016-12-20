package edu.mum.wap.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.mum.wap.service.ILoginService;
import edu.mum.wap.service.impl.LoginServiceImpl;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isUserAuthenticated = false;
		ILoginService loginService = new LoginServiceImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberme = request.getParameter("rememberme");

		try {
			isUserAuthenticated = loginService.IsUserRegistered(username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (isUserAuthenticated) {
			if (request.getParameter("rememberme") != null) {
				Cookie usernameCookie = new Cookie("user", username.trim());
				Cookie passwordCookie = new Cookie("pass", password.trim());
				Cookie rememberCookie = new Cookie("rem", rememberme.trim());
				usernameCookie.setMaxAge(60 * 60 * 24 * 15);// 15 days
				passwordCookie.setMaxAge(60 * 60 * 24 * 15);
				rememberCookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
				response.addCookie(rememberCookie);
			}

			// set session, and session will expire in 15 minutes
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("loggedinuser", username.trim());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/profile.jsp");
			requestDispatcher.forward(request, response);
		} else {
			System.out.println("Authentication failure.");
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
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(req, resp);
	}

}
