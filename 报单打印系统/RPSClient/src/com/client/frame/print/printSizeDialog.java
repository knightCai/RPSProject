package com.client.frame.print;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import com.client.common.FrameUtil;
import com.client.common.GlobalParam;
import com.client.frame.main.mainFrame;
import org.eclipse.wb.swt.SWTResourceManager;

public class printSizeDialog extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public printSizeDialog(Shell parent, int style) {
		super(parent, style);
		setText("报关打印系统");
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
		shell.setSize(453, 287);
		shell.setText(getText());
		shell.setBackgroundImage(new  Image(shell.getDisplay(), this.getClass().getResourceAsStream(GlobalParam.SOURCE_CS1)));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		FrameUtil.center(shell);
		Label label_tips = new Label(shell, SWT.NONE);
		label_tips.setBounds(105, 25, 191, 20);
		label_tips.setText("请选择打印面单的尺寸：");
		
		Label lblx = new Label(shell, SWT.NONE);
		lblx.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblx.setBounds(105, 48, 191, 20);
		lblx.setText("(*不选择默认尺寸为10X15)");
		
		final Button radio1 = new Button(shell, SWT.RADIO);
		radio1.setBounds(105, 89, 119, 20);
		radio1.setSelection(true);
		radio1.setText("10 X 15");
		
		final Button radio2 = new Button(shell, SWT.RADIO);
		radio2.setText("10 X 18");
		radio2.setBounds(105, 130, 119, 20);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//判断是否选择
				if(radio2.getSelection()){
					GlobalParam.PRINT_PLANESIZE = "1";
				}
				shell.dispose();
			}
		});
		button_2.setBounds(198, 176, 98, 30);
		button_2.setText("确定");
		

	}
}
