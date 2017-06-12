package com.client.model.contrllo;

import java.util.List;

import com.client.common.FrameUtil;
import com.service.service.Oddnumber;
import com.service.service.OddnumberServiceDelegate;
import com.service.service.OddnumberServiceService;
import com.service.service.Userinfo;
import com.service.service.UserinfoServiceDelegate;
import com.service.service.UserinfoServiceService;

public class UserinfoContrllo {
	private UserinfoServiceDelegate userdele;
	
	public UserinfoContrllo() {
		if(userdele == null)
			userdele = new UserinfoServiceService().getUserinfoServicePort();
	}
	
	public void saveOddNum(Userinfo user){
		user.setPkid(FrameUtil.getUUID());
		userdele.saveUser(user);
	}
	
	public void deleteUser(Userinfo user){
		userdele.deleteUser(user);
	}
	
	public List<Userinfo> findAllUser(){
		return userdele.findAllUser();
	}
	public Userinfo findUserByUserId(String userId) {
		return userdele.findUserByUserId(userId);
	}
	public void updateUser(Userinfo user) {
		userdele.updateUser(user);
	}
	
	public Userinfo userLogin(Userinfo user){
		return userdele.userLogin(user);
	}
}
