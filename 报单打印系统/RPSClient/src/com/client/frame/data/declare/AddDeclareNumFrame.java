package com.client.frame.data.declare;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import com.client.common.FrameUtil;
import com.client.common.GlobalParam;
import com.client.common.TextVerifyListener;
import com.service.service.Oddnumber;
import com.client.model.contrllo.OddnumberContrllo;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;

public class AddDeclareNumFrame {

	protected Shell shell;
	private Text text;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Text text_3;
	private Spinner spinner;
	private Spinner spinner_1;
	private OddnumberContrllo oddnumCon;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddDeclareNumFrame window = new AddDeclareNumFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public AddDeclareNumFrame() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		oddnumCon = new OddnumberContrllo();
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
		shell.setSize(451, 310);
		shell.setText("添加号段");
		FrameUtil.center(shell);
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(42, 10, 76, 20);
		label.setText("起始号码：");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(144, 7, 193, 26);
		text.addVerifyListener(new TextVerifyListener(1));   
		label_1 = new Label(shell, SWT.NONE);
		label_1.setText("发货数量：");
		label_1.setBounds(42, 59, 76, 20);
		
		label_2 = new Label(shell, SWT.NONE);
		label_2.setText("剩余提醒：");
		label_2.setBounds(42, 103, 76, 20);
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setText("结束号码：");
		label_3.setBounds(42, 147, 76, 20);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_3.setBounds(144, 144, 193, 26);
		
		Button button = new Button(shell, SWT.NONE);
		button.setBounds(97, 204, 98, 30);
		button.setText("确定");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setBounds(239, 204, 98, 30);
		button_1.setText("取消");
		
		spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(144, 56, 98, 26);
		spinner.setMaximum(100000);
		
		spinner_1 = new Spinner(shell, SWT.BORDER);
		spinner_1.setBounds(144, 100, 98, 26);
		spinner_1.setMaximum(500);
		
		text.addListener(SWT.FocusOut, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				String startnum = text.getText().trim();
				int amountnum = Integer.parseInt(spinner.getText());
				if(amountnum > 0){
					text_3.setText((Long.parseLong(startnum)+amountnum-1)+"");
				}
			}
		});
		spinner.addListener(SWT.FocusOut, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				String startnum = text.getText().trim();
				int amountnum = Integer.parseInt(spinner.getText());
				if(!startnum.equals("")){
					text_3.setText((Long.parseLong(startnum)+amountnum-1)+"");
				}
			}
		});
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String logStr = "";
				Oddnumber oddnum = new Oddnumber();
				String startnum = text.getText().trim();
				String endnum = text_3.getText().trim();
				int amountnum = Integer.parseInt(spinner.getText());
				int warnnum = Integer.parseInt(spinner_1.getText());
				if(startnum.length()<5){
					logStr = "起始号码不能小于5位数！";
				}else if(endnum.length()<5){
					logStr = "结束号码不能小于5位数！";
				}else if(startnum.length()>12){
					logStr = "起始号码不能大于12位数！";
				}
				if(!logStr.equals("")){
					MessageDialog.openInformation(shell,"系统提示",logStr);
				}else{
					//如果单号已存在不允许再次添加
					List<Oddnumber> oddlist = oddnumCon.findAllOddnum(GlobalParam.SYSTEM_LOGINUSER);
					Long startnumf = Long.parseLong(startnum);
					Long endnumf = Long.parseLong(endnum);
					for(Oddnumber oddnum1 : oddlist){
						//新单号 1、开始号码<系统起始号码，结束号码>系统起始号码
						//		2、开始号码<系统结束号码，结束号码>系统结束号码
						//		3、开始号码>系统起始号码，结束号码<系统结束号码
						if(startnumf < Long.parseLong(oddnum1.getStartnum()) && endnumf > Long.parseLong(oddnum1.getStartnum())
								|| startnumf < Long.parseLong(oddnum1.getEndnum())&& endnumf > Long.parseLong(oddnum1.getEndnum())
								|| startnumf > Long.parseLong(oddnum1.getStartnum())&& endnumf < Long.parseLong(oddnum1.getEndnum())){
							MessageDialog.openInformation(shell,"系统提示","该单号系统中已存在！");
							return;
						}
					}
					oddnum.setStartnum(startnum);
					oddnum.setEndnum(endnum);
					oddnum.setUsenum("");
					oddnum.setWarnnum(warnnum);
					oddnum.setSuplusnum(amountnum);
					oddnum.setAmountnum(amountnum);
					oddnum.setState("0");
					oddnum.setUserid(GlobalParam.SYSTEM_LOGINUSER);
					oddnumCon.saveOddNum(oddnum);
					MessageDialog.openInformation(shell,"系统提示","单号添加成功！");
					shell.dispose();
				}
			}
		});
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
		});
	}
}
