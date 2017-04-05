/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： XysbhParam
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.yiya.bean.XysbhParam;
import com.yiya.model.XysbhParamModel;
import com.yiya.service.XysbhParamService;
import com.yiya.utils.ActionUpload;
import com.yiya.utils.ExcelUtil;
import com.yiya.utils.HtmlUtil;
import com.yiya.exception.ServiceException;
/**
 * XysbhParam.
 * @author knight
 */
@Controller
@RequestMapping("/xysbhParam") 
public class XysbhParamAction extends BaseAction {

	private final static Logger log= Logger.getLogger(MainAction.class);
	private String savePath = "tempfile";

	// Servrice start
	@Autowired(required=false) 
	private XysbhParamService<XysbhParam> xysbhParamService; 

	/**
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(XysbhParamModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<XysbhParam> dataList = xysbhParamService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("kh/xysbhParam",context); 
	}


	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(XysbhParamModel model,HttpServletResponse response) throws Exception{
		List<XysbhParam> dataList = xysbhParamService.queryByList(model);
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
	public void save(XysbhParam bean,HttpServletResponse response) throws Exception{
		XysbhParam xysbhParam = xysbhParamService.queryByName(bean.getParamname());
		if(bean.getId() == null || bean.getId().equals("") || xysbhParam.getId() == null){
			xysbhParamService.add(bean);
		}else{
			bean.setId(xysbhParam.getId());
			xysbhParamService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		XysbhParam bean  = xysbhParamService.queryById(id);
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
		xysbhParamService.delete(id);
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
		List<XysbhParam> xysbhlist = new ArrayList<XysbhParam>();
		ActionUpload upload = new ActionUpload();
		//1、保存上传文件
		request.setAttribute("type", "xysbh");
		String filename = upload.uploadFile(request, savePath);
		//2、读取execl内容封装成对象
		List<ArrayList<String>> llist = new ExcelUtil().read(request.getSession().getServletContext().getRealPath("/")+savePath+"/"+filename);
		XysbhParam xysbh;
		for(List<String> model:llist){
			if("".equals(model.get(0)))
				continue;
			xysbh = new XysbhParam();
			xysbh.setParamname(model.get(0));
			xysbh.setParamnum(model.get(1));
			xysbh.setUnit(model.get(2));
			xysbhlist.add(xysbh);
		}
		//3、批量插入
		if(xysbhlist.size()>0){
			xysbhParamService.insertbatch(xysbhlist);
			//删除品名重复的数据
			xysbhParamService.deleteDouble();
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
		String filepath = request.getSession().getServletContext().getRealPath("/")+"temples/XysbhParamTemple.xls";
		String fileName = "行邮税号导入模板.xls";
		ActionUpload upload = new ActionUpload();
		upload.downFiel(fileName, filepath, response);
	}

}