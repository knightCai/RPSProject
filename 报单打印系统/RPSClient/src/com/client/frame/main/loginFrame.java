package com.client.frame.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class loginFrame {

	protected Shell shell;
	private Text textusername;
	private Text textpasswrod;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			loginFrame window = new loginFrame();
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
		shell.setSize(450, 300);
		shell.setText("报单打印系统");
		
		textusername = new Text(shell, SWT.BORDER);
		textusername.setBounds(180, 32, 173, 26);
		
		textpasswrod = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textpasswrod.setBounds(180, 75, 173, 26);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(83, 35, 76, 20);
		label.setText("用户名：");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("密    码：");
		label_1.setBounds(83, 78, 76, 20);
		
		Button buttonlogin = new Button(shell, SWT.NONE);
		buttonlogin.setBounds(159, 129, 98, 30);
		buttonlogin.setText("登录");
		
		buttonlogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				mainFrame mainwoindow = new mainFrame();
				mainwoindow.open();
				shell.dispose();
			}
		});
		
	}

}
