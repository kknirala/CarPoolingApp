package edu.mum.wap.service.helper;

import java.sql.SQLException;
import java.util.Date;

import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.model.mapper.PostMapper;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.service.impl.UserServiceImpl;

public class PostServiceHelper {
	public static Posts getPostFrommapper(PostMapper mapper) throws SQLException {

		Users user = new UserServiceImpl().findUser(mapper.getUserId());
		int postId = new PostServiceImpl().getMaxId();
		System.out.println("Post type here is: "+ mapper.getPostType());
		System.out.println("Post type here is: "+ mapper.getComment());

		//add 1 in the post
		Posts post = new Posts(postId + 1, user, mapper.getComment(), mapper.getPostType(), new Date(), new Date());
		return post;

	}
}
