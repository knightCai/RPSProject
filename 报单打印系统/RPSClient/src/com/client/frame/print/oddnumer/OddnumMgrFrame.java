package com.client.frame.print.oddnumer;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.client.common.ExcelUtil;
import com.client.common.FrameUtil;
import com.client.model.contrllo.LogisticsListContrllo;
import com.service.service.impl.Logisticslisting;

public class OddnumMgrFrame {

	protected Shell shell;
	private Text text;
	private String[] resultStr;
	private String msg;
	private Spinner spinner;	//导入批次号
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OddnumMgrFrame window = new OddnumMgrFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(483, 320);
		shell.setText("导入数据...");
		FrameUtil.center(shell);
		Label label = new Label(shell, SWT.NONE);
		label.setText("导入数据模板下载，请点击...");
		label.setBounds(21, 25, 208, 20);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(235, 20, 98, 30);
		btnNewButton.setText("下载模板");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(21, 92, 90, 20);
		lblNewLabel.setText("导入批次号：");
		
		spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(117, 89, 97, 26);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(21, 135, 90, 20);
		lblNewLabel_1.setText("请选择文件：");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(117, 132, 208, 26);
		
		Button button = new Button(shell, SWT.NONE);
		button.setBounds(331, 130, 98, 30);
		button.setText("浏览...");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setBounds(117, 199, 98, 30);
		button_1.setText("确定");
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setBounds(253, 199, 98, 30);
		button_2.setText("取消");

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog filedia = new FileDialog(shell, SWT.SINGLE);
				text.setText(filedia.open());
			}
		});
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				List<ArrayList<String>> list = new ExcelUtil().read(text.getText());
				List<Logisticslisting> llist = packgeLogistics(list);
				String result = new LogisticsListContrllo().saveBatch(llist);
				resultStr = result.split(",");
				if(resultStr[0].equals("999")){
					msg = "数据导入失败！异常原因：" + resultStr[1];
				}else{
					msg = "数据导入成功！";
				}
				MessageDialog.openInformation(shell,"系统提示",msg);
			}
		});
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
	 */
	public List<Logisticslisting> packgeLogistics(List<ArrayList<String>> list){
		List<Logisticslisting> llist = new ArrayList<Logisticslisting>();
		Logisticslisting logis;
		int i = 1;
		for(List temp:list){
			if(i==1 || temp.get(1).toString().equals("")){
				i++;
				continue;
			}
			/*logis = new  Logisticslisting();
			logis.setPkid(FrameUtil.getUUID());
			logis.setDeclarenum(temp.get(0).toString());
		  	logis.setExpressnum(temp.get(0).toString());
			logis.setCargoname(temp.get(1).toString());
			logis.setDeclaretype(temp.get(2).toString());
			logis.setPaytype(temp.get(3).toString());
			logis.setDeclarecount(temp.get(4).toString());
			logis.setDeclareweight(temp.get(5).toString());
			logis.setDeclarevalue (temp.get(6).toString());
			logis.setCurrency(temp.get(7).toString());
			logis.setConsigneename(temp.get(8).toString());
			logis.setConsigneeaddr(temp.get(9).toString());
			logis.setConsigneephone(temp.get(10).toString());
			logis.setConsignercardtype(temp.get(11).toString());
			logis.setConsignercardid(temp.get(12).toString());
			logis.setConsignername(temp.get(13).toString());
			logis.setConsignercity (temp.get(14).toString());
		   	logis.setConsigneraddr(temp.get(15).toString());
		   	logis.setConsignerphone (temp.get(16).toString());
		   	logis.setFromcountry(temp.get(17).toString());
		   	logis.setConsigneecountry(temp.get(18).toString());
		   	logis.setConsignercountry(temp.get(19).toString());
		   	logis.setCargoid(temp.get(20).toString());
		   	logis.setCargotype(temp.get(21).toString());
		   	logis.setCount(temp.get(22).toString());
		   	logis.setWeight(temp.get(23).toString());
		   	logis.setUnit(temp.get(24).toString());
		   	logis.setTrademode(temp.get(25).toString());
		   	logis.setPacktype(temp.get(26).toString());
		   	logis.setNetweight(temp.get(41).toString());
		   	logis.setLegalnum(temp.get(45).toString());
		   	logis.setIsprint("0");
	   		logis.setImportnum(spinner.getText());
	   		llist.add(logis);*/
		}
		return llist;
	}
}
