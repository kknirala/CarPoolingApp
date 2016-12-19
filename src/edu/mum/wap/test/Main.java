package edu.mum.wap.test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import edu.mum.wap.model.Users;
import edu.mum.wap.service.impl.UserServiceImpl;

public class Main {
	public static void main(String[] args) throws SQLException {
//		System.out.println(null == null);
//		Users user = new Users(10, "yishagerew", "male", "Iowa", "Fairfield", "1000 N", "52557", LocalDate.now(), "ylulie@Gamil.com", "12345", LocalDate.now(), LocalDate.now());
//		user.setEmail("yishagerew.yitaih@unit.it");
		//		new UserServiceImpl().addNewUser(user);
//		Date date = Date.valueOf(LocalDate.now());
//		Instant instant = Instant.ofEpochMilli(date.getTime());
//		System.out.println(instant);
//		System.out.println(LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
//        .toLocalDate());
//		System.out.println(new UserServiceImpl().findUser(10));
//		System.out.println(new UserServiceImpl().updateUser(user));
		new UserServiceImpl().deleteUser(10);
	}
}
