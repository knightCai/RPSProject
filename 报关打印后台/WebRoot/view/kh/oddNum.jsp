<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
   <script type="text/javascript">
	   /**
	    * 总数修改事件
	    */
	   function numchange(){
	   	var startnum = $("#startnum")[0].value;
	   	var amountnum = $("#amountnum")[0].value;
	   	if(startnum != ''){
	   		$("#endnum").val(Number(startnum)+Number(amountnum)-1);
	   	}
	   }
   </script>
  </head>
	<body>
<div class="warp easyui-panel" data-options="border:false">
	<!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="报关单号查询" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
 	 	    <label class="ui-label">使用客户:</label><select class="easyui-combobox"
						style="width: 126px;" name="userid"
						data-options="panelHeight:'auto',valueField:'userid',textField:'username',url:'../khUser/dataListforSer.do'">
					</select>
            <!-- <label class="ui-label">员工姓名: </label><input name="nickName" class="easyui-box ui-text" style="width:100px;"> -->
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- DataList  -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
     </form>

     <!-- Edit Form -->
     <div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     		 <input class="hidden" name="deleted">
     		 <input class="hidden" type="text" name="usenum">
     		 <div class="ui-edit">
	     	   <div class="ftitle">单号信息修改</div>    
	           <div class="fitem">  
	               <label>起始号码:</label>  
	               <input class="easyui-validatebox" type="text" name="startnum" id="startnum"  onchange="numchange()" missingMessage="该项不能为空" data-options="required:true,validType:'length[1,11]'">
	           </div>  
               <div class="fitem">  
	               <label>结束号码:</label>  
	               <input class="easyui-validatebox" type="text" name="endnum" id="endnum" readonly=”readonly” style="background-color: WhiteSmoke" missingMessage="该项不能为空" data-options="required:true">
	           </div> 
	           <div class="fitem">  
	               <label>总数:</label>  
	               <input class="easyui-numberbox" type="text" name="amountnum" id="amountnum" value="1" onchange="numchange()" missingMessage="该项不能为空" data-options="required:true,validType:'length[1,11]'">
	           </div> 
               <div class="fitem">  
	               <label>状态:</label>  
	               <select class="easyui-combobox" name="state" missingMessage="该项不能为空" data-options="required:true">
                    	<option value="0" selected="selected">可用</option>
                    	<option value="1">禁用</option>
                   	</select>
	           </div> 
	           <div class="fitem">  
	               <label>剩余提醒:</label>  
	               <input class="easyui-numberbox" type="text" name="warnnum" value = "0" data-options="required:false,validType:'length[1,10]'">
	           </div>
	           <div class="fitem">  
	               <label>使用客户:</label>  
	               <select class="easyui-combobox"
						style="width: 126px;" name="userid"
						data-options="required:false,panelHeight:'auto',valueField:'userid',textField:'username',url:'../khUser/dataListforSer.do'">
					</select>
	           </div>
	         </div>
     	</form>
  	 </div> 
  	 
</div>

<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/kh/oddNum.js"></script>
  </body>
</html>
