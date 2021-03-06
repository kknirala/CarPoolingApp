package edu.mum.wap.service.helper;

import java.sql.SQLException;
import java.util.Date;

import edu.mum.wap.model.Likes;
import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.model.mapper.LikePostMapper;
import edu.mum.wap.service.impl.LikeServiceImpl;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.service.impl.UserServiceImpl;

public class LikeServiceHelper {
	public static Likes getLikeFrommapper(LikePostMapper mapper) throws SQLException {
		Posts post = new PostServiceImpl().findPost(mapper.getPostId());
		Users user = new UserServiceImpl().findUser(mapper.getUserId());
		int maxId = new LikeServiceImpl().getMaxId();
		Likes like = new Likes(maxId + 1, user, post, new Date(), new Date());
		return like;
	}
}
