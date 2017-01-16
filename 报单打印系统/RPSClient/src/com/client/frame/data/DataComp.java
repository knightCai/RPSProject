package com.client.frame.data;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.client.frame.data.declare.DeclareMgrFrame;
import com.client.frame.print.printComp;
import com.client.frame.print.oddnumer.OddnumMgrFrame;
import com.client.frame.system.systemComp;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class DataComp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DataComp(Composite parent, int style) {
		super(parent, style);
		Composite c = this;
        /*InputStream in = this.getClass().getResourceAsStream("/images/system/bg-main.jpg");
        this.setBackgroundImage(new  Image(this.getShell().getDisplay(), in));*/
        //setLayout(new RowLayout(SWT.HORIZONTAL));
		//定义tabFolder  
        final TabFolder tabFolder = new TabFolder(c, SWT.NONE);
        //每一个tabItem内包含一个Composite的子类，因此我们可以将其分离出来，  
        //这里继承composite就好了。  
        final TabItem comdeclareTabItem = new TabItem(tabFolder, SWT.CLOSE);  
        comdeclareTabItem.setText("报关单号设置");  
        //实例化一个我们定制的composite  
        final Composite composite = new DeclareMgrFrame(tabFolder, SWT.BORDER);  
        //将我们定制的composite附加到tabItem上。  
        comdeclareTabItem.setControl(composite);  
		setLayout(new FillLayout(SWT.HORIZONTAL));
	}
	@Override
	protected void checkSubclass() {
	}
}
