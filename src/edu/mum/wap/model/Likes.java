package edu.mum.wap.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Likes {
	private int likeId;
	private Users user;
	private Posts post;
	private LocalDate dateCreated;
	private LocalDate dateUpdated;

	
	
	public Likes(int likeId, Users user, Posts post, LocalDate dateCreated,
			LocalDate dateUpdated) {
		this.likeId = likeId;
		this.user = user;
		this.post = post;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Posts getPost() {
		return post;
	}

	public void setPost(Posts post) {
		this.post = post;
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


}
