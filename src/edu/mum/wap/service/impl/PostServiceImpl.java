package edu.mum.wap.service.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.service.IPostService;

public class PostServiceImpl implements IPostService {
	PreparedStatement ps;

	@Override
	public Posts addNewPost(Posts post) throws SQLException {

		ps = DBConnection.getConnection().conn.prepareStatement(
				"insert into Posts (postid, userid, post, posttype, datecreated, dateupdated) values(?,?,?,?,?,?)");
		ps.setInt(1, post.getPostId());
		ps.setInt(2, post.getUser().getUserId());
		ps.setString(3, post.getPostText());
		ps.setString(4, post.getPostType());
		ps.setDate(5, Date.valueOf(LocalDate.now()));
		ps.setDate(6, Date.valueOf(LocalDate.now()));
		ps.execute();
		return post;
	}

	@Override
	public void deletePost(int postId) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("delete from Posts where postid = ?");
		ps.setInt(1, postId);
		ps.executeUpdate();

	}

	@Override
	public Posts updatePost(Posts post) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("update Posts set post=? where postid=?");
		ps.setString(1, post.getPostText());
		ps.setInt(2, post.getPostId());
		ps.executeUpdate();
		return post;
	}

	@Override
	public Posts findPost(int postId) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("select * from posts where postid = ?");
		ps.setInt(1, postId);
		ResultSet rs = ps.executeQuery();
		Posts post = null;
		if (rs.next()) {
			Users user = new UserServiceImpl().findUser(rs.getInt(2));
			post = new Posts(rs.getInt(1), user, rs.getString(3), rs.getString(4),
					rs.getDate(5), rs.getDate(6));
		}
		return post;
	}

}
