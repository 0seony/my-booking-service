package com.mybooking.controller;

import java.sql.Date;
import java.util.Scanner;

import com.mybooking.dto.Booking;
import com.mybooking.dto.Users;
import com.mybooking.model.AccService;
import com.mybooking.model.BookingService;
import com.mybooking.model.UserService;
import com.mybooking.view.Menu;
import com.mybooking.view.Output;

import util.DateUtil;

public class Job {
	Scanner sc = new Scanner(System.in);
	UserService userService = new UserService();
	AccService accService = new AccService();
	BookingService bookingService = new BookingService();
	
	public void signUp() {
		System.out.println("----------회원 가입----------");
		Users user = new Users();
		System.out.print("아이디를 입력하세요>>");
		String id = sc.next();
		System.out.print("비밀번호를 입력하세요>>");
		String pwd = sc.next();
		System.out.print("이름을 입력하세요>>");
		String name = sc.next();
		System.out.print("연락처를 입력하세요(010-0000-0000)>>");
		String phone = sc.next();
		System.out.print("생년월일을 입력하세요(yyyy/mm/dd)>>");
		String sdate = sc.next();
		Date birth = DateUtil.convertToDate(sdate);
		user.setUserId(id);
		user.setUserPwd(pwd);
		user.setUserName(name);
		user.setPhone(phone);
		user.setBirth(birth);
		System.out.println(userService.signUp(user));
	}
	
	public Users login() {
		System.out.println("[로그인]");
		System.out.print("아이디를 입력하세요>>");
		String id = sc.next();
		System.out.print("비밀번호를 입력하세요>>");
		String pwd = sc.next();
		Users user = new Users();
		user.setUserId(id);
		user.setUserPwd(pwd);
		return userService.login(user);
	}
	
	public void onLogin(Users nowUser) {
		while(true) {
			Menu.onLogin(nowUser.getUserName());
			 String choice = sc.next();
			switch (choice) {
			case "1":
				search();
				continue;
			case "2":
				System.out.println("[숙소 예약]");
				System.out.print("예약하실 숙소명을 입력해주세요>> ");
				String houseName = sc.next();
				System.out.print("체크인 날짜를 입력해주세요((yyyy/mm/dd)>> ");
				String checkin = sc.next();
				System.out.print("체크아웃 날짜를 입력해주세요((yyyy/mm/dd)>> ");
				String checkout = sc.next();
				System.out.print("원하시는 객실수를 입력해주세요>> ");
				int sumRoom = sc.nextInt();
				Booking booking = new Booking();
				booking.setHouseName(houseName);
				booking.setUserId(nowUser.getUserId());
				booking.setInDate(checkin);
				booking.setOutDate(checkout);
				booking.setRoom(sumRoom);
				Output.print(bookingService.booking(booking));
				continue;
			case "3":
				Output.printBooked(bookingService.selectByUserId(nowUser.getUserId()), nowUser.getUserName());
				continue;
			case "4":
				Output.printBooked(bookingService.selectByUserId(nowUser.getUserId()), nowUser.getUserName());
				System.out.print("취소하실 예약 번호를 입력해주세요>> ");
				String bookNo = sc.next();
				Output.print(bookingService.cancleBook(bookNo));
				continue;
			case "5":
				break;
			}
			break;
		}
		
	}
	
	public void search() {
		while(true) {
			Menu.search();
			 String choice = sc.next();
			switch (choice) {
			case "1":
				Output.print(accService.selectAll());
				continue;
			case "2":
				Menu.searchByName();
				String accName = sc.next();
				Output.print(accService.selectByName(accName));
				continue;
			case "3":
				Menu.searchByLoc();
				String loc = sc.next();
				Output.print(accService.selectByLoc(loc));
				continue;
			case "4":
				break;
			}
			break;
		}
	}
	
	public void bookedList() {
		System.out.print("예약 번호를 입력해주세요>> ");
		String bookNo = sc.next();
		Output.print(bookingService.selectByBookingNo(bookNo));
	}

}
