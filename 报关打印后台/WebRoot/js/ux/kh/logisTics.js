$package('YiYa.logisTics');
YiYa.logisTics = function(){
	var _box = null;
	var _this = {
			config:{
				dataGrid:{
					title:'运单信息列表',
					url:'dataList.do',
					columns:[[
					          {field:'id',checkbox:true},
					          {field:'importnum',title:'总运单号',width:80,sortable:true},
					          {field:'serialnum',title:'导入序号',width:60,sortable:true},
					          {field:'importuser',title:'导入用户',width:60,sortable:true},
					          {field:'consigneecountry',title:'包裹状态',width:60,color:'red',ortable:true},
					          {field:'declarenum',title:'报关单号',width:80,sortable:true},
					          {field:'expressnum',title:'分运单号',width:80,sortable:true},
					          {field:'createtime',title:'导入时间',width:64,sortable:true},
					          {field:'isprint',title:'打印状态',width:60,align:'center',sortable:true,styler:function(value,row,index){
					        	  if(value == 1){
					        		  return 'color:red;';  
					        	  }
					          },
					          formatter:function(value,row,index){
					        	  if(value == 0){
					        		  return "未打印";
					        	  }
					        	  if(value == 1){
					        		  return "已打印";
					        	  }
					          }},
					          {field:'consigneename',title:'收件人名称',width:80,sortable:true},
					          {field:'consigneeaddr',title:'收件人地址(省市详细地址按“|”符号分隔)',width:200,sortable:true},
					          {field:'consigneephone',title:'收件人电话',width:80,sortable:true},
					          {field:'consignercardid',title:'身份证证件号',width:120,sortable:true},
					          {field:'brand',title:'品牌',width:80,sortable:true},
					          {field:'cargoname',title:'货品名称',width:80,sortable:true},
					          {field:'cargotype',title:'规格型号',width:80,sortable:true},
					          {field:'declareweight',title:'毛重（KG）',width:80,sortable:true},
					          {field:'declareprice',title:'货品单价',width:80,sortable:true},
					          {field:'declarepricesum',title:'货品总价',width:80,sortable:true},
					          {field:'legalnum',title:'第一(法定)数量',width:80,sortable:true},
					          {field:'legalunit',title:'第一(法定)单位',width:80,sortable:true},
					          {field:'netweight',title:'净重（KG',width:80,sortable:true},
					          {field:'cargoid',title:'商品编号',width:80,sortable:true},
					          {field:'packagecount',title:'包裹数量',width:80,sortable:true},
					          {field:'count',title:'件数',width:80,sortable:true},
					          {field:'consignername',title:'发件人名称',width:80,sortable:true},
					          {field:'consigneraddr',title:'发件人地址',width:80,sortable:true},
					          {field:'consignerphone',title:'发件人电话',width:80,sortable:true},
					          {field:'consignercountry',title:'发件人国家',width:80,sortable:true}
					          ]],
					          toolbar:[
					                   {id:'btnadd',text:'新增',btnType:'add'},
					                   {id:'btnedit',text:'信息修改',btnType:'edit'},
					                   {id:'btndelete',text:'删除',btnType:'remove'},
					                   {id:'btnimports',text:'包裹状态批量修改',btnType:'imports'}
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
	YiYa.logisTics.init();
});	