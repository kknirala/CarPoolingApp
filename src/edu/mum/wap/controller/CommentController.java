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

import com.google.gson.Gson;

import edu.mum.wap.model.Comments;
import edu.mum.wap.model.Likes;
import edu.mum.wap.model.mapper.CommentMapper;
import edu.mum.wap.model.mapper.LikePostMapper;
import edu.mum.wap.service.ICommentService;
import edu.mum.wap.service.helper.CommentServiceHelper;
import edu.mum.wap.service.impl.CommentServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;

public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int commentId = Integer.valueOf(request.getPathInfo().substring(1));
		ICommentService commentService = new CommentServiceImpl();
		Comments comment = null;
		try {
			comment = commentService.findComment(commentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(comment);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Comments comment = null;
		BufferedReader reader = request.getReader();
		String comingresult = reader.lines().collect(Collectors.joining(System.lineSeparator()));
		Gson gson = new Gson();
		CommentMapper commentMapper = new CommentMapper();

		commentMapper = gson.fromJson(comingresult, CommentMapper.class);
		try {
			comment = CommentServiceHelper.getCommentFrommapper(commentMapper);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ICommentService commentService = new CommentServiceImpl();
		try {
			commentService.addNewComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(comment);
		PrintWriter out = response.getWriter();
		out.write(responseText);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		int commentId = Integer.valueOf(req.getPathInfo().substring(1));
		Gson gson = new Gson();
		Comments comment = gson.fromJson(reader, Comments.class);
		ICommentService commentService = new CommentServiceImpl();
		Comments oldComment = null;
		try {
			oldComment = commentService.findComment(commentId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (oldComment != null) {
			oldComment.setComment(comment.getComment());
			try {
				commentService.updateComment(comment);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				commentService.addNewComment(comment);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String responseText = CarPoolingMarshaller.getJsonFromObject(comment);
		PrintWriter out = resp.getWriter();
		out.write(responseText);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int commentId = Integer.valueOf(req.getPathInfo().substring(1));
		ICommentService commentService = new CommentServiceImpl();
		try {
			commentService.deleteComment(commentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.write("{deleted: true}");

	}
}
