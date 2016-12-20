package edu.mum.wap.service.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Comments;
import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.service.ICommentService;
import edu.mum.wap.service.IPostService;

public class CommentServiceImpl implements ICommentService {

	PreparedStatement ps;

	@Override
	public Comments addNewComment(Comments comment) throws SQLException {

		ps = DBConnection.getConnection().conn.prepareStatement(
				"insert into Comments (commentId, userid, postid, comment, datecreated, dateupdated) values(?,?,?,?,?,?)");
		ps.setInt(1, comment.getCommentId());
		ps.setInt(2, comment.getUser().getUserId());
		ps.setInt(3, comment.getPost().getPostId());
		ps.setString(4, comment.getComment());
		ps.setDate(5, Date.valueOf(LocalDate.now()));
		ps.setDate(6, Date.valueOf(LocalDate.now()));
		ps.execute();
		return comment;
	}

	@Override
	public void deleteComment(int commentId) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("delete from Comments where commentid = ?");
		ps.setInt(1, commentId);
		ps.executeUpdate();
	}

	@Override
	public Comments updateComment(Comments comment) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("update Comments set comment=? where commentid=?");
		ps.setString(1, comment.getComment());
		ps.setInt(2, comment.getCommentId());
		ps.executeUpdate();
		return comment;
	}

	@Override
	public Comments findComment(int commentId) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("select * from Comments where commentid = ?");
		ps.setInt(1, commentId);
		ResultSet rs = ps.executeQuery();
		Posts post = null;
		Users user = null;
		Comments comment = null;
		if (rs.next()) {
			// lazy load dependencies
			user = new UserServiceImpl().findUser(rs.getInt(2));
			post = new PostServiceImpl().findPost(rs.getInt(3));

			comment = new Comments(rs.getInt(1), user, post, rs.getString(4),
					rs.getDate(5), rs.getDate(6));
			post = new Posts(rs.getInt(1), user, rs.getString(3), rs.getString(4),
					rs.getDate(5), rs.getDate(6));
		}
		return comment;
	}

	@Override
	public List<Comments> findCommentsByPostId(int postid) throws SQLException {
		IPostService postservice = new PostServiceImpl();
		List<Comments> postComments = new ArrayList<Comments>();
		Posts post = postservice.findPost(postid);
		ps = DBConnection.getConnection().conn.prepareStatement("select * from comments where postid = ?");
		ps.setInt(1, post.getPostId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			postComments.add(new Comments(rs.getInt(1), post.getUser(), post, rs.getString(4),
					rs.getDate(5), rs.getDate(6)));
		}
		return postComments;
	}
}
