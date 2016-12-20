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

import edu.mum.wap.model.Posts;
import edu.mum.wap.service.IPostService;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;


public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.valueOf(request.getPathInfo().substring(1));
		IPostService postService = new PostServiceImpl();
		Posts post = null;
		try {
			post = postService.findPost(postId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(post);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		Posts post = gson.fromJson(reader, Posts.class);
		IPostService postService = new PostServiceImpl();
		try {
			postService.addNewPost(post);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(post);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();
		Posts post = gson.fromJson(reader, Posts.class);
		IPostService postService = new PostServiceImpl();
		try {
			postService.updatePost(post);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(post);
		PrintWriter out = resp.getWriter();
		out.write(responseText);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int postId = Integer.valueOf(req.getPathInfo().substring(1));
		IPostService postService = new PostServiceImpl();
		try {
			postService.deletePost(postId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.write("{deleted: true}");
	}
	
	
	
	

}
