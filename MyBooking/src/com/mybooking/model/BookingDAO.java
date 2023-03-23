package com.mybooking.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.mybooking.dto.Booking;

import util.OracleUtil;

public class BookingDAO {
	Connection conn;
	Statement statement;
	PreparedStatement prepared;
	CallableStatement callable = null;
	ResultSet resultSet;
	int resultCount;

	public Booking selectByBookingNo(String BookingNo) {
		Booking booked = null;
		String selectByBookingNo = "select booking_no, name, user_id, in_date, out_date, booking_room"
				+ " from booking join accommodation using(house_no) where booking_no='" + BookingNo + "'";
		conn = OracleUtil.getConnection();
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(selectByBookingNo);
			while (resultSet.next()) {
				booked = makeObject(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(resultSet, statement, conn);
		}
		return booked;
	}

	public List<Booking> selectByUserId(String userId) {
		List<Booking> bookedList = new ArrayList<>();
		String selectByUserId = "select booking_no, name, user_id, in_date, out_date, booking_room"
				+ " from booking join accommodation using(house_no) where user_id='" + userId + "'";
		conn = OracleUtil.getConnection();
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(selectByUserId);
			while (resultSet.next()) {
				Booking booked = makeObject(resultSet);
				bookedList.add(booked);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(resultSet, statement, conn);
		}
		return bookedList;
	}

	public String booking(Booking booking) {
		String insert = """
				insert into booking values (
				(to_char(sysdate,'yymmdd')||lpad(seq_booking_no.nextval,3,0)),
				(select house_no from accommodation where name = ?),
				?,
				to_date(?,'yyyy/mm/dd'),
				to_date(?,'yyyy/mm/dd'),
				?
				)
				""";
		String gethouseNo = "select house_no from accommodation where name = '" + booking.getHouseName() + "'";
		conn = OracleUtil.getConnection();
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(gethouseNo);
			int houseNo = 0;
			while (resultSet.next()) {
				houseNo = resultSet.getInt(1);
			}
			booking.setHouseNo(houseNo);
			if (!newValidRoom(booking)) {
				System.out.println("해당 날짜는 이용 가능한 객실이 없습니다.");
				resultCount = -1;
			} else {
				prepared = conn.prepareStatement(insert);
				prepared.setString(1, booking.getHouseName());
				prepared.setString(2, booking.getUserId());
				prepared.setString(3, booking.getInDate());
				prepared.setString(4, booking.getOutDate());
				prepared.setInt(5, booking.getRoom());
				resultCount = prepared.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, prepared, conn);
		}
		return resultCount > 0 ? "예약이 완료되었습니다." : "예약이 실패하였습니다.";
	}

	private boolean validRoom(Booking booking) {
		int canBooked = 0;
		conn = OracleUtil.getConnection();
		String checkRoom = """
				select  nvl(room - (
				select sum(booking_room) from booking
				where house_no = ? and to_date(?,'yyyy/mm/dd') between in_date and out_date-1
				group by booking_room
				),room)room
				from accommodation where name = ?
				""";
		try {
			prepared = conn.prepareStatement(checkRoom);
			prepared.setInt(1, booking.getHouseNo());
			prepared.setString(2, booking.getInDate());
			prepared.setString(3, booking.getHouseName());
			resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				canBooked = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (canBooked <= 0 || canBooked < booking.getRoom()) {
			return false;
		}
		return true;
	}

	public String cancleBook(String BookingNo) {
		String canCancle = "select to_char(sysdate,'yyyymmdd'), to_char(in_date,'yyyymmdd')"
				+ " from booking where booking_no = ?";
		String cancle = "delete from booking where booking_no = ?";
		conn = OracleUtil.getConnection();
		try {
			prepared = conn.prepareStatement(canCancle);
			prepared.setString(1, BookingNo);
			resultSet = prepared.executeQuery();
			String nowDate = "";
			String inDate = "";
			while (resultSet.next()) {
				nowDate = resultSet.getString(1);
				inDate = resultSet.getString(2);
			}
			if (nowDate.equals(inDate)) {
				return "당일 취소는 불가능합니다.";
			}
			prepared = conn.prepareStatement(cancle);
			prepared.setString(1, BookingNo);
			resultCount = prepared.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(null, prepared, conn);
		}
		return resultCount > 0 ? "예약이 취소되었습니다." : "예약 취소 오류";
	}

	public boolean newValidRoom(Booking booking) {
		int canBooked = 0;
		conn = OracleUtil.getConnection();
		String query = "{call checkRoom(?,?,?,?,?)";
		try {
			callable = conn.prepareCall(query);
			callable.setString(1, booking.getInDate());
			callable.setString(2, booking.getOutDate());
			callable.setInt(3, booking.getHouseNo());
			callable.setString(4, booking.getHouseName());
			callable.registerOutParameter(5, Types.INTEGER);
			callable.executeQuery();
			canBooked = callable.getInt(5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (canBooked <= 0 || canBooked < booking.getRoom()) {
			return false;
		}
		return true;
	}

	private Booking makeObject(ResultSet rs) throws SQLException {
		Booking booked = new Booking();
		booked.setBookNo(rs.getString(1));
		booked.setHouseName(rs.getString(2));
		booked.setUserId(rs.getString(3));
		booked.setInDate(rs.getDate(4).toString());
		booked.setOutDate(rs.getDate(5).toString());
		booked.setRoom(rs.getInt(6));
		return booked;
	}

}
