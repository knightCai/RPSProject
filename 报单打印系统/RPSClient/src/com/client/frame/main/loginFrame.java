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

import com.client.common.FileUtil;
import com.client.common.FrameUtil;
import com.client.common.GlobalParam;
import com.client.frame.print.printSizeDialog;
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
	private Button btnCheckSaveLogin;
	
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
			in = this.getClass().getResourceAsStream(GlobalParam.SOURCE_BGLOGIN);
			shell.setBackgroundImage(new  Image(shell.getDisplay(), in));
			shell.setBackgroundMode(SWT.INHERIT_DEFAULT); 
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
		shell = new Shell(SWT.CLOSE | SWT.MIN);
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
		buttonlogin.setBounds(154, 137, 98, 30);
		buttonlogin.setText("登录");
		
		btnCheckSaveLogin = new Button(shell, SWT.CHECK);
		btnCheckSaveLogin.setBounds(154, 186, 121, 20);
		btnCheckSaveLogin.setText("记住登录密码");

		buttonlogin.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					String username = textusername.getText();
					String pwd = textpasswrod.getText();
					if(!username.equals("") && !pwd.equals("")){
						Userinfo user = new Userinfo();
						user.setUserid(username);
						user.setPassword(pwd);
						user = usercon.userLogin(user);
						if(user!=null){
							//如果选择了记住密码，保存密码信息
								if(btnCheckSaveLogin.getSelection()){
									user.setState("1");
									user.setPassword(pwd);
								}else{
									user.setUserid("");
									user.setPassword("");
									user.setState("0");
								}
								FileUtil.saveLoginInfo(user);
								//记住当前登录用户账号
								GlobalParam.SYSTEM_LOGINUSER = username;
								GlobalParam.SYSTEM_USER = user;
								if(user.getType() == 1){
									printSizeDialog printType = new printSizeDialog(shell,  SWT.APPLICATION_MODAL | SWT.CLOSE);
									Object obj = printType.open();
								}
								shell.dispose();
								mainFrame mainwoindow = new mainFrame();
								mainwoindow.open();
						}else{
							MessageDialog.openQuestion(shell,"系统提示","用户名或密码错误！");
						}
					}else{
						MessageDialog.openQuestion(shell, "系统提示", "请输入您的用户名及密码！");
					}
				} catch (Exception e) {
					MessageDialog.openQuestion(shell, "系统提示", "登录异常，请联系管理员！"+e.getMessage());
				}
			}
		});
		
		//获取用户本地保存的账户信息
		try {
			Userinfo user = FileUtil.getLoginInfo();
			boolean isSave = "1".equals(user.getState())?true:false;
			if(isSave){
				btnCheckSaveLogin.setSelection(true);
				textusername.setText(user.getUserid());
				textpasswrod.setText(user.getPassword());
			}
		} catch (Exception e) {
			MessageDialog.openQuestion(shell, "系统提示", "获取本地账户信息异常，请联系管理员！");
			e.printStackTrace();
		}
		
	}

}
