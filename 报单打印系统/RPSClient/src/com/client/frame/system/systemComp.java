package com.client.frame.system;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.service.service.Userinfo;
import com.service.service.UserinfoServiceService;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class systemComp extends Composite {
	
	public systemComp(Composite parent, int style) {  
        super(parent, style);
        /*InputStream in = this.getClass().getResourceAsStream("/images/system/bg-main.jpg");
        this.setBackgroundImage(new  Image(this.getShell().getDisplay(), in));*/
        //this.setBackgroundImage(new  Image(this.getDisplay(), "/images/system/bg-main.jpg"));
        Button button = new Button(this, SWT.NONE);
        button.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		Userinfo user = new UserinfoServiceService().getUserinfoServicePort().findUserByUserId("knight");
        	}
        });
        button.setBounds(10, 10, 98, 30);
        button.setText("修改密码");
        
        Button button_1 = new Button(this, SWT.NONE);
        button_1.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		System.exit(0);
        	}
        });
        button_1.setBounds(166, 10, 98, 30);
        button_1.setText("退出系统");
    }  

}
