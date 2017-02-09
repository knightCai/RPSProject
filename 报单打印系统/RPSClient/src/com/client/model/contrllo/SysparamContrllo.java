package com.client.model.contrllo;

import java.util.List;

import com.client.common.FrameUtil;
import com.service.service.Oddnumber;
import com.service.service.OddnumberServiceDelegate;
import com.service.service.OddnumberServiceService;
import com.service.service.Sysparam;
import com.service.service.SysparamServiceDelegate;
import com.service.service.SysparamServiceService;

public class SysparamContrllo {
	private SysparamServiceDelegate sysparamser;
	
	public SysparamContrllo() {
		if(sysparamser == null)
			sysparamser = new SysparamServiceService().getSysparamServicePort();
	}
	
	public void saveOddNum(Sysparam sys){
		sys.setPkid(FrameUtil.getUUID());
		sysparamser.saveSysParam(sys);
	}
	
	public void deletesys(Sysparam sys){
		sysparamser.deleteSysParam(sys);
	}
	
	public List<Sysparam> findAllsys(){
		return sysparamser.findAllSysParam();
	}
	public Sysparam findSysByParamname(String paramname) {
		return sysparamser.findSysByParamname(paramname);
	}
	public void updateSysParam(Sysparam sys) {
		sysparamser.updateSysParam(sys);
	}
}
