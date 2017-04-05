<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/view/resource.jsp"%>
</head>
<body>
	<div class="warp easyui-panel" data-options="border:false">
		<!-- Search panel start -->
		<div class="easyui-panel ui-search-panel" title="税率信息表查询"
			data-options="striped: true,collapsible:true,iconCls:'icon-search'">
			<form id="searchForm">
				<p class="ui-fields">
					<label class="ui-label">税号:</label><input name="taxnum"
						class="easyui-box ui-text" style="width: 100px;"> <label
						class="ui-label">税率:</label><input name="rate"
						class="easyui-box ui-text" style="width: 100px;"> 
					<!-- <script>
					var myloader = function() {
						var url = '../khUser/dataList.do';
						YiYa.ajaxJson(url,null,function(data){
							var items = $.map(
									data.rows,
									function(item) {
										return {
											id : item.userid,
											name : item.username
										};
									});
							return items;
						});
					}
				</script> -->
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
					<div class="ftitle">税率信息表修改</div>
					<div class="fitem">
						<label>税号:</label> <input class="easyui-validatebox" type="text"
							name="taxnum" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
					<div class="fitem">
						<label>税率:</label> <input class="easyui-validatebox" type="text"
							name="rate" missingMessage="该项不能为空"
							data-options="required:true,validType:'length[1,11]'">
					</div>
				</div>
			</form>
		</div>

		<!-- file Form -->
		<div id="file-win" class="easyui-dialog" title="文件上传"
			data-options="closed:true,iconCls:'icon-save',modal:true"
			style="width: 350px; height: 250px; padding: 30px 60px 50px 60px">
			<div>
				<a href="export.do">导入数据模板下载，请点击此处...</a>
			</div>
			<form id="fileForm" class="ui-form" method="post"
				enctype="multipart/form-data" style="padding: 10px 0px">
				<div style="margin-bottom: 20px">
					<span>导入文件:</span><input type="file" name="uploadify"
						id="multiple_file_upload" /><span id="progress"></span>
				</div>
			</form>
		</div>

	</div>

	<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
	<script type="text/javascript" src="${msUrl}/js/ux/kh/taxRateInfo.js"></script>
</body>
</html>
