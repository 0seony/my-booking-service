package com.mybooking.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mybooking.dto.Booking;

public class BookingService {
	BookingDAO bookingDao = new BookingDAO();

	public Booking selectByBookingNo(String BookingNo) {
		return bookingDao.selectByBookingNo(BookingNo);
	}

	public List<Booking> selectByUserId(String userId) {
		return bookingDao.selectByUserId(userId);
	}

	public String booking(Booking booking) {
		int result = validDate(booking.getInDate(), booking.getOutDate());
		if (result == 1) {
			return "체크아웃 날짜가 체크인보다 이전입니다.";
		}
		if (result == 2) {
			return "지난 날짜는 예약이 불가능합니다.";
		}

		return bookingDao.booking(booking);
	}

	public String cancleBook(String BookingNo) {
		return bookingDao.cancleBook(BookingNo);
	}

	private int validDate(String indate, String outdate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date checkin = null;
		Date checkout = null;
		Date today = null;
		try {
			checkin = formatter.parse(indate);
			checkout = formatter.parse(outdate);
			Calendar cal = Calendar.getInstance();
			String stoday = formatter.format(cal.getTime());
			today = formatter.parse(stoday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 체크인 날짜보다 체크아웃 날짜가 큰 경우
		if (checkout.before(checkin)) {
			return 1;
		}
		// 체크인 날짜가 지난 날짜인 경우
		if (checkin.before(today)) {
			return 2;
		}
		return 3;
	}

}
