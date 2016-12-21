package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.Comments;
import edu.mum.wap.service.ICommentService;
import edu.mum.wap.service.impl.CommentServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;

public class PostCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.valueOf(request.getPathInfo().substring(1));
		ICommentService commentService = new CommentServiceImpl();
		List<Comments> comments = null;
		try {
			comments = commentService.findCommentsByPostId(postId);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		String responseText = CarPoolingMarshaller.getJsonFromObject(comments);
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

}
