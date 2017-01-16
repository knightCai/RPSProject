package com.client.model.contrllo;

import java.util.List;

import com.client.common.FrameUtil;
import com.service.service.impl.Oddnumber;
import com.service.service.impl.OddnumberServiceImpl;
import com.service.service.impl.OddnumberServiceImplService;

public class OddnumberContrllo {
	private OddnumberServiceImpl oddnumserImpl;
	
	public OddnumberContrllo() {
		if(oddnumserImpl == null)
			oddnumserImpl = new OddnumberServiceImplService().getOddnumberServiceImplPort();
	}
	
	public void saveOddNum(Oddnumber oddnum){
		oddnum.setPkid(FrameUtil.getUUID());
		oddnumserImpl.saveOddnum(oddnum);
	}
	
	public void deleteOddNum(Oddnumber oddnum){
			oddnumserImpl.deleteOddnum(oddnum);
	}
	
	public List<Oddnumber> findAllOddnum(){
		return oddnumserImpl.findAllOddnum();
	}
}
