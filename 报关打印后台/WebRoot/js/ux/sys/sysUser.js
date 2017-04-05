$package('YiYa.sysUser');
YiYa.sysUser = function(){
	var _box = null;
	var _this = {
		updatePwdAction:'updatePwd.do',
		editPwdForm:function(){
			return $("#pwdForm");
		},
		editPwdWin:function(){
			return $("#edit-pwd-win");
		},
		savePwd:function(){
			if(_this.editPwdForm().form('validate')){
				_this.editPwdForm().attr('action',_this.updatePwdAction);
				YiYa.saveForm(_this.editPwdForm(),function(data){
					_this.editPwdWin().dialog('close');
				});
			 }
		},
		initForm:function(){
			//修改密码
			_this.editPwdWin().find("#btn-pwd-submit").click(function(){
				_this.savePwd();
			});
			_this.editPwdWin().find("#btn-pwd-close").click(function(){	
				$.messager.confirm('Confirm','确定要关闭窗口吗?',function(r){  
				    if (r){  
				     	_this.editPwdWin().dialog('close');
				    }  
				});
			});
		},
		config:{
  			dataGrid:{
  				title:'员工信息列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'email',title:'账号',width:120,sortable:true},
						{field:'nickName',title:'员工姓名',width:80,sortable:true},
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
						{field:'createTime',title:'创建时间',width:120,sortable:true},
						{field:'loginCount',title:'登录次数',align:'right',width:80,sortable:true},
						{field:'loginTime',title:'最后一次登录时间',width:120,sortable:true}
				]],
				toolbar:[
					{id:'btnadd',text:'新增',btnType:'add'},
					{id:'btnedit',text:'信息修改',btnType:'edit'},
					{id:'btnedit',text:'密码修改',btnType:'editPwd',iconCls:'icon-edit',handler:function(){
							var selected = _box.utils.getCheckedRows();
							if ( _box.utils.checkSelectOne(selected)){
								_this.editPwdForm().resetForm();
								_this.editPwdForm().find("input[name='id']").val(selected[0].id);
								_this.editPwdForm().find("input[name='email']").val(selected[0].email);
								_this.editPwdWin().window('open'); 
							}
						}},
					{id:'btndelete',text:'删除',btnType:'remove'}
				]
			}
		},
		init:function(){
			_this.initForm();
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	YiYa.sysUser.init();
});		