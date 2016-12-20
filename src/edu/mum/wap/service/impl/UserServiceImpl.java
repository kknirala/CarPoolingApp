package edu.mum.wap.service.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.model.Users;
import edu.mum.wap.service.IUserService;
import edu.mum.wap.util.DateToLocalDateUtil;

public class UserServiceImpl implements IUserService {

	PreparedStatement ps;

	@Override
	public void deleteUser(int id) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("delete from Users where userId = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	@Override
	public Users updateUser(Users user) {

		Users oldUser = null;
		try {
			oldUser = findUser(user.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldUser.setPassword(user.getPassword());
		oldUser.setCity(user.getCity());
		oldUser.setStreet(user.getStreet());
		oldUser.setZipCode(user.getZipCode());
		oldUser.setEmail(user.getEmail());
		oldUser.setState(user.getState());

		try {
			ps = DBConnection.getConnection().conn.prepareStatement(
					"update Users set password=?, state=?, city=?, street=?, zipcode=?,email=?,dateupdated=? where userId=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		ps.setString(1, user.getPassword());
		ps.setString(2, user.getState());
		ps.setString(3, user.getCity());
		ps.setString(4, user.getStreet());
		ps.setString(5, user.getZipCode());
		ps.setString(6, user.getEmail());
		ps.setDate(7, Date.valueOf(LocalDate.now()));
		ps.setInt(8, user.getUserId());

		ps.executeUpdate();
		}
		catch(Exception e){
			
		}

		

		return oldUser;

	}

	@Override
	public Users findUser(int id) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("select * from Users where userId = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Users user = null;
		if (rs.next()) {
			System.out.println("User found");
			user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9),
					rs.getString(10), rs.getDate(11),
					rs.getDate(12));
		}
		return user;
	}

	@Override
	public Users addNewUser(Users user) {

		try {

			ps = DBConnection.getConnection().conn.prepareStatement(
					"insert into Users (userId, fullname, gender, state, city, street, zipcode, birthyear, email, password, datecreated,dateupdated) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getFullName());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getState());
			ps.setString(5, user.getCity());
			ps.setString(6, user.getStreet());
			ps.setString(7, user.getZipCode());
			ps.setDate(8, new java.sql.Date(user.getBirthYear().getTime()));
			ps.setString(9, user.getEmail());
			ps.setString(10, user.getPassword());
			ps.setDate(11, new java.sql.Date(user.getDateCreated().getTime()));
			ps.setDate(12, new java.sql.Date(user.getDateUpdated().getTime()));
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
