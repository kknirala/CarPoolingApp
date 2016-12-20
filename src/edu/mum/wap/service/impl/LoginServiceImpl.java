package edu.mum.wap.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.mum.wap.datasource.DBConnection;
import edu.mum.wap.service.ILoginService;

public class LoginServiceImpl implements ILoginService {

	PreparedStatement ps;

	@Override
	public boolean IsUserRegistered(String username, String password) throws SQLException {
		ps = DBConnection.getConnection().conn.prepareStatement("select * from users");
		ResultSet rs = ps.executeQuery();
		boolean isuserfound = false;
		while (rs.next()) {
			String dbUsername = rs.getString(9);
			String dbPassword = rs.getString(10);
			if (dbUsername.trim().equals(username) && (dbPassword.trim().equals(password))) {
				isuserfound = true;
				break;
			}
		}
		return isuserfound;
	}
}
