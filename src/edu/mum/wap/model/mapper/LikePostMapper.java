package edu.mum.wap.model.mapper;

public class LikePostMapper {
	private int postId;
	private int userId;
	
	
	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "LikePostMapper [postId=" + postId + ", userId=" + userId + "]";
	}
	
	
}
