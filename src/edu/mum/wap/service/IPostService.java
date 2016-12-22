package edu.mum.wap.service;

import java.sql.SQLException;
import java.util.List;

import edu.mum.wap.model.Posts;

public interface IPostService {
	public Posts addNewPost(Posts post) throws SQLException;
	public  void deletePost(int postId) throws SQLException;
	public Posts updatePost(Posts post) throws SQLException;
	public Posts findPost(int postId) throws SQLException;
	public List<Posts>getRidePostPerPage(int pageSize) throws SQLException;
	public List<Posts>getDrivePostPerPage(int pageSize) throws SQLException;
	public int getMaxId() throws SQLException;

}
