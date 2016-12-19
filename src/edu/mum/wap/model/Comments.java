package edu.mum.wap.model;

import java.time.LocalDate;

public class Comments {
	private int commentId;
	private Users user;
	private Posts post;
	private String comment;
	private LocalDate dateCreated;
	private LocalDate dateUpdated;

	
	
	public Comments(int commentId, Users user, Posts post, String comment, LocalDate dateCreated,
			LocalDate dateUpdated) {
		this.commentId = commentId;
		this.user = user;
		this.post = post;
		this.comment = comment;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	//only for test
	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", post=" + post + ", comment=" + comment + ", dateCreated="
				+ dateCreated + ", dateUpdated=" + dateUpdated + "]";
	}
	
	

}
