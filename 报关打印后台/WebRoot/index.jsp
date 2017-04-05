<%@page import="com.yiya.utils.SessionUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	if(SessionUtils.getUser(request)!=null){
		response.sendRedirect("main/main.shtml");
	}else{
		response.sendRedirect("login.shtml");
	}
%>