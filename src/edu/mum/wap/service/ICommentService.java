package edu.mum.wap.service;

import java.sql.SQLException;

import edu.mum.wap.model.Comments;

public interface ICommentService {
	public Comments addNewComment(Comments comment) throws SQLException;
	public  void deleteComment(int commentId) throws SQLException;
	public Comments updateComment(Comments comment) throws SQLException;
	public Comments findComment(int commentId) throws SQLException;
}
