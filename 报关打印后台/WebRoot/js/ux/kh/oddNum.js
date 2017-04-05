$package('YiYa.oddNum');
YiYa.oddNum = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'报关单号列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'startnum',title:'起始号码',width:120,sortable:true},
						{field:'endnum',title:'结束号码',width:120,sortable:true},
						{field:'suplusnum',title:'剩余数量',width:80,sortable:true},
						{field:'amountnum',title:'总数',width:80,sortable:true},
						{field:'usenum',title:'发放号码',width:120,sortable:true},
						{field:'state',title:'状态',width:80,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							if(value == 0){
								return "可用";
							}
							if(value == 1){
								return "禁用";
							}
						}},
						{field:'warnnum',title:'剩余提醒',width:80,sortable:true},
						{field:'createtime',title:'录入日期',width:80,sortable:true},
						{field:'userid',title:'使用客户',width:120,sortable:true},
				]],
				toolbar:[
							{id:'btnadd',text:'新增',btnType:'add'},
							{id:'btnedit',text:'信息修改',btnType:'edit'},
							{id:'btndelete',text:'删除',btnType:'remove'}
								/*,handler:function(){
								var selected = _box.utils.getCheckedRows();
								for(var i=0;i<selected.length;i++){
									if(selected[i].usenum != ''&&selected[i].usenum != null){
										YiYa.alert('警告','已使用的单号不能删除.','warning');
										return false;
									}
								}
								if ( _box.utils.checkSelectOne(selected)){
									
									$.messager.confirm('Confirm','确定要删除该条数据?',function(r){  
									    if (r){
									    	//var idKey = dataGrid.idField || 'id'; //主键名称
									    	var  data = $("input[name='id']", _this.Form.list ).fieldSerialize(); //序列化字段
									   		YiYa.deleteForm(Action.remove,data,function(result){
												Events.refresh();
												//回调函数
												if(jQuery.isFunction(callback)){
													callback(result);
												}
											});
									    }  
									});
								}
							}}*/
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
	YiYa.oddNum.init();
});	