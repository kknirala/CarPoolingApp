package edu.mum.wap.service.helper;

import java.sql.SQLException;
import java.util.Date;

import edu.mum.wap.model.Comments;
import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.model.mapper.CommentMapper;
import edu.mum.wap.service.impl.CommentServiceImpl;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.service.impl.UserServiceImpl;

public class CommentServiceHelper {

	public static Comments getCommentFrommapper(CommentMapper mapper) throws SQLException {
		Posts post = new PostServiceImpl().findPost(mapper.getPostId());
		Users user = new UserServiceImpl().findUser(mapper.getUserId());

		int maxId = new CommentServiceImpl().getMaxId();
		Comments comment = new Comments(maxId + 1, user, post, mapper.getComment(), new Date(), new Date());
		return comment;
	}
}
