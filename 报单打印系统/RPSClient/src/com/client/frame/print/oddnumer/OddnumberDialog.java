package com.client.frame.print.oddnumer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.client.common.ExcelUtil;
import com.client.common.FileUtil;
import com.client.common.FrameUtil;
import com.client.common.GlobalParam;
import com.client.model.contrllo.LogisticsListContrllo;
import com.client.model.contrllo.OddnumberContrllo;
import com.client.model.contrllo.OddnumerDiyContrllo;
import com.service.service.Logisticslisting;
import com.service.service.Oddnumber;
import com.service.service.OddnumerDiy;

import org.eclipse.wb.swt.SWTResourceManager;

public class OddnumberDialog extends Dialog {

	protected Object result = "false";
	protected Shell shell;
	private Text text;
	private String[] resultStr;
	private String msg;
	private LogisticsListContrllo logiscor;	//运单接口处理类
	private OddnumberContrllo oddnumcor;	//报关单号接口处理类
	private OddnumerDiyContrllo oddnumDiycor;	//报关单号接口处理类
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OddnumberDialog(Shell parent, int style) {
		super(parent, style);
		logiscor = new LogisticsListContrllo();
		oddnumcor = new OddnumberContrllo();
		oddnumDiycor = new OddnumerDiyContrllo();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(483, 320);
		shell.setBackgroundImage(new  Image(shell.getDisplay(), this.getClass().getResourceAsStream(GlobalParam.SOURCE_CS1)));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setText("导入数据...");
		
		FrameUtil.center(shell);
		Label label = new Label(shell, SWT.NONE);
		label.setText("导入数据模板下载，请点击...");
		label.setBounds(21, 74, 208, 20);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(235, 69, 98, 30);
		btnNewButton.setText("下载模板");
		
		/*Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(21, 92, 90, 20);
		lblNewLabel.setText("导入批次号：");
		
		String max = logiscor.findMaxImportnum("admin");
		max = max==null?"0":max;
		spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(117, 89, 97, 26);
		spinner.setMinimum(Integer.parseInt(max)+1);
		spinner.setMaximum(10000000);*/
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(21, 121, 90, 20);
		lblNewLabel_1.setText("请选择文件：");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(117, 118, 208, 26);
		
		Button button = new Button(shell, SWT.NONE);
		button.setBounds(331, 116, 98, 30);
		button.setText("浏览...");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setBounds(117, 199, 98, 30);
		button_1.setText("确定");
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setBounds(253, 199, 98, 30);
		button_2.setText("取消");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setBounds(21, 161, 408, 20);
		lblNewLabel.setText("*请在导入模板(xls文件)的基础上添加数据，请勿使用公式。");
		
		//下载模板按钮
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					DirectoryDialog dd=new DirectoryDialog(shell);  
					dd.setMessage("setMessage");  
					dd.setText("setText");  
					dd.setFilterPath("C://");  
					String saveFile=dd.open();
					String sourcepath = GlobalParam.SYSTEM_USER.getType() == 3 ? 
							GlobalParam.SOURCE_IMPORTTEMPLEYTO : 
							GlobalParam.SYSTEM_USER.getType() == 2?
							GlobalParam.SOURCE_IMPORTTEMPLEDIY:GlobalParam.SOURCE_IMPORTTEMPLE;
					boolean flg= FileUtil.downloadLocal("报关单导入模板.xls",saveFile,sourcepath);
					if(flg){
						MessageDialog.openInformation(shell, "系统提示", "模板下载成功!保存路径为："+saveFile+"/报关单导入模板.xls");
					}else{
						MessageDialog.openError(shell, "系统提示", "模板下载失败！");
					}
				 }catch(Exception ex){
					 System.out.print("创建失败");
				 }  
			}
		});
		
		//导入文件按钮
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog filedia = new FileDialog(shell, SWT.SINGLE);
				String filepath = filedia.open()+"";
				text.setText(filepath.replace("null",""));
			}
		});
		//确定导入
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					List<ArrayList<String>> list = new ExcelUtil().read(text.getText());
					List<Logisticslisting> llist;
					llist = packgeLogistics(list);
					//查询该总运单号是否已存在
					List<String> param = new ArrayList<String>();
					param.add("importuser:"+GlobalParam.SYSTEM_LOGINUSER);
					param.add("importnum:"+llist.get(0).getImportnum());
					param.add("serialnum<5 and '1':1");
					if(logiscor.findLlistByParams(param).size()>0){
						throw new Exception("总运单号：【"+llist.get(0).getImportnum()+"】系统中已导入，请修改总运单号！");
					}
					if(GlobalParam.SYSTEM_USER.getType() != 1 && GlobalParam.SYSTEM_USER.getType() != 3){
						int oddcount = 0;
						//根据用户类型查询单号 1-系统 2-自定义
						if(GlobalParam.SYSTEM_USER.getType() == 1){
							List<Oddnumber> oddnums = oddnumcor.findAllOddnum(GlobalParam.SYSTEM_LOGINUSER);
							for(Oddnumber oddnum:oddnums){
								if(oddnum.getState().equals("0")){
									oddcount = oddcount+oddnum.getSuplusnum();
								}
							}
						}else{
							List<OddnumerDiy> oddnums = oddnumDiycor.findAllOddnumDiy("");
							int type0,type1 = type0= 0;
							for(OddnumerDiy odddiy:oddnums){
								if(odddiy.getType()==0)
									type0++;
								else
									type1++;
							}
							oddcount = type0 >= type1 ?type0:type1;
						}
						Map<String, String> numMap = new HashMap<String, String>();
						//通过报关单号及序号筛选同一物流包裹
						//key：序号+运单号 value：后续存放 报关单号
						for (Logisticslisting logis : llist) {
							numMap.put(logis.getSerialnum()+logis.getExpressnum(),null );
						}
						if(oddcount<numMap.size()){
							MessageDialog.openError(shell,"系统提示","系统库存报关单号数量少于货物导入数量！请先添加报关单号再进行导入操作！");
							shell.dispose();
							return;
						}
					}
					String result = logiscor.saveBatch(llist);
					resultStr = result.split(",");
					if(resultStr[0].equals("999")){
						msg = "数据导入失败！异常原因：" + resultStr[1];
					}else{
						msg = "数据导入成功！";
						result = "true";
					}
				} catch (Exception e) {
					e.printStackTrace();
					msg = "数据导入失败！异常原因：" + e.getMessage();
					FrameUtil.isError_systemmusic();
				}
				MessageDialog.openInformation(shell,"系统提示",msg);
			}
		});
		//取消
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
		});
	}
	/**
	 * 组装execl解析后的数据为Logistics集合
	 * 跳过第一行列名以及没有运单号的数据
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	public List<Logisticslisting> packgeLogistics(List<ArrayList<String>> list) throws Exception{
		List<Logisticslisting> llist = new ArrayList<Logisticslisting>();
		Logisticslisting logis;
		Logisticslisting logistemp = new Logisticslisting();
		List<String> explist = new ArrayList<String>();
		int i = 1;
		try{
			for(List temp:list){
				int j = 0;
				if(temp.get(0).toString().equals("")&&temp.get(1).toString().equals("")&&temp.get(2).toString().equals("")){
					if(!temp.get(3).toString().equals("")||!temp.get(4).toString().equals("")){
						throw new Exception("总运单号不能为空!");
					}
		   			continue;
		   		}
				if(temp.get(1).toString().equals("")&&!temp.get(3).toString().equals("")){
					throw new Exception("总运单号不能为空!");
				}
				logis = new  Logisticslisting();
				logis.setPkid(FrameUtil.getUUID());
				logis.setDeclarenum("");
				logis.setIsprint("0");
				logis.setImportuser(GlobalParam.SYSTEM_LOGINUSER);
				logis.setCreatetime(FrameUtil.convertToXMLGregorianCalendar(new Date()));
				if(GlobalParam.SYSTEM_USER.getType() == 2){//自定义用户导入
					logis.setSerialnum(temp.get(j++).toString());
					logis.setImportnum(temp.get(j++).toString());
					logis.setExpressnum(temp.get(j++).toString());
					logis.setCargoname(temp.get(j++).toString());
					logis.setBrand(temp.get(j++).toString());
					logis.setDeclareweight(temp.get(j++).toString());
					logis.setConsigneename(temp.get(j++).toString());
					logis.setConsigneephone(temp.get(j++).toString());
					//地址内容：收货人省份+收货人城市+收货人详细地址
					logis.setConsigneeaddr(temp.get(j++).toString()+"|"+ temp.get(j++).toString() +"|"+temp.get(j++).toString());
					logis.setConsignercardid(temp.get(j++).toString());
					//验证净重数据格式是否正确
					String flagNetWeight = logis.getDeclareweight();
					if(ExcelUtil.isZM(flagNetWeight)){
						throw new Exception("重量数据，请检查是否引用格式!");
					}
				}else{
					logis.setSerialnum(temp.get(j++).toString());
					logis.setImportnum(temp.get(j++).toString());
					logis.setExpressnum(temp.get(j++).toString());
					logis.setBrand(temp.get(j++).toString());
					logis.setCargoname(temp.get(j++).toString());
					logis.setCargotype(temp.get(j++).toString());
					logis.setDeclareweight(temp.get(j++).toString());
					logis.setDeclareprice(temp.get(j++).toString());
					logis.setDeclarepricesum(temp.get(j++).toString());
					if(GlobalParam.SYSTEM_USER.getType() == 3){//圆通导入模板
						logis.setPackagecount(temp.get(j++).toString());
						logis.setNetweight(temp.get(j++).toString());
					}else{//系统用户导入模板
						logis.setLegalnum(temp.get(j++).toString());
						logis.setNetweight(temp.get(j++).toString());
						logis.setPackagecount(temp.get(j++).toString());
						logis.setCount(temp.get(j++).toString());
						//判断是否为整数
						/*if(!logis.getLegalnum().matches("^\\d+$$")){
							throw new Exception("第一法定数量必须为整数!");
						}*/
					}
					logis.setConsigneename(temp.get(j++).toString());
					//地址内容：收货人省份+收货人城市+收货人详细地址
					logis.setConsigneeaddr(temp.get(j++).toString()+"|"+ temp.get(j++).toString() +"|"+temp.get(j++).toString());
					logis.setConsigneephone(temp.get(j++).toString());
					logis.setConsignercardid(temp.get(j++).toString());
					logis.setConsignername(temp.get(j++).toString());
					logis.setConsigneraddr(temp.get(j++).toString());
					logis.setConsignerphone(temp.get(j++).toString());
					logis.setConsignercountry(temp.get(j++).toString());
					//用户类型为3的模板相同订单只在第一条数据有值
					if(logis.getSerialnum().equals("")){
						logis.setSerialnum(logistemp.getSerialnum());
						logis.setExpressnum(logistemp.getExpressnum());
						logis.setConsigneename(logistemp.getConsigneename());
						logis.setConsigneeaddr(logistemp.getConsigneeaddr());
						logis.setConsigneephone(logistemp.getConsigneephone());
						logis.setConsignercardid(logistemp.getConsignercardid());
					}else if(GlobalParam.SYSTEM_USER.getType() == 3){
						String exptmp = logis.getConsignercardid();
						for(String exp:explist){
							if(exp.equals(exptmp)){
								throw new Exception(exp+"身份证号码重复!");
							}
						}
						explist.add(exptmp);
					}
					//验证净重数据格式是否正确
					String flagNetWeight = logis.getNetweight();
					if(ExcelUtil.isZM(flagNetWeight)){
						throw new Exception("净重数据，请检查是否引用格式!");
					}
			   		//类型为3的（圆通）用户不判断毛重
			   		if(GlobalParam.SYSTEM_USER.getType() != 3){
				   		if(Double.parseDouble(logis.getNetweight()) > Double.parseDouble(logis.getDeclareweight())){
				   			throw new Exception("净重不能大于毛重!");
				   		}else if(!(Double.parseDouble(logis.getNetweight())>0)){
				   			throw new Exception("净重不能为0或小于0!");
				   		}
			   		}
			   		if(!Pattern.compile("^\\d{15}|\\d{17}[0-9A-Z]$").matcher(logis.getConsignercardid()).matches()){
			   			throw new Exception("身份证格式不正确!");
			   		}
				}
		   		logistemp = logis;
		   		llist.add(logis);
		   		i++;
			}
		}catch (NumberFormatException e) {
			throw new Exception("第【"+ i+"】行数据格式有误，请检查："+e.getMessage());
		}catch (Exception e) {
			throw new Exception("第【"+ i+"】行数据有误："+e.getMessage());
		}
		if(llist.size()==0){
			throw new Exception("没有找到需要导入的数据！");
		}
		return llist;
	}
}
