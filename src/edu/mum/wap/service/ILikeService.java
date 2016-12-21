package edu.mum.wap.service;

import java.sql.SQLException;
import java.util.List;

import edu.mum.wap.model.Likes;

public interface ILikeService {
	public Likes addNewLike(Likes like) throws SQLException;
	public  void deleteLike(int likeId) throws SQLException;
	public List<Likes> findLikeByPostId(int postId) throws SQLException;
	public Likes findLikeById(int likeId) throws SQLException;
	public int getMaxId() throws SQLException;
}
