package edu.mum.wap.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.mum.wap.model.Users;
import edu.mum.wap.service.IUserService;
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
		Gson gson = new Gson();
		Users user = gson.fromJson(reader, Users.class);
		IUserService userService = new UserServiceImpl();
		try {
			userService.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();
		Users user = gson.fromJson(reader, Users.class);
		IUserService userService = new UserServiceImpl();
		try {
			userService.addNewUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(user);
		PrintWriter out = resp.getWriter();
		out.write(responseText);
	}
}
