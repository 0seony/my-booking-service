package com.mybooking.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mybooking.dto.Accommodation;

import util.OracleUtil;

public class AccDAO {
	Connection conn;
	Statement statement;
	PreparedStatement prepared;
	ResultSet resultSet;
	int resultCount;

	public List<Accommodation> selectAll() {
		List<Accommodation> accList = new ArrayList<>();
		conn = OracleUtil.getConnection();
		String selectAll = "select * from Accommodation order by house_no";
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(selectAll);
			while (resultSet.next()) {
				Accommodation acc = makeObject(resultSet);
				accList.add(acc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(resultSet, statement, conn);
		}
		return accList;

	}
	
	public List<Accommodation> selectByName(String accName) {
		List<Accommodation> accList = new ArrayList<>();
		conn = OracleUtil.getConnection();
		String selectByName = "select * from accommodation where name like '%"
				+ accName
				+ "%' order by house_no";
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(selectByName);
			while (resultSet.next()) {
				Accommodation acc = makeObject(resultSet);
				accList.add(acc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(resultSet, statement, conn);
		}
		return accList;
	}
	
	public List<Accommodation> selectByLoc(String loc) {
		List<Accommodation> accList = new ArrayList<>();
		conn = OracleUtil.getConnection();
		String selectByName = "select * from accommodation where loc like '%"
				+ loc
				+ "%' order by house_no";
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(selectByName);
			while (resultSet.next()) {
				Accommodation acc = makeObject(resultSet);
				accList.add(acc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(resultSet, statement, conn);
		}
		return accList;

	}

	private Accommodation makeObject(ResultSet rs) throws SQLException {
		Accommodation acc = new Accommodation();
		acc.setHouseNo(rs.getInt("house_no"));
		acc.setHouseName(rs.getString("name"));
		acc.setAddress(rs.getString("address"));
		acc.setTel(rs.getString("tel"));
		acc.setLoc(rs.getString("loc"));
		acc.setRoom(rs.getInt("room"));

		return acc;
	}

}
