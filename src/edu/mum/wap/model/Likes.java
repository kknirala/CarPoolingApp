package edu.mum.wap.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "likes")
@XmlAccessorType (XmlAccessType.FIELD)
public class Likes {
	private int likeId;
	private Users user;
	private Posts post;
	private Date dateCreated;
	private Date dateUpdated;

	public Likes(int likeId, Users user, Posts post, Date dateCreated, Date dateUpdated) {
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

	//only for test
	@Override
	public String toString() {
		return "Likes [likeId=" + likeId + ", post=" + post + ", dateCreated=" + dateCreated + ", dateUpdated="
				+ dateUpdated + "]";
	}
	

}
