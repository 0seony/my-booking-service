package com.mybooking.controller;

import java.util.Scanner;

import com.mybooking.dto.Users;
import com.mybooking.view.Menu;

public class Application {
	public static void main(String[] args) {
		Job job = new Job();
		Scanner sc = new Scanner(System.in);

		while (true) {
			Menu.showMenu();
			String choice = sc.next();
			if (choice.equals("6")) {
				System.out.println("종료되었습니다.");
				break;
			}
			switch (choice) {
			case "1": {// 회원 가입
				job.signUp();
				break;
			}
			case "4": // 로그인 없이 숙소 예약
				System.out.println("로그인후 이용가능합니다.");
			case "2": // 로그인
				Users nowUser = job.login();
				job.onLogin(nowUser);
				break;
			case "3":
				job.search();
				break;
			case "5": // 예약번호로 예약확인
				job.bookedList();
				break;
			}

		}
	}
}
