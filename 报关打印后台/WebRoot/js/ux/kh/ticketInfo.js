$package('YiYa.ticketInfo');
YiYa.ticketInfo = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'小票货品名转译列表',
	   			url:'dataList.do',
	   			columns:[[
									{field:'id',checkbox:true},
												{field:'sernum',title:'序号',width:80,sortable:true},
												{field:'bino',title:'提单号',width:80,sortable:true},
												{field:'delnum',title:'订单号',width:80,sortable:true},
												{field:'wpname',title:'物品名称',width:80,sortable:true},
												{field:'brand',title:'品牌',width:80,sortable:true},
												{field:'spec',title:'规格',width:80,sortable:true},
												{field:'count',title:'数量',width:80,sortable:true},
												{field:'price',title:'购买单价',width:80,sortable:true},
												{field:'currency',title:'币种',width:80,sortable:true},
												{field:'discount',title:'折扣金额',width:80,sortable:true},
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
	YiYa.ticketInfo.init();
});	