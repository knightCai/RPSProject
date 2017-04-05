/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： LogisTics
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.yiya.utils.ExcelUtil;
import com.yiya.bean.LogisTics;
import com.yiya.bean.ZypmdzParam;
import com.yiya.model.LogisTicsModel;
import com.yiya.service.LogisTicsService;
import com.yiya.service.ZypmdzParamService;
import com.yiya.utils.ActionUpload;
import com.yiya.utils.HtmlUtil;
import com.yiya.exception.ServiceException;
/**
 * LogisTics.
 * @author knight
 */
@Controller
@RequestMapping("/logisTics") 
public class LogisTicsAction extends BaseAction {

	private final static Logger log= Logger.getLogger(MainAction.class);
	private String savePath = "tempfile";
	
	// Servrice start
	@Autowired(required=false) 
	private LogisTicsService<LogisTics> logisTicsService; 
	@Autowired(required=false) 
	private ZypmdzParamService<ZypmdzParam> zypmdzParamService; 
	/**
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(LogisTicsModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<LogisTics> dataList = logisTicsService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("kh/logisTics",context); 
	}


	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(LogisTicsModel model,HttpServletResponse response) throws Exception{
		List<LogisTics> dataList = logisTicsService.queryByList(model);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(LogisTics bean,HttpServletResponse response) throws Exception{
		LogisTics logisTics = logisTicsService.queryById(bean.getId());
		if(bean.getId() == null || bean.getId().equals("")){
			if(logisTics != null){
				throw new ServiceException("数据已存在.");
			}
			bean.setCreatetime(new Timestamp(System.currentTimeMillis()));
			bean.setId(UUID.randomUUID().toString().replace("-", ""));
			logisTicsService.add(bean);
		}else{
			logisTicsService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		LogisTics bean  = logisTicsService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}

	@RequestMapping("/delete")
	public void delete(String[] id,HttpServletResponse response) throws Exception{
		logisTicsService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * execl批量导入
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/import")
	public void addbatch(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<LogisTics> logislist = new ArrayList<LogisTics>();
		ActionUpload upload = new ActionUpload();
		//1、保存上传文件
		request.setAttribute("type", "logistics");
		String filename = upload.uploadFile(request, savePath);
		//2、读取execl内容封装成对象
		List<ArrayList<String>> llist = new ExcelUtil().read(request.getSession().getServletContext().getRealPath("/")+savePath+"/"+filename);
		LogisTics logis;
		for(List<String> model:llist){
			if("".equals(model.get(0)))
				continue;
			logis = new LogisTics();
			logis.setDeclarenum(model.get(0));
			logis.setConsigneecountry(model.get(1));
			logislist.add(logis);
		}
		//3、批量更新
		if(logislist.size()>0){
			logisTicsService.updatebatchforBGStatu(logislist);
		}
		sendSuccessMessage(response, "导入成功");
	}
	
	/**
	 * 导出导入模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String filepath = request.getSession().getServletContext().getRealPath("/")+"temples/LogisTicsStatuTemple.xls";
		String fileName = "包裹状态导入模板.xls";
		ActionUpload upload = new ActionUpload();
		upload.downFiel(fileName, filepath, response);
	}
	/**
	 * 生成清单数据
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping("/export")
	public void delete(LogisTicsModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<LogisTics> dataList = logisTicsService.queryListByParam(model);
		if(dataList.size()>0){
			//存放已经翻译好的数据
			List<LogisTics> tmpdataList = new ArrayList<LogisTics>();
			//存放未翻译好的数据
			List<LogisTics> tmpdataList1 = new ArrayList<LogisTics>();
			//未翻译中文品名集合
			Map<String,String> pmap = new HashMap<String,String>();
			//中英品名库
			List<ZypmdzParam> zypmdzList = zypmdzParamService.queryListByParam(new ZypmdzParamModel());
			for(LogisTics logis:dataList){
				//是否匹配
				boolean ischange = false;
				for(ZypmdzParam zypmdz : zypmdzList){
					if(zypmdz.getChainname().equals(logis.getCargoname())){
						logis.setCargoname(zypmdz.getEnglishname());
						tmpdataList.add(logis);
						ischange = true;
						break;
					}
				}
				//系统库未能匹配
				if(!ischange){
					tmpdataList1.add(logis);
					pmap.put(logis.getCargoname(), "");
				}
			}
			//存在待翻译字段
			if(pmap.size()>0){
				List<String> pmarr = new ArrayList<String>();
				//有道翻译集合 英文为key，中文为value
				Map<String, String> ydmap = new HashMap<String, String>();
				//本地词组对象
				List<ZypmdzParam> newZypmdzList = new ArrayList<ZypmdzParam>();
				ZypmdzParam tmpzypmdz = new ZypmdzParam();
				String tmpStr = "";
				for(String key:pmap.keySet()){
					//有道限定字符长度不能超过200个字符,本地限定在160左右
					if(tmpStr.length()>160){
						pmarr.add(tmpStr);
						tmpStr = "";
					}
					tmpStr += key+",";
				}
				//调用有道翻译接口
				for(String str:pmarr){
					String[] oldarr = str.split(",");
					String[] chaarr = YouDaoInterface.getRequest1(str).split(",");
					for(int k=0;k<oldarr.length;k++){
						ydmap.put(chaarr[k], oldarr[k]);
					}
				}
				log.debug("本次导出接口调用次数："+pmarr.size());
				//再次匹配
				for(LogisTics logis:tmpdataList1){
					for(String key:ydmap.keySet()){
						String keystr = ydmap.get(key);
						tmpzypmdz.setChainname(keystr);
						tmpzypmdz.setEnglishname(key);
						newZypmdzList.add(tmpzypmdz);
						if(keystr.equals(logis.getCargoname())){
							logis.setCargoname(key);
							tmpdataList.add(logis);
							break;
						}
					}
				}
				//新翻译词组保存在本地数据库
				zypmdzParamService.insertbatch(newZypmdzList);
			}
			String filepath = request.getServletContext().getRealPath("/")+"tempfile";
			String filename = "批次【"+model.getImportnum()+"】用户【"+model.getImportuser()+"】-"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"物流清单导出.xls";
			ExcelUtil.createExcel(tmpdataList,filepath+"/"+filename);
			ActionUpload upload = new ActionUpload();
			upload.downFiel(fileName, filepath, response);
			//设置页面数据
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("filepath",filepath);
			jsonMap.put("filename", filename);
			jsonMap.put(SUCCESS, true);
			HtmlUtil.writerJson(response, jsonMap);
		}else{
			sendFailureMessage(response, "没有找到对应的记录!");
		}
	}
	*//**
	 * 下载指定文件
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping("/downFile")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String filepath = request.getParameter("filepath");
		String filename = request.getParameter("filename");
		ActionUpload upload = new ActionUpload();
		upload.downFiel(filename, filepath, response);
	}*/
}