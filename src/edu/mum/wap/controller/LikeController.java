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

import edu.mum.wap.model.Likes;
import edu.mum.wap.service.ILikeService;
import edu.mum.wap.service.impl.LikeServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;

public class LikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		Likes like = gson.fromJson(reader, Likes.class);
		ILikeService likeService = new LikeServiceImpl();
		try {
			likeService.addNewLike(like);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(like);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int likeId = Integer.valueOf(req.getPathInfo().substring(1));
		ILikeService likeService = new LikeServiceImpl();
		try {
			likeService.deleteLike(likeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.write("{deleted: true}");
	}
}
