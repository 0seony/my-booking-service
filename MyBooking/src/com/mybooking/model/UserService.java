package com.mybooking.model;

import com.mybooking.dto.Users;

public class UserService {

	UserDAO userDAO = new UserDAO();

	public String signUp(Users user) {
		int result = userDAO.signUp(user);
		return result > 0 ? "회원가입 성공" : "회원가입 실패";
	}
	
	public Users login(Users user) {
		userDAO.login(user);
		return user;
	}

}
