<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/view/resource.jsp"%>
</head>
<body>
	<div class="warp easyui-panel" data-options="border:false">
		<!-- Search panel start -->
		<div class="easyui-panel ui-search-panel" title="运单信息查询"
			data-options="striped: true,collapsible:true,iconCls:'icon-search'">
			<form id="searchForm">
				<p class="ui-fields">
					<label class="ui-label">总运单号:</label><input name="importnum"
						class="easyui-box ui-text" style="width: 100px;"><label
						class="ui-label">导入用户:</label><input name="importuser"
						class="easyui-box ui-text" style="width: 100px;"><label
						class="ui-label">包裹状态:</label><input name="consigneecountry"
						class="easyui-box ui-text" style="width: 100px;"><label
						class="ui-label">报关单号:</label><input name="declarenum"
						class="easyui-box ui-text" style="width: 100px;"> <label
						class="ui-label">分运单号:</label><input name="expressnum"
						class="easyui-box ui-text" style="width: 100px;"> <label
						class="ui-label">打印状态:</label><select name="isprint"
						class="easyui-combobox" style="width: 100px;">
						<option selected value="">请选择</option>
						<option value="0">未打印</option>
						<option value="1">已打印</option></select>
				</p>
				<a href="#" id="btn-search" class="easyui-linkbutton"
					iconCls="icon-search">查询</a>
			</form>
		</div>
		<!--  Search panel end -->

		<!-- DataList  -->
		<form id="listForm" method="post">
			<table id="data-list"></table>
		</form>

		<!-- Edit Form -->
		<div id="edit-win" class="easyui-dialog" title="Edit"
			data-options="closed:true,iconCls:'icon-save',modal:true"
			style="width: 400px; height: 300px;">
			<form id="editForm" class="ui-form" method="post">
				<input class="hidden" type="text" name="id"> <input
					class="hidden" name="deleted">
				<div class="ui-edit">
					<div class="ftitle">运单信息修改</div>
					<div class="fitem">
						<label>报关单号:</label> <input class="easyui-validatebox" type="text"
							name="declarenum" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>导入序号:</label> <input class="easyui-validatebox" type="text"
							name="serialnum" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>分运单号:</label> <input class="easyui-validatebox" type="text"
							name="expressnum" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>品牌:</label> <input class="easyui-validatebox" type="text"
							name="brand" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>货品名称:</label> <input class="easyui-validatebox" type="text"
							name="cargoname" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>规格型号:</label> <input class="easyui-validatebox" type="text"
							name="cargotype" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>毛重（KG）:</label> <input class="easyui-validatebox"
							type="text" name="declareweight" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>货品单价:</label> <input class="easyui-validatebox" type="text"
							name="declareprice" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>货品总价:</label> <input class="easyui-validatebox" type="text"
							name="declarepricesum" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>第一(法定)数量:</label> <input class="easyui-validatebox"
							type="text" name="legalnum" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>第一(法定)单位:</label> <input class="easyui-validatebox"
							type="text" name="legalunit" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>净重（KG:</label> <input class="easyui-validatebox"
							type="text" name="netweight" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>商品编号:</label> <input class="easyui-validatebox" type="text"
							name="cargoid" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>包裹数量:</label> <input class="easyui-validatebox" type="text"
							name="packagecount" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>件数:</label> <input class="easyui-validatebox" type="text"
							name="count" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>收件人名称(货主单位名称):</label> <input class="easyui-validatebox"
							type="text" name="consigneename" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>收件人地址:</label> <input class="easyui-validatebox"
							type="text" name="consigneeaddr" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>收件人电话:</label> <input class="easyui-validatebox"
							type="text" name="consigneephone" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>身份证证件号:</label> <input class="easyui-validatebox"
							type="text" name="consignercardid" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>发件人名称:</label> <input class="easyui-validatebox"
							type="text" name="consignername" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>发件人地址:</label> <input class="easyui-validatebox"
							type="text" name="consigneraddr" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>发件人电话:</label> <input class="easyui-validatebox"
							type="text" name="consignerphone" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>发件人国家:</label> <input class="easyui-validatebox"
							type="text" name="consignercountry" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>打印状态0-未打印 1-已打印:</label> <input class="easyui-validatebox"
							type="text" name="isprint" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>导入总运单号（航空班号）:</label> <input class="easyui-validatebox"
							type="text" name="importnum" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<!-- <div class="fitem">
						<label>导入时间:</label> <input class="easyui-validatebox" type="text"
							name="createtime" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div> -->
					<div class="fitem">
						<label>导入用户:</label> <input class="easyui-validatebox" type="text"
							name="importuser" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
				</div>
			</form>
		</div>
		<!-- file Form -->
  	 <div id="file-win" class="easyui-dialog" title="文件上传" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:350px;height:250px;padding:30px 60px 50px 60px">
  	 	<div><a href="export.do">导入更新模板下载，请点击此处...</a></div>
	 	 <form id="fileForm" class="ui-form" method="post" enctype="multipart/form-data" style="padding:10px 0px">  
	 	 	<div style="margin-bottom:20px">
	 	 		<span>导入更新文件:</span><input type="file" name="uploadify" id="multiple_file_upload" /><span id="progress"></span>
			</div>
		</form>
  	 </div>

	</div>

	<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
	<script type="text/javascript" src="${msUrl}/js/ux/kh/logisTics.js"></script>
</body>
</html>
