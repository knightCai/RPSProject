$package('YiYa.taxRateInfo');
YiYa.taxRateInfo = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'税率信息表列表',
	   			url:'dataList.do',
	   			columns:[[
									{field:'id',checkbox:true},
												{field:'taxnum',title:'税号',width:80,sortable:true},
												{field:'rate',title:'税率',width:80,sortable:true},
												/*{field:'mmo1',title:'预留字段1',width:80,sortable:true},
												{field:'mmo2',title:'预留字段2',width:80,sortable:true},*/
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
	YiYa.taxRateInfo.init();
});	