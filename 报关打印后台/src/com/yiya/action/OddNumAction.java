package com.yiya.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.yiya.bean.OddNum;
import com.yiya.bean.SysRole;
import com.yiya.model.OddNumModel;
import com.yiya.service.OddNumService;
import com.yiya.utils.HtmlUtil;
import com.yiya.utils.SessionUtils;
import com.yiya.bean.BaseBean.DELETED;
import com.yiya.exception.ServiceException;
 
@Controller
@RequestMapping("/oddNum") 
public class OddNumAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(OddNumAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private OddNumService<OddNum> oddNumService; 
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(OddNumModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<OddNum> dataList = oddNumService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("kh/oddNum",context); 
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(OddNumModel model,HttpServletResponse response) throws Exception{
		List<OddNum> dataList = oddNumService.queryByList(model);
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
	public void save(OddNum bean,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		checkNum(bean);
		if(bean.getId() == null || bean.getId().equals("")){
			bean.setState(DELETED.NO.key+"");
			bean.setUsenum("");
			bean.setId(UUID.randomUUID().toString().replace("-", ""));
			bean.setSuplusnum(bean.getAmountnum());
			oddNumService.add(bean);
		}else{
			if(bean.getUsenum().equals("")){
				bean.setSuplusnum(bean.getAmountnum());
			}else{
				Long tmpnum = Long.parseLong(bean.getEndnum())-Long.parseLong(bean.getUsenum());
				if(tmpnum<1){
					throw new ServiceException("【结束号码】必须大于已使用单号！");
				}
				bean.setSuplusnum(Integer.parseInt(tmpnum.toString()));
			}
			oddNumService.updateBySelective(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	/**
	 * 检查单号是否重复
	 * @param bean
	 * @throws Exception
	 */
	private void checkNum(OddNum bean) throws Exception{
		//如果单号已存在不允许再次添加
		List<OddNum> oddlist = oddNumService.queryByList(new OddNumModel());
		Long startnumf = Long.parseLong(bean.getStartnum());
		Long endnumf = Long.parseLong(bean.getEndnum());
		String id = bean.getId();
		for(OddNum oddnum1 : oddlist){
			//自身单号校验：起始号码是否修改，若单号已使用则不允许修改
			if(oddnum1.getId().equalsIgnoreCase(id)){
				if(!oddnum1.getStartnum().equals(bean.getStartnum())&&!oddnum1.getUsenum().equals(""))
					throw new ServiceException("已使用单号不允许修改【起始号码】！");
				else
					continue;
			}
			//新单号 1、开始号码<系统起始号码，结束号码>系统起始号码
			//		2、开始号码<系统结束号码，结束号码>系统结束号码
			//		3、开始号码>系统起始号码，结束号码<系统结束号码
			if(startnumf <= Long.parseLong(oddnum1.getStartnum()) && endnumf >= Long.parseLong(oddnum1.getStartnum())
					|| startnumf <= Long.parseLong(oddnum1.getEndnum())&& endnumf >= Long.parseLong(oddnum1.getEndnum())
					|| startnumf >= Long.parseLong(oddnum1.getStartnum())&& endnumf <= Long.parseLong(oddnum1.getEndnum())){
				throw new ServiceException("该单号系统中已存在！");
			}
		}
	}
	/**
	 * 查询单号信息
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		OddNum bean  = oddNumService.queryById(id);
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
		oddNumService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
}
