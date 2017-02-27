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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import com.service.service.Logisticslisting;
import com.service.service.Oddnumber;
import org.eclipse.wb.swt.SWTResourceManager;

public class OddnumberDialog extends Dialog {

	protected Object result = "false";
	protected Shell shell;
	private Text text;
	private String[] resultStr;
	private String msg;
	private LogisticsListContrllo logiscor;	//运单接口处理类
	private OddnumberContrllo oddnumcor;	//报关单号接口处理类
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OddnumberDialog(Shell parent, int style) {
		super(parent, style);
		logiscor = new LogisticsListContrllo();
		oddnumcor = new OddnumberContrllo();
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
					boolean flg= FileUtil.downloadLocal(saveFile);
					if(flg){
						MessageDialog.openInformation(shell, "系统提示", "模板下载成功!保存路径为："+saveFile+"/导入模板.xls");
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
					List<Oddnumber> oddnums = oddnumcor.findAllOddnum(GlobalParam.SYSTEM_LOGINUSER);
					int oddcount = 0;
					for(Oddnumber oddnum:oddnums){
						if(oddnum.getState().equals("0")){
							oddcount = oddcount+oddnum.getSuplusnum();
						}
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
		int i = 1;
		for(List temp:list){
			logis = new  Logisticslisting();
			logis.setPkid(FrameUtil.getUUID());
			logis.setDeclarenum(temp.get(2).toString());
			logis.setSerialnum(temp.get(0).toString());
			logis.setExpressnum(temp.get(2).toString());
			logis.setBrand(temp.get(3).toString());
			logis.setCargoname(temp.get(4).toString());
			logis.setCargotype(temp.get(5).toString());
			logis.setDeclareweight(temp.get(6).toString());
			logis.setDeclareprice(temp.get(7).toString());
			logis.setDeclarepricesum(temp.get(8).toString());
			logis.setLegalnum(temp.get(9).toString());
			//logis.setLegalunit(temp.get(10).toString());
			logis.setNetweight(temp.get(10).toString());
			//logis.setCargoid(temp.get(12).toString());
			logis.setPackagecount(temp.get(11).toString());
			logis.setCount(temp.get(12).toString());
			logis.setConsigneename(temp.get(13).toString());
			//地址内容：收货人省份+收货人城市+收货人详细地址
			logis.setConsigneeaddr(temp.get(14).toString()+"|"+ temp.get(15).toString() +"|"+temp.get(16).toString());
			logis.setConsigneephone(temp.get(17).toString());
			logis.setConsignercardid(temp.get(18).toString());
			logis.setConsignername(temp.get(19).toString());
			logis.setConsigneraddr(temp.get(20).toString());
			logis.setConsignerphone(temp.get(21).toString());
			logis.setConsignercountry(temp.get(22).toString());
			//logis.setConsigneecountry(temp.get(24).toString());
			logis.setIsprint("0");
			logis.setImportnum(temp.get(1).toString());
			logis.setImportuser(GlobalParam.SYSTEM_LOGINUSER);
			logis.setCreatetime(FrameUtil.convertToXMLGregorianCalendar(new Date()));
	   		llist.add(logis);
	   		i++;
	   		if(Double.parseDouble(logis.getNetweight()) > Double.parseDouble(logis.getDeclareweight())){
	   			throw new Exception("第【"+ i+"】行数据有误,净重不能大于毛重!");
	   		}else if(!(Double.parseDouble(logis.getNetweight())>0)){
	   			throw new Exception("第【"+ i+"】行数据有误,净重不能为0或小于0!");
	   		}
		}
		return llist;
	}
}
