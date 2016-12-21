package edu.mum.wap.service.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Likes;
import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.service.ILikeService;
import edu.mum.wap.service.IPostService;

public class LikeServiceImpl implements ILikeService {

	
	PreparedStatement ps;
	
	@Override
	public Likes addNewLike(Likes like) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement(
				"insert into likes (likeid, userid, postid, datecreated, dateupdated) values(?,?,?,?,?)");
		ps.setInt(1, like.getLikeId());
		ps.setInt(2, like.getUser().getUserId());
		ps.setInt(3, like.getPost().getPostId());
		ps.setDate(4, Date.valueOf(LocalDate.now()) );
		ps.setDate(5, Date.valueOf(LocalDate.now()));
		ps.execute();
		return like;
	}

	@Override
	public void deleteLike(int likeId) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("delete from likes where likeid = ?");
		ps.setInt(1, likeId);
		ps.executeUpdate();
		
	}

	@Override
	public List<Likes> findLikeByPostId(int postId) throws SQLException {
		IPostService postservice = new PostServiceImpl();
		List<Likes> postLikes = new ArrayList<Likes>();
		Posts post = postservice.findPost(postId);
		ps = DBConnection.getConnection().conn.prepareStatement("select * from likes where postid = ?");
		ps.setInt(1, post.getPostId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			postLikes.add(new Likes(rs.getInt(1), post.getUser(), post, rs.getDate(4), rs.getDate(5)));
		}
		return postLikes;
	}

	@Override
	public Likes findLikeById(int likeId) throws SQLException {
		
		ps = DBConnection.getConnection().conn.prepareStatement("select * from likes where likeid = ?");
		ps.setInt(1, likeId);
		ResultSet rs = ps.executeQuery();
		Likes like = null;
		Users user = null;
		Posts post = null;
		if (rs.next()) {
			// lazy load dependencies, omitted here
			user = new UserServiceImpl().findUser(rs.getInt(2));
			post = new PostServiceImpl().findPost(rs.getInt(3));
			like = new Likes(rs.getInt(1), user, post, rs.getDate(4), rs.getDate(5));
		}
		return like;
	}

	@Override
	public int getMaxId() throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("select max(likeid) from likes");
		ResultSet rs = ps.executeQuery();
		int currentMaxindex = 0;
		if(rs.next()){
			currentMaxindex = rs.getInt(1);
			System.out.println("current max value is: "+rs.getInt(1));
		}
		return currentMaxindex;
	}

	@Override
	public int findLikeByUserIdAndPostId(int userId, int postId) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("select count(likeid), likeid from likes where userid=? and postid=?");
		ps.setInt(1, userId);
		ps.setInt(2, postId);
		ResultSet rs = ps.executeQuery();
		int likeStatus = 0;
		if(rs.next()){
			likeStatus = rs.getInt(1);
			System.out.println("Like status is: "+rs.getInt(1));
		}
		if(likeStatus == 1){
			likeStatus = rs.getInt(2);
		}
		return likeStatus;
	}	
}
