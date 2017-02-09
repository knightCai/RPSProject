package com.client.model.contrllo;

import java.util.List;

import com.client.common.FrameUtil;
import com.service.service.Oddnumber;
import com.service.service.OddnumberServiceDelegate;
import com.service.service.OddnumberServiceService;

public class OddnumberContrllo {
	private OddnumberServiceDelegate oddnumserImpl;
	
	public OddnumberContrllo() {
		if(oddnumserImpl == null)
			oddnumserImpl = new OddnumberServiceService().getOddnumberServicePort();
	}
	
	public void saveOddNum(Oddnumber oddnum){
		oddnum.setPkid(FrameUtil.getUUID());
		oddnumserImpl.saveOddnum(oddnum);
	}
	
	public void deleteOddNum(Oddnumber oddnum){
			oddnumserImpl.deleteOddnum(oddnum);
	}
	
	public List<Oddnumber> findAllOddnum(String userid){
		return oddnumserImpl.findAllOddnumByUserid(userid);
	}
}
