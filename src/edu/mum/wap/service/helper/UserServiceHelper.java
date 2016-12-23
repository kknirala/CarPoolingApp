package edu.mum.wap.service.helper;

import java.sql.SQLException;
import java.util.Date;

import edu.mum.wap.model.Users;
import edu.mum.wap.model.mapper.UserMapper;
import edu.mum.wap.service.impl.UserServiceImpl;

public class UserServiceHelper {
	public static Users getUserFrommapper(UserMapper mapper) throws SQLException {

		int userId = new UserServiceImpl().getMaxId();
		Users user = new Users(userId + 1, mapper.getFullName(), mapper.getGender(), mapper.getState(), mapper.getCity(), mapper.getStreet(), mapper.getZipCode(), new Date(), mapper.getEmail(), mapper.getPassword(), new Date(), new Date());
		return user;

	}
}
