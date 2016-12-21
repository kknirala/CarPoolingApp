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

import edu.mum.wap.model.LikePostMapper;
import edu.mum.wap.model.Likes;
import edu.mum.wap.service.ILikeService;
import edu.mum.wap.service.helper.LikeServiceHelper;
import edu.mum.wap.service.impl.LikeServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;

public class LikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		System.out.println(reader);
		Gson gson = new Gson();
		LikePostMapper likemapper = gson.fromJson(reader, LikePostMapper.class);
		Likes like = null;
		try {
			like = LikeServiceHelper.getLikeFrommapper(likemapper);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ILikeService likeService = new LikeServiceImpl();
		try {
			
			likeService.addNewLike(like);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(like);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int likeId = Integer.valueOf(req.getPathInfo().substring(1));
		ILikeService likeService = new LikeServiceImpl();
		Likes like = null;
		try {
			like = likeService.findLikeById(likeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(like);
		resp.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = resp.getWriter();
		out.write(responseText);
	}

}
