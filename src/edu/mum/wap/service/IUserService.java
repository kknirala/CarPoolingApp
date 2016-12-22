package edu.mum.wap.service;

import java.sql.SQLException;

import edu.mum.wap.model.Users;

public interface IUserService {

	public Users addNewUser(Users user) throws SQLException;
	public void deleteUser(int id) throws SQLException;
	public Users updateUser(Users user) throws SQLException;
	public Users findUser(int id) throws SQLException;
	public int getMaxId() throws SQLException;
}
