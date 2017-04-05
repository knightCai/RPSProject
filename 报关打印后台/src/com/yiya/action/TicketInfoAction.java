/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TicketInfo
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.yiya.bean.TicketInfo;
import com.yiya.model.TicketInfoModel;
import com.yiya.service.TicketInfoService;
import com.yiya.utils.ActionUpload;
import com.yiya.utils.ExcelUtil;
import com.yiya.utils.HtmlUtil;
import com.yiya.exception.ServiceException;
/**
 * TicketInfo.
 * @author knight
 */
@Controller
@RequestMapping("/ticketInfo") 
public class TicketInfoAction extends BaseAction {
	
	private final static Logger log= Logger.getLogger(MainAction.class);
	private String savePath = "tempfile";
	
	// Servrice start
	@Autowired(required=false) 
	private TicketInfoService<TicketInfo> ticketInfoService; 
	
	/**
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(TicketInfoModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<TicketInfo> dataList = ticketInfoService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("kh/ticketInfo",context); 
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(TicketInfoModel model,HttpServletResponse response) throws Exception{
		List<TicketInfo> dataList = ticketInfoService.queryByList(model);
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
	public void save(TicketInfo bean,HttpServletResponse response) throws Exception{
		TicketInfo ticketInfo = ticketInfoService.queryById(bean.getId());
		if(bean.getId() == null || bean.getId().equals("")){
			if(ticketInfo != null){
				throw new ServiceException("数据已存在.");
			}
			ticketInfoService.add(bean);
		}else{
			ticketInfoService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		TicketInfo bean  = ticketInfoService.queryById(id);
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
		ticketInfoService.delete(id);
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
		List<TicketInfo> ticketInfolist = new ArrayList<TicketInfo>();
		ActionUpload upload = new ActionUpload();
		//1、保存上传文件
		request.setAttribute("type", "ticketInfo");
		String filename = upload.uploadFile(request, savePath);
		//2、读取execl内容封装成对象
		List<ArrayList<String>> llist = new ExcelUtil().read(request.getSession().getServletContext().getRealPath("/")+savePath+"/"+filename);
		TicketInfo ticketInfo;
		for(List<String> model:llist){
			if("".equals(model.get(0)))
				continue;
			ticketInfo = new TicketInfo();
			ticketInfo.setSernum(model.get(0));
			ticketInfo.setBino(model.get(1));
			ticketInfo.setDelnum(model.get(2));
			ticketInfo.setWpname(model.get(3));
			ticketInfo.setBrand(model.get(4));
			ticketInfo.setSpec(model.get(5));
			ticketInfo.setCount(model.get(6));
			ticketInfo.setPrice(model.get(7));
			ticketInfo.setCurrency(model.get(8));
			ticketInfo.setDiscount(model.get(9));
			ticketInfolist.add(ticketInfo);
		}
		//3、批量插入
		if(ticketInfolist.size()>0){
			ticketInfoService.insertbatch(ticketInfolist);
			//删除重复的数据
			ticketInfoService.deleteDouble();
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
		String filepath = request.getSession().getServletContext().getRealPath("/")+"temples/TicketInfoTemple.xls";
		String fileName = "小票货品名转译导入模板.xls";
		ActionUpload upload = new ActionUpload();
		upload.downFiel(fileName, filepath, response);
	}

}