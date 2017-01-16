package com.client.model;

import java.util.ArrayList;
import java.util.List;

import com.client.common.ExcelUtil;
import com.client.common.FrameUtil;
import com.service.service.impl.Logisticslisting;

public class LogisticslistingService {
	ExcelUtil excutil;
	public LogisticslistingService() {
		if(excutil == null){
			excutil = new ExcelUtil();
		}
	}
	public boolean readOddNumFile(String path){
		boolean flag = false;
		List<String> list = null;
		List<Logisticslisting> logislist = new ArrayList<Logisticslisting>();
		List<ArrayList<String>> oddarrlist = excutil.read(path);
		for(int i = 0; i < oddarrlist.size(); i++){
			/*list = oddarrlist.get(i);
			Logisticslisting logis = new Logisticslisting();
			logis.setPkid(FrameUtil.getUUID());
			logis.setDeclarenum(temp.get(0).toString());
			logis.setSerialnum(temp.get(0).toString());
			logis.setExpressnum(temp.get(1).toString());
			logis.setBrand(temp.get(0).toString());
			logis.setCargoname(temp.get(0).toString());
			logis.setCargotype(temp.get(0).toString());
			logis.setDeclareweight(temp.get(0).toString());
			logis.setDeclareprice(temp.get(0).toString());
			logis.setDeclarepricesum(temp.get(0).toString());
			logis.setLegalnum(temp.get(0).toString());
			logis.setLegalunit(temp.get(0).toString());
			logis.setNetweight(temp.get(0).toString());
			logis.setCargoid(temp.get(0).toString());
			logis.setPackagecount(temp.get(0).toString());
			logis.setCount(temp.get(0).toString());
			logis.setConsigneename(temp.get(0).toString());
			logis.setConsigneeaddr(temp.get(0).toString());
			logis.setConsigneephone(temp.get(0).toString());
			logis.setConsignercardid(temp.get(0).toString());
			logis.setConsignername(temp.get(0).toString());
			logis.setConsigneraddr(temp.get(0).toString());
			logis.setConsignerphone(temp.get(0).toString());
			logis.setConsignercountry(temp.get(0).toString());
			logis.setConsigneecountry(temp.get(0).toString());
			logis.setIsprint(temp.get(0).toString());
			logis.setImportnum(spinner.getText());
			//logis.setCreatetime(temp.get(0).toString());
			logis.setImportuser(temp.get(0).toString());
		   	logis.setIsprint("0");
			logislist.add(logis);*/
		}
		return flag;
	}
}
