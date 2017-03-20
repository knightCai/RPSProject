package com.client.model.contrllo;

import java.util.ArrayList;
import java.util.List;

import com.client.common.FrameUtil;
import com.service.service.Logisticslisting;
import com.service.service.LogisticslistingServiceDelegate;
import com.service.service.LogisticslistingServiceService;

public class LogisticsListContrllo {
	private LogisticslistingServiceDelegate Loglistser;
	
	public LogisticsListContrllo() {
		if(Loglistser == null)
			Loglistser = new LogisticslistingServiceService().getLogisticslistingServicePort();
	}
	
	public void saveLoglist(Logisticslisting Loglist){
		Loglist.setPkid(FrameUtil.getUUID());
		Loglistser.saveLlist(Loglist);
	}
	/**
	 * 批量保存数据
	 * @param Llists
	 * @return
	 */
	public String saveBatch(List<Logisticslisting> Llists){
		return Loglistser.saveBatch(Llists);
	}
	public void deleteLoglist(Logisticslisting Loglist){
			Loglistser.deleteLlist(Loglist);
	}
	
	public List<Logisticslisting> findAllLoglist(){
		return Loglistser.findAllLlist();
	}

	public List<Logisticslisting> findLlistByParams(List lsit) {
		return Loglistser.findLlistByParams(lsit);
	}
	
	public void updateLlist(Logisticslisting Llist) {
		//Loglistser.updateLlist(Llist);
	}

	public String updateLlistBySql(String sql) {
		return Loglistser.updateLlistBySql(sql);
	}
	
	public String findMaxImportnum(String userid){
		return Loglistser.findMaxImportnum(userid);	
	}

	public String updateBatch(List<Logisticslisting> llist) {
		return Loglistser.updateBatch(llist);
	}
	
	public String deleteBatch(List<String> delpkids) {
		return Loglistser.deleteBatch(delpkids);
	}
}
