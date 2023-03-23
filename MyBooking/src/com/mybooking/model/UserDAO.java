package com.mybooking.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mybooking.dto.Users;

import util.OracleUtil;

public class UserDAO {
	Connection conn;
	Statement statement;
	PreparedStatement prepared;
	ResultSet resultSet;
	int resultCount;

	// 회원가입
	public int signUp(Users user) {
		String signUp = "insert into users values(?,?,?,?,?)";
		conn = OracleUtil.getConnection();
		try {
			prepared = conn.prepareStatement(signUp);
			prepared.setString(1, user.getUserId());
			prepared.setString(2, user.getUserPwd());
			prepared.setString(3, user.getUserName());
			prepared.setString(4, user.getPhone());
			prepared.setDate(5, user.getBirth());
			resultCount = prepared.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, prepared, conn);
		}
		return resultCount;
	}

	public void login(Users user) {
		String login = "select user_name from users where user_id =? and user_pwd=?";
		conn = OracleUtil.getConnection();
		try {
			prepared = conn.prepareStatement(login);
			prepared.setString(1, user.getUserId());
			prepared.setString(2, user.getUserPwd());
			resultSet = prepared.executeQuery();
			while(resultSet.next()) {
				user.setUserName(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, prepared, conn);
		}
	}

}
