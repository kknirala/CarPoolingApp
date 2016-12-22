package edu.mum.wap.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.mum.wap.model.Users;
import edu.mum.wap.model.mapper.UserMapper;
import edu.mum.wap.service.IUserService;
import edu.mum.wap.service.helper.UserServiceHelper;
import edu.mum.wap.service.impl.UserServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 112335355L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.valueOf(request.getPathInfo().substring(1));
		IUserService userService = new UserServiceImpl();
		Users user = null;
		try {
			user = userService.findUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(user);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.valueOf(req.getPathInfo().substring(1));
		IUserService userService = new UserServiceImpl();
		try {
			userService.deleteUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.write("{deleted: true}");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		String comingresult = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		Gson gson = new Gson();
		UserMapper usermapper = new UserMapper();
		usermapper = gson.fromJson(comingresult, UserMapper.class);
		IUserService userService = new UserServiceImpl();
		Users user = null;
		try {
			user = UserServiceHelper.getPostFrommapper(usermapper);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//add the user
		try {
			userService.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		String comingresult = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		Gson gson = new Gson();
		UserMapper usermapper = new UserMapper();
		Users user = null;
		Users newUser = null;
		System.out.println(comingresult);
		usermapper= gson.fromJson(comingresult, UserMapper.class);
		IUserService userService = new UserServiceImpl();
		try {
			user = UserServiceHelper.getPostFrommapper(usermapper);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			newUser = userService.addNewUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//create an httpsession for this user
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("userId", newUser.getUserId());
		httpSession.setAttribute("fullname", newUser.getFullName());

		resp.sendRedirect("index.jsp");
		/*String responseText = CarPoolingMarshaller.getJsonFromObject(user);
		PrintWriter out = resp.getWriter();
		out.write(responseText);*/
		
	}
}
