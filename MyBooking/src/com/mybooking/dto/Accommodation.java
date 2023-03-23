package com.mybooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {
	private int houseNo;
	private String houseName;
	private String address;
	private String tel;
	private String loc;
	private int room;

}
