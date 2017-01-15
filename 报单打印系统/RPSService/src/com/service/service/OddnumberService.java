package com.service.service;

import java.util.List;

import com.service.model.Oddnumber;


public interface OddnumberService {

public String saveOddnum(Oddnumber oddnum);
	
	public String deleteOddnum(Oddnumber oddnum);
	
	public String updateOddnum(Oddnumber oddnum);
	
	public Oddnumber findOddnumByPkId(String oddnumId);
	
	public List<Oddnumber> findAllOddnum();
	
	public Oddnumber getUseOddnumber(String userid);
}
