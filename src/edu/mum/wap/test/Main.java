package edu.mum.wap.test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import edu.mum.wap.model.Comments;
import edu.mum.wap.model.Likes;
import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.service.impl.CommentServiceImpl;
import edu.mum.wap.service.impl.LikeServiceImpl;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.service.impl.UserServiceImpl;

public class Main {
	public static void main(String[] args) throws SQLException {
//		System.out.println(null == null);
		Users user = new Users(10, "yishagerew", "male", "Iowa", "Fairfield", "1000 N", "52557", LocalDate.now(), "ylulie@Gamil.com", "12345", LocalDate.now(), LocalDate.now());
//		user.setEmail("yishagerew.yitaih@unit.it");
			//new UserServiceImpl().addNewUser(user);
//		Date date = Date.valueOf(LocalDate.now());
//		Instant instant = Instant.ofEpochMilli(date.getTime());
//		System.out.println(instant);
//		System.out.println(LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
//        .toLocalDate());
//		System.out.println(new UserServiceImpl().findUser(10));
//		System.out.println(new UserServiceImpl().updateUser(user));
		//new UserServiceImpl().deleteUser(10);
		//add new post
	Posts post = new Posts(12, user, "hello world", "needDrive", LocalDate.now(), LocalDate.now());
//		new PostServiceImpl().addNewPost(post);
		//update post
		//post.setPostText("I dont think so");
		
		//new PostServiceImpl().updatePost(post);
		//find a post
		//System.out.println(new PostServiceImpl().findPost(12));
		//delete a post
		
		//new PostServiceImpl().deletePost(12);
//		Likes like = new Likes(25, user, post, LocalDate.now(), LocalDate.now());
//		new LikeServiceImpl().addNewLike(like);
		//find like by post id
//		List<Likes> likes = new LikeServiceImpl().findLikeByPostId(12);
//		likes.forEach(e -> System.out.println(e));
		//new LikeServiceImpl().deleteLike(25);
		
	Comments comment = new Comments(23, user, post, "Nice post", LocalDate.now(), LocalDate.now());
//		new CommentServiceImpl().addNewComment(comment);
		
//		System.out.println(new CommentServiceImpl().findComment(23));
	
//		System.out.println(new CommentServiceImpl().findCommentsByPostId(12));
//	comment.setComment("COmment changed");
//	System.out.println(new CommentServiceImpl().updateComment(comment));
	new CommentServiceImpl().deleteComment(23);
	}
}
