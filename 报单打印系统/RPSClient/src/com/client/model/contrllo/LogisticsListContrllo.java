package com.client.model.contrllo;

import java.util.List;

import com.client.common.FrameUtil;
import com.service.service.impl.Logisticslisting;
import com.service.service.impl.LogisticslistingServiceImpl;
import com.service.service.impl.LogisticslistingServiceImplService;

public class LogisticsListContrllo {
	private LogisticslistingServiceImpl LoglistserImpl;
	
	public LogisticsListContrllo() {
		if(LoglistserImpl == null)
			LoglistserImpl = new LogisticslistingServiceImplService().getLogisticslistingServiceImplPort();
	}
	
	public void saveLoglist(Logisticslisting Loglist){
		Loglist.setPkid(FrameUtil.getUUID());
		LoglistserImpl.saveLlist(Loglist);
	}
	/**
	 * 批量保存数据
	 * @param Llists
	 * @return
	 */
	public String saveBatch(List<Logisticslisting> Llists){
		return LoglistserImpl.saveBatch(Llists);
	}
	public void deleteLoglist(Logisticslisting Loglist){
			LoglistserImpl.deleteLlist(Loglist);
	}
	
	public List<Logisticslisting> findAllLoglist(){
		return LoglistserImpl.findAllLlist();
	}

	public List<Logisticslisting> findLlistByParams(List lsit) {
		return LoglistserImpl.findLlistByParams(lsit);
	}
	
	public void updateLlist(Logisticslisting Llist) {
		LoglistserImpl.updateLlist(Llist);
	}

	public String updateLlistBySql(String sql) {
		return LoglistserImpl.updateLlistBySql(sql);
	}
	
	public String findMaxImportnum(String userid){
		return LoglistserImpl.findMaxImportnum(userid);	
	}
}
