package com.client.common;

import java.awt.Graphics;
import java.awt.print.PageFormat;

/**
 * 打印类主入口
 * @author knight
 *
 */
public class printSplaneMain {
PrintSplane15 plane15;
PrintSplane18 plane18;
	public printSplaneMain() {
		plane15 = new PrintSplane15();
		plane18 = new PrintSplane18();
	}
	public void doPrint() {
		if(GlobalParam.PRINT_PLANESIZE.equals("0")){
			plane15.doPrint();
		}else{
			plane18.doPrint();
		}
	}
	/**
	 * 打印模式主入口，根据用户类型判断调用不同模板进行打印
	 *//*
	public int print(Graphics gra, PageFormat pf, int pageIndex){
		if(GlobalParam.SYSTEM_USER.getType() == 3){
			return print_TYPE3(gra, pf, pageIndex);
		}else if(GlobalParam.SYSTEM_USER.getType() == 2){
			return print_TYPE2(gra, pf, pageIndex);
		}else{
			return print_DEFAULT(gra, pf, pageIndex);
		}
	}*/
}
