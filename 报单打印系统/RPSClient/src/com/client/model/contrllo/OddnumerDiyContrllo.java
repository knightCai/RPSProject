package com.client.model.contrllo;

import java.util.List;

import com.client.common.FrameUtil;
import com.service.service.Oddnumber;
import com.service.service.OddnumberServiceDelegate;
import com.service.service.OddnumberServiceService;
import com.service.service.OddnumerDiy;
import com.service.service.OddnumerDiyServiceDelegate;
import com.service.service.OddnumerDiyServiceService;

public class OddnumerDiyContrllo {
private OddnumerDiyServiceDelegate oddnumserImpl;
	
	public OddnumerDiyContrllo() {
		if(oddnumserImpl == null)
			oddnumserImpl = new OddnumerDiyServiceService().getOddnumerDiyServicePort();
	}
	
	public List<OddnumerDiy> findAllOddnumDiy(String userid){
		return oddnumserImpl.findAllOddnumDiyByUserid(userid);
	}
}
