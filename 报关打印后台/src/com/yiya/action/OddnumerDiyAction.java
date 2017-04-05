/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： OddnumerDiy
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.action;

import java.math.BigDecimal;
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


import com.yiya.bean.OddnumerDiy;
import com.yiya.bean.ZypmdzParam;
import com.yiya.model.OddnumerDiyModel;
import com.yiya.service.OddnumerDiyService;
import com.yiya.utils.ActionUpload;
import com.yiya.utils.ExcelUtil;
import com.yiya.utils.HtmlUtil;
import com.yiya.exception.ServiceException;
/**
 * OddnumerDiy.
 * @author knight
 */
@Controller
@RequestMapping("/oddnumerDiy") 
public class OddnumerDiyAction extends BaseAction {
	
	private final static Logger log= Logger.getLogger(MainAction.class);
	private String savePath = "tempfile";
	
	// Servrice start
	@Autowired(required=false) 
	private OddnumerDiyService<OddnumerDiy> oddnumerDiyService; 
	
	/**
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(OddnumerDiyModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<OddnumerDiy> dataList = oddnumerDiyService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("kh/oddnumerDiy",context); 
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(OddnumerDiyModel model,HttpServletResponse response) throws Exception{
		List<OddnumerDiy> dataList = oddnumerDiyService.queryByList(model);
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
	public void save(OddnumerDiy bean,HttpServletResponse response) throws Exception{
		OddnumerDiy oddnumerDiy = oddnumerDiyService.queryById(bean.getId());
		if(bean.getId() == null || bean.getId().equals("")){
			if(oddnumerDiy != null){
				throw new ServiceException("数据已存在.");
			}
			oddnumerDiyService.add(bean);
		}else{
			oddnumerDiyService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		OddnumerDiy bean  = oddnumerDiyService.queryById(id);
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
		oddnumerDiyService.delete(id);
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
		List<OddnumerDiy> numdiylist = new ArrayList<OddnumerDiy>();
		ActionUpload upload = new ActionUpload();
		//1、保存上传文件
		request.setAttribute("type", "numdiy");
		String filename = upload.uploadFile(request, savePath);
		//2、读取execl内容封装成对象
		List<ArrayList<String>> llist = new ExcelUtil().read(request.getSession().getServletContext().getRealPath("/")+savePath+"/"+filename);
		OddnumerDiy numdiy;
		for(List<String> model:llist){
			if("".equals(model.get(0)))
				continue;
			numdiy = new OddnumerDiy();
			numdiy.setNum(model.get(0));
			numdiy.setType(new BigDecimal(model.get(1)).toBigInteger().intValue());
			numdiy.setUseuserid(model.get(2));
			numdiy.setStatus(0);
			numdiylist.add(numdiy);
		}
		//3、批量插入
		if(numdiylist.size()>0){
			oddnumerDiyService.insertbatch(numdiylist);
			//删除单号重复的数据
			//oddnumerDiyService.deleteDouble();
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
		String filepath = request.getSession().getServletContext().getRealPath("/")+"temples/OddNumberDiyTemple.xls";
		String fileName = "自定义报关单号导入模板.xls";
		ActionUpload upload = new ActionUpload();
		upload.downFiel(fileName, filepath, response);
	}
}