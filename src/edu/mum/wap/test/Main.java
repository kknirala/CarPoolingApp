package edu.mum.wap.test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.mum.wap.model.Comments;
import edu.mum.wap.model.Likes;
import edu.mum.wap.model.Posts;
import edu.mum.wap.model.Users;
import edu.mum.wap.service.impl.CommentServiceImpl;
import edu.mum.wap.service.impl.LikeServiceImpl;
import edu.mum.wap.service.impl.PostServiceImpl;
import edu.mum.wap.service.impl.UserServiceImpl;
import edu.mum.wap.util.CarPoolingMarshaller;
import edu.mum.wap.util.CarPoolingUnmarshaller;

public class Main {
	public static void main(String[] args) throws SQLException, JsonParseException, JsonMappingException, IOException {
//		System.out.println(null == null);
		Users user = new Users(10, "yishagerew", "male", "Iowa", "Fairfield", "1000 N", "52557", new Date(), "ylulie@Gamil.com", "12345", new Date(), new Date());//		user.setEmail("yishagerew.yitaih@unit.it");
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
	Posts post = new Posts(12, user, "hello world", "needDrive", new Date(), new Date());
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
		
	Comments comment = new Comments(23, user, post, "Nice post",  new Date(), new Date());
		new CommentServiceImpl().addNewComment(comment);
		
//		System.out.println(new CommentServiceImpl().findComment(23));
	
//		System.out.println(new CommentServiceImpl().findCommentsByPostId(12));
//	comment.setComment("COmment changed");
//	System.out.println(new CommentServiceImpl().updateComment(comment));
//	new CommentServiceImpl().deleteComment(23);
//	System.out.println(CarPoolingMarshaller.getJsonFromObject(user));
		//Users user = new Users(10, "yishagerew", "male", "Iowa", "Fairfield", "1000 N", "52557", new Date(), "ylulie@Gamil.com", "12345", new Date(), new Date());
//		Users user = new UserServiceImpl().findUser(10);
//		//FakeUser user = new FakeUser(new Date(), "Lulie");
//		String userString = CarPoolingMarshaller.getJsonFromObject(user);
//		System.out.println(userString);
//		Users user2 = CarPoolingUnmarshaller.getObjectFromJsonString(userString).readValue(userString, Users.class);
//		//Users user2 = (Users) CarPoolingUnmarshaller.getObjectFromJsonString(userString);
//		System.out.println(user2);
		
	}
}
