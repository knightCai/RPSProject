package com.client.frame.main;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.client.common.FrameUtil;
import com.client.common.WordComposite;
import com.client.frame.data.DataComp;
import com.client.frame.print.printComp;
import com.client.frame.system.systemComp;
import org.eclipse.swt.layout.FillLayout;

public class mainFrame {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mainFrame window = new mainFrame();
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
		shell.setMaximized(true);//初始窗口默认为最大化
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
		shell.setMinimumSize(1080,768);
		shell.setText("报单打印系统");
		//InputStream in = this.getClass().getResourceAsStream("/images/system/printer-blue.ico");
		//shell.setImage(new  Image(shell.getDisplay(), in));
		FrameUtil.center(shell);
		showCTabFolder(shell);
	}

	public static void showCTabFolder(Shell shell)
	 {
       shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		 //定义tabFolder  
       final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
       //tabFolder.setBackgroundImage(new  Image(shell.getDisplay(), "images/system/bg-main.jpg"));
       //每一个tabItem内包含一个Composite的子类，因此我们可以将其分离出来，  
       //这里继承composite就好了。  
       final TabItem comaTabItem = new TabItem(tabFolder, SWT.NONE);  
       comaTabItem.setText("打印管理");  
       //实例化一个我们定制的composite  
       final Composite composite = new printComp(tabFolder, SWT.NONE);  
       //将我们定制的composite附加到tabItem上。  
       comaTabItem.setControl(composite);  
         
       
       final TabItem comcTabItem = new TabItem(tabFolder, SWT.NONE);  
       comcTabItem.setText("数据管理");  
       final Composite composite_2 = new DataComp(tabFolder, SWT.NONE);  
       comcTabItem.setControl(composite_2);  

       final TabItem combTabItem = new TabItem(tabFolder, SWT.NONE);  
       combTabItem.setText("系统管理");  
       final Composite composite_1 = new systemComp(tabFolder, SWT.NONE);  
       combTabItem.setControl(composite_1);  
	 }
}
