package com.service.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.service.model.Userinfo;

public interface UserinfoService {

public void saveUser(Userinfo user);
	public void deleteUser(Userinfo user);
	public void updateUser(Userinfo user);
	public Userinfo findUserByUserId(String userId);
	public List<Userinfo> findAllUser();
}
