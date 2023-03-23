package com.mybooking.dto;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private Date birth;
	
}
