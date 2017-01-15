package com.service.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.service.model.Userinfo;
import com.service.service.UserinfoService;


@WebService
public class UserinfoServiceImpl implements UserinfoService {

	
	public void saveUser(Userinfo user) {
	}
	public void deleteUser(Userinfo user) {
	}
	public List<Userinfo> findAllUser() {
		List<Userinfo> list =  new ArrayList<Userinfo>();
		return (List<Userinfo>) list;
	}
	public Userinfo findUserByUserId(String UserId) {
		return null;
	}
	public void updateUser(Userinfo user) {
	}
}
