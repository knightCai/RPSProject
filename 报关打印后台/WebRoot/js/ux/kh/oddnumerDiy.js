$package('YiYa.oddnumerDiy');
YiYa.oddnumerDiy = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'自定义报关单号列表',
	   			url:'dataList.do',
	   			columns:[[
									{field:'id',checkbox:true},
												{field:'num',title:'报关单号',width:80,sortable:true},
												{field:'type',title:'单号类型',width:80,sortable:true,formatter:function(value,row,index){
													if(value == 0){
														return "国内";
													}
													if(value == 1){
														return "陕西省";
													}
												}},
												{field:'status',title:'状态',width:80,align:'center',sortable:true,styler:function(value,row,index){
													if(value == 1){
													  return 'color:red;';  
													}
												},
												formatter:function(value,row,index){
													if(value == 0){
														return "未使用";
													}
													if(value == 1){
														return "已使用";
													}
												}},
												{field:'useuserid',title:'使用用户',width:80,sortable:true},
									]],
				toolbar:[
							{id:'btnadd',text:'新增',btnType:'add'},
							{id:'btnedit',text:'信息修改',btnType:'edit'},
							{id:'btndelete',text:'删除',btnType:'remove'},
							{id:'btnimports',text:'批量导入',btnType:'imports'}
						]
					}
				},
				init:function(){
					//_this.initForm();
					_box = new YDataGrid(_this.config); 
					_box.init();
				}
			}
			return _this;
		}();

$(function(){
	YiYa.oddnumerDiy.init();
});	