package edu.mum.wap.model;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class FakeUser {

	
	public FakeUser() {

	}
	private Date date;
	private String name;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FakeUser(Date date, String name) {
		this.date = date;
		this.name = name;
	}
	@Override
	public String toString() {
		return "FakeUser [date=" + date + ", name=" + name + "]";
	}
	
	
	
}
