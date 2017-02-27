package com.client.frame.main;

import java.io.InputStream;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.client.common.FrameUtil;
import com.client.common.GlobalParam;
import com.client.frame.system.systemUp;
import com.client.model.contrllo.SysparamContrllo;
import com.client.model.contrllo.UserinfoContrllo;
import com.service.service.Sysparam;
import com.service.service.Userinfo;

public class loginFrame {

	protected Shell shell;
	private Text textusername;
	private Text textpasswrod;
	private UserinfoContrllo usercon;
	private SysparamContrllo syscon;
	
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
		usercon = new UserinfoContrllo();
		syscon = new SysparamContrllo();
		Display display = Display.getDefault();
		Sysparam sys = syscon.findSysByParamname("SYS_VERSION");
		if(!sys.getValue().equals(GlobalParam.SYSTEM_VERRSION)){
			if(MessageDialog.openConfirm(shell, "系统提示", "系统已有更新版本，请先完成系统升级后使用！")){
				//在线升级
				systemUp window = new systemUp();
				window.open();
			}else{
				System.exit(0);
			}
		}else{
			createContents();
			FrameUtil.center(shell);
			InputStream in = this.getClass().getResourceAsStream(GlobalParam.SOURCE_LOGONAME);
			shell.setImage(new  Image(shell.getDisplay(), in));
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 280);
		shell.setText(GlobalParam.SYSTEM_SYSNAME);
		
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
			@SuppressWarnings("unused")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String username = textusername.getText();
				String pwd = textpasswrod.getText();
				if(!username.equals("") && !pwd.equals("")){
					Userinfo user = new Userinfo();
					user.setUserid(username);
					user.setPassword(pwd);
					if(usercon.userLogin(user)){
							//保存当前登录用户账号
							shell.dispose();
							GlobalParam.SYSTEM_LOGINUSER = username;
							mainFrame mainwoindow = new mainFrame();
							mainwoindow.open();
					}else{
						MessageDialog.openQuestion(shell,"系统提示","用户名或密码错误！");
					}
				}else{
					MessageDialog.openQuestion(shell, "系统提示", "请输入您的用户名及密码！");
				}
			}
		});
		
	}

}
