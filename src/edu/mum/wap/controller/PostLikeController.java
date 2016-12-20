package edu.mum.wap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.wap.model.Likes;
import edu.mum.wap.service.ILikeService;
import edu.mum.wap.service.impl.LikeServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;


public class PostLikeController extends HttpServlet {
	private static final long serialVersionUID = 15869856L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.valueOf(request.getPathInfo().substring(1));
		ILikeService likeService = new LikeServiceImpl();
		List<Likes> likes = null;
		try {
			likes = likeService.findLikeByPostId(postId);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		String responseText = CarPoolingMarshaller.getJsonFromObject(likes);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}
       
    
}
