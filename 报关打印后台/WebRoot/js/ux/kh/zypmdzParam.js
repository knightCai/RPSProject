$package('YiYa.zypmdzParam');
YiYa.zypmdzParam = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'中英品名信息列表',
	   			url:'dataList.do',
	   			columns:[[
									{field:'id',checkbox:true},
												{field:'chainname',title:'中文品名',width:80,sortable:true},
												{field:'englishname',title:'英文品名',width:80,sortable:true},
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
	YiYa.zypmdzParam.init();
});	