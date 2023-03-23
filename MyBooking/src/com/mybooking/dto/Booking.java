package com.mybooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	private String bookNo;
	private int houseNo;
	private String houseName;
	private String userId;
	private String inDate;
	private String outDate;
	private int room;

}
