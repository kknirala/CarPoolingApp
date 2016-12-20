package edu.mum.wap.service;

import java.sql.SQLException;

public interface ILoginService {
	public boolean IsUserRegistered(String username, String password) throws SQLException;
}
