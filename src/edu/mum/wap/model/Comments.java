package edu.mum.wap.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comments")
@XmlAccessorType (XmlAccessType.FIELD)
public class Comments {
	private int commentId;
	private Users user;
	private Posts post;
	private String comment;
	private Date dateCreated;
	private Date dateUpdated;

	public Comments(int commentId, Users user, Posts post, String comment, Date dateCreated,
			Date dateUpdated) {
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
	
	

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
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
