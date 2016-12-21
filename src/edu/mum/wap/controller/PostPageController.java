package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.Posts;
import edu.mum.wap.service.IPostService;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;

public class PostPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = Integer.valueOf(request.getPathInfo().substring(1));
		IPostService postService = new PostServiceImpl();
		List<Posts> posts = null;
		try {
			posts = postService.getPostPerPage(pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		String responseText = CarPoolingMarshaller.getJsonFromObject(posts);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

}
