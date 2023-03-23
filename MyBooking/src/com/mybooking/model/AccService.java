package com.mybooking.model;

import java.util.List;

import com.mybooking.dto.Accommodation;


public class AccService {
	AccDAO accDao = new AccDAO();
	
	public List<Accommodation> selectAll() {
		return accDao.selectAll();
	}
	
	public List<Accommodation> selectByName(String accName) {
		return accDao.selectByName(accName);
	}
	
	public List<Accommodation> selectByLoc(String loc) {
		return accDao.selectByLoc(loc);
	}

}
