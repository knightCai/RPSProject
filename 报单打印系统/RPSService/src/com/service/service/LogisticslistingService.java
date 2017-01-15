package com.service.service;

import java.util.List;

import com.service.model.Logisticslisting;


public interface LogisticslistingService {

public void saveLlist(Logisticslisting llist);
	
	public void deleteLlist(Logisticslisting llist);
	
	public void updateLlist(Logisticslisting llist);
	
	public Logisticslisting findLlistByPkId(String pkId);
	
	public List<Logisticslisting> findAllLlist();
	
	public List<Logisticslisting> findLlistByParams(String[] params);
	
	public String updateLlistBySql(String param);
	
	public String findMaxImportnum(String userid);
}
