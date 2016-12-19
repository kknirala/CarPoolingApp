package edu.mum.wap.model;

import java.time.LocalDate;

public class Posts {
	private int postId;
	private Users user;
	private String postText;
	/**
	 * 	1 - indicate asking for a ride 
	 * 	0 - indicate a post for giving a ride
	 */
	private String postType;
	private LocalDate dateCreated;
	private LocalDate dateUpdated;

	public Posts(int postId, Users user, String postText, String postType, LocalDate dateCreated,
			LocalDate dateUpdated) {
		this.postId = postId;
		this.user = user;
		this.postText = postText;
		this.postType = postType;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}
	

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDate dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posts other = (Posts) obj;
		if (postId != other.postId)
			return false;
		return true;
	}

	//only for test
	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", user=" + user + ", postText=" + postText + ", postType=" + postType + "]";
	}
	

	

}
