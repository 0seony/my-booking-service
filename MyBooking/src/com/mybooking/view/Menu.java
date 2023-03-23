package com.mybooking.view;

public class Menu {
	
	public static void showMenu() {
		System.out.println("===================================================");
		System.out.println("|★여수밤바다★|");
		System.out.println("1.회원가입|2.로그인|3.숙소 검색|4.숙소 예약|5.예약 확인|6.종료");
		System.out.println("===================================================");
		System.out.print("무엇을 하시겠습니까?>>");
	}
	
	public static void onLogin(String nowUser) {
		System.out.println("===================================================");
		System.out.println("|★" + nowUser + "★님 환영합니다|");
		System.out.println("1.숙소 검색|2.숙소 예약|3.예약 확인|4.예약 취소|5.로그아웃");
		System.out.println("===================================================");
		System.out.print("무엇을 하시겠습니까?>>");
		
	}
	
	public static void search() {
		System.out.println("===================================================");
		System.out.println("1.전체|2.숙소명|3.위치|4.뒤로가기");
		System.out.println("===================================================");
		System.out.print("무엇을 하시겠습니까?>>");
	}
	
	public static void searchByName() {
		System.out.println("숙소명을 입력해주세요>>");
	}
	
	public static void searchByLoc() {
		System.out.println("쌍봉동|화정면|화양면|남면|삼산면|만덕동|소라면|돌산읍");
		System.out.println("위치를 입력해주세요>>");
	}

}
