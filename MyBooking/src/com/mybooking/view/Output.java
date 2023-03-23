package com.mybooking.view;

import java.util.List;

import com.mybooking.dto.Accommodation;
import com.mybooking.dto.Booking;

public class Output {

	public static void print(List<Accommodation> accList) {
		System.out.println("-------------------------------------여수시 숙소 정보-----------------------------------------");
		System.out.printf("%-17s\t\t %-30s %-12s %s", "숙소명", "위치", "연락처", "   객실수");
		System.out.println();
		System.out
				.println("------------------------------------------------------------------------------------------");
		for (Accommodation one : accList) {
			System.out.printf("%-20s %-30s %15s\t %3d", one.getHouseName(), one.getAddress(), one.getTel(),
					one.getRoom());
			System.out.println();
		}
	}

	public static void print(Booking booked) {
		if (booked == null) {
			String msg = "예약 내역이 없습니다.";
			print(msg);
			return;
		}
		System.out.println("---------------------------예약 내역------------------------------------");
		System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s", "예약번호", "숙소명", "체크인", "체크아웃", "객실수");
		System.out.println();
		System.out.println("---------------------------------------------------------------------");
		System.out.print(booked.getBookNo() + "\t");
		System.out.print(booked.getHouseName() + "\t\t");
		System.out.print(booked.getInDate() + "\t");
		System.out.print(booked.getOutDate() + "\t  ");
		System.out.println(booked.getRoom());
	}

	public static void printBooked(List<Booking> bookedList, String nowUser) {
		if (bookedList.size() == 0) {
			String msg = "예약 내역이 없습니다.";
			print(msg);
			return;
		}
		System.out.println("----------------------" + nowUser +"님 예약 내역----------------------------------");
		System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s", "예약번호", "숙소명", "체크인", "체크아웃", "객실수");
		System.out.println();
		System.out.println("---------------------------------------------------------------------");
		for (Booking one : bookedList) {
			System.out.print(one.getBookNo() + "\t");
			System.out.print(one.getHouseName() + "\t\t");
			System.out.print(one.getInDate() + "\t");
			System.out.print(one.getOutDate() + "\t  ");
			System.out.println(one.getRoom());
		}
	}

	public static void print(String msg) {
		System.out.println("[알림]" + msg);
	}

}
