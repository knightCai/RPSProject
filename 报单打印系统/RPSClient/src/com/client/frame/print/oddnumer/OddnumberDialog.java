package com.client.frame.print.oddnumer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import com.client.model.contrllo.LogisticsListContrllo;
import com.client.model.contrllo.OddnumberContrllo;
import com.service.service.impl.Logisticslisting;
import com.service.service.impl.Oddnumber;

public class OddnumberDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private String[] resultStr;
	private String msg;
	private Spinner spinner;	//导入批次号
	private LogisticsListContrllo logiscor;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OddnumberDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		logiscor = new LogisticsListContrllo();
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
		label.setBounds(21, 25, 208, 20);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(235, 20, 98, 30);
		btnNewButton.setText("下载模板");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(21, 92, 90, 20);
		lblNewLabel.setText("导入批次号：");
		
		String max = logiscor.findMaxImportnum("admin");
		max = max==null?"0":max;
		spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(117, 89, 97, 26);
		spinner.setMinimum(Integer.parseInt(max)+1);
		spinner.setMaximum(10000000);
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
					List<Oddnumber> oddnums = new OddnumberContrllo().findAllOddnum();
					int oddcount = 0;
					for(Oddnumber oddnum:oddnums){
						if(oddnum.getState().equals("0")){
							oddcount = oddcount+oddnum.getSuplusnum();
						}
					}
					if(oddcount<list.size()){
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
						String max = logiscor.findMaxImportnum("admin");
						max = max==null?"0":max;
						spinner.setMinimum(Integer.parseInt(max)+1);
					}
				} catch (Exception e) {
					e.printStackTrace();
					msg = "数据导入失败！异常原因：" + e.getMessage();
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
	 * @throws DatatypeConfigurationException 
	 */
	public List<Logisticslisting> packgeLogistics(List<ArrayList<String>> list) throws DatatypeConfigurationException{
		List<Logisticslisting> llist = new ArrayList<Logisticslisting>();
		Logisticslisting logis;
		int i = 1;
		for(List temp:list){
			if(i==1 || temp.get(1).toString().equals("")){
				i++;
				continue;
			}
			logis = new  Logisticslisting();
			logis.setPkid(FrameUtil.getUUID());
			logis.setDeclarenum(temp.get(1).toString());
			logis.setSerialnum(temp.get(0).toString());
			logis.setExpressnum(temp.get(1).toString());
			logis.setBrand(temp.get(2).toString());
			logis.setCargoname(temp.get(3).toString());
			logis.setCargotype(temp.get(4).toString());
			logis.setDeclareweight(temp.get(5).toString());
			logis.setDeclareprice(temp.get(6).toString());
			logis.setDeclarepricesum(temp.get(7).toString());
			logis.setLegalnum(temp.get(8).toString());
			logis.setLegalunit(temp.get(9).toString());
			logis.setNetweight(temp.get(10).toString());
			logis.setCargoid(temp.get(11).toString());
			logis.setPackagecount(temp.get(12).toString());
			logis.setCount(temp.get(13).toString());
			logis.setConsigneename(temp.get(14).toString());
			//地址内容：收货人省份+收货人城市+收货人详细地址
			logis.setConsigneeaddr(temp.get(15).toString()+ temp.get(16).toString() +temp.get(17).toString());
			logis.setConsigneephone(temp.get(18).toString());
			logis.setConsignercardid(temp.get(19).toString());
			logis.setConsignername(temp.get(20).toString());
			logis.setConsigneraddr(temp.get(21).toString());
			logis.setConsignerphone(temp.get(22).toString());
			logis.setConsignercountry(temp.get(23).toString());
			//logis.setConsigneecountry(temp.get(24).toString());
			logis.setIsprint("0");
			logis.setImportnum(spinner.getText());
			logis.setImportuser("admin");
			logis.setCreatetime(FrameUtil.convertToXMLGregorianCalendar(new Date()));
			//logis.setImportuser(temp.get(0).toString());
	   		llist.add(logis);
		}
		return llist;
	}
}
