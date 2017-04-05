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
import com.yiya.bean.SysMenu;
import com.yiya.bean.SysRole;
import com.yiya.bean.SysRoleRel;
import com.yiya.bean.KhUser;
import com.yiya.model.SysMenuModel;
import com.yiya.model.KhUserModel;
import com.yiya.service.OddNumService;
import com.yiya.service.SysRoleService;
import com.yiya.service.KhUserService;
import com.yiya.utils.HtmlUtil;
import com.yiya.utils.MethodUtil;
import com.yiya.utils.SessionUtils;
import com.yiya.bean.KhUser;
import com.yiya.bean.BaseBean.DELETED;
import com.yiya.bean.BaseBean.STATE;
import com.yiya.exception.ServiceException;
import com.yiya.model.KhUserModel;
 
@Controller
@RequestMapping("/khUser") 
public class KhUserAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(KhUserAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private KhUserService<KhUser> khUserService; 
	private OddNumService<OddNum> oddNumService;
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(KhUserModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		List<KhUser> dataList = khUserService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("kh/khUser",context); 
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(KhUserModel model,HttpServletResponse response) throws Exception{
		List<KhUser> dataList = khUserService.queryByList(model);
		//设置页面数据
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	/**
	 * 用户信息下拉框
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/dataListforSer") 
	public void  dataListforSer(KhUserModel model,HttpServletResponse response) throws Exception{
		List<KhUser> dataList = khUserService.queryByList();
		HtmlUtil.writerJson(response, dataList);
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(KhUser bean,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		List<KhUser> khUser = khUserService.getUserCountByUserid(bean.getUserid());
		if(bean.getId() == null || bean.getId().equals("")){
			if(khUser.size() > 0){
				throw new ServiceException("用户已存在.");
			}
			bean.setState(DELETED.NO.key+"");
			bean.setPassword(MethodUtil.MD5(bean.getUserid()+"123456"));
			bean.setId(UUID.randomUUID().toString().replace("-", ""));
			khUserService.add(bean);
		}else{
			if(khUser.size() > 1){
				throw new ServiceException("用户已存在.");
			}
			khUserService.updateBySelective(bean);
			if(!khUser.get(0).getUserid().equalsIgnoreCase(bean.getUserid())){
				//oddNumService.getMapper().updateBySelective(t)update(t);
			}
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(String id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		KhUser bean  = khUserService.queryById(id);
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
		khUserService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updatePwd")
	public void updatePwd(String id,String oldPwd,String newPwd,HttpServletRequest request,HttpServletResponse response) throws Exception{
		boolean isAdmin = SessionUtils.isAdmin(request); //是否超级管理员
		KhUser bean  = khUserService.queryById(id);
		if(bean.getId() == null || DELETED.YES.key == Integer.parseInt(bean.getState())){
			sendFailureMessage(response, "抱歉，用户不存在！");
			return;
		}
		if(StringUtils.isBlank(newPwd)){
			sendFailureMessage(response, "密码不能为空");
			return;
		}
		//不是超级管理员，匹配旧密码
		if(!isAdmin && !MethodUtil.ecompareMD5(bean.getUserid()+oldPwd,bean.getPassword())){
			sendFailureMessage(response, "原密码错误！");
			return;
		}
		bean.setPassword(MethodUtil.MD5(bean.getUserid()+newPwd));
		khUserService.update(bean);
		sendSuccessMessage(response, "保存成功~");
	}
	

	/**
	 * 查询用户信息
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getUser") 
	public void getUser(String id,HttpServletResponse response)  throws Exception{
		Map<String,Object>  context = getRootMap();
		KhUser bean  = khUserService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", bean.getId());
		data.put("userid", bean.getUserid());
		data.put("username", bean.getUsername());
		data.put("state", bean.getState());
		context.put(SUCCESS, true);
		context.put("data", data);
		HtmlUtil.writerJson(response, context);
		
	}
	
}
