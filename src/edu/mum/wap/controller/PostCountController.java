package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.service.IPostService;
import edu.mum.wap.service.impl.PostServiceImpl;


public class PostCountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPostService postService = new PostServiceImpl();
		int numberofposts = 0;
		try {
			numberofposts = postService.getNumberOfPosts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.write("{numberofposts:"+ numberofposts+"}");
	}
}
