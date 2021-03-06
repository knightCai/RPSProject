package com.client.common;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.imageio.ImageIO;

/**
 * 打印面单类
 * @author knight
 * 面单规格10X18
 */
public class PrintSplane18   implements Printable{

	/**
	 * 最多5行
	 * @param g
	 * @param text
	 * @param lineWidth
	 * @param x
	 * @param y
	 */
	public static void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
		FontMetrics m = g.getFontMetrics();
		if(m.stringWidth(text) < lineWidth) {
			g.drawString(text, x, y);
		} else {
			String[] words = text.split("\r\n");
			String currentLine = words[0];
			for(int i = 1; i < words.length; i++) {
				if(m.stringWidth(currentLine+words[i]) < lineWidth) {
					currentLine += " "+words[i];
				} else {
					g.drawString(currentLine, x, y);
					y += m.getHeight();
					currentLine = words[i];
				}
				if(i>5)
					break;
			}
			if(currentLine.trim().length() > 0) {
				g.drawString(currentLine, x, y);
			}
		}
	}
	/**
	 * 无限制
	 * @param g
	 * @param text
	 * @param lineWidth
	 * @param x
	 * @param y
	 */
	public static void drawStringMultiLine2(Graphics2D g, String text, int lineWidth, int x, int y) {
		FontMetrics m = g.getFontMetrics();
		if(m.stringWidth(text) < lineWidth) {
			g.drawString(text, x, y);
		} else {
			String[] words = text.split("\r\n");
			String currentLine = words[0];
			for(int i = 1; i < words.length; i++) {
				if(m.stringWidth(currentLine+words[i]) < lineWidth) {
					currentLine += " "+words[i];
				} else {
					g.drawString(currentLine, x, y);
					y += m.getHeight()-2;
					currentLine = words[i];
				}
			}
			if(currentLine.trim().length() > 0) {
				g.drawString(currentLine, x, y);
			}
		}
	}
	/**
	 * 打印模式主入口，根据用户类型判断调用不同模板进行打印
	 */
	public int print(Graphics gra, PageFormat pf, int pageIndex){
		if(GlobalParam.SYSTEM_USER.getType() == 3){
			return print_TYPE3(gra, pf, pageIndex);
		}else if(GlobalParam.SYSTEM_USER.getType() == 2){
			return print_TYPE2(gra, pf, pageIndex);
		}else{
			return print_DEFAULT(gra, pf, pageIndex);
		}
	}

	/**
	 * @param Graphic指明打印的图形环境
	 * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
	 * @param pageIndex指明页号
	 **/
	public int print2(Graphics gra, PageFormat pf, int pageIndex){
		try {
			//转换成Graphics2D
			Graphics2D g2 = (Graphics2D) gra;
			//打印起点坐标
			/*double x = pf.getImageableX();
	      double y = pf.getImageableY();*/
			switch(pageIndex){
			case 0:
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
				//设置打印颜色为黑色
				g2.setColor(Color.black);
				//设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
				//Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
				Font font = new Font("Microsoft YaHei UI", Font.PLAIN,10);
				Font font1 = new Font("Microsoft YaHei UI", Font.BOLD,11);
				g2.setFont(font);//设置字体
				//BasicStroke   bs_3=new   BasicStroke(0.5f);   
				float[]   dash1   =   {2.0f}; 
				//设置打印线的属性。
				//1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
				g2.setStroke(new   BasicStroke(0.5f,   BasicStroke.CAP_BUTT,   BasicStroke.JOIN_MITER,   2.0f,   dash1,   0.0f));  
				//g2.setStroke(bs_3);//设置线宽
				//BufferedImage bufimag =  MatrixUtil.toBufferedImage(MatrixUtil.toBarCodeMatrix(GlobalParam.PRINT_DECLARENUM, 230, null));
				BufferedImage bufimag =  MatrixUtil.write2D(GlobalParam.PRINT_DECLARENUM, 100, 35);
				//面单头
				g2.drawLine(6, 10, 284, 10);// 上边框横线
				g2.drawLine(6, 10, 8,420);//左边框竖线
				g2.drawLine(284, 10, 284,420);//右边框竖线
				String sendName ="LEADER EXPRESS";
				g2.drawString(sendName,12,25);
				String sendTelephone = "立达国际快递有限公司";
				g2.drawString(sendTelephone,12,40);
				String sendCompory = "LEADER EXPRESS CO.ltd";
				g2.drawString(sendCompory, 12,55);
				g2.drawImage(bufimag, null,140,13);
				//g2.drawString(canvas_declarenum1, 150,20);
				String text_declarenum1 = GlobalParam.PRINT_DECLARENUM;
				g2.drawString(text_declarenum1, 150,60);
				//寄件人信息
				g2.drawLine(6, 65, 284, 65);// 第二条横线
				String senduser = "寄件人/Form:";
				g2.drawString(senduser,12,80);
				String text_consignername = GlobalParam.NERNAME_OF_COUNTRYS.get(GlobalParam.PRINT_CONSIGNERCOUNTRY);
				text_consignername = text_consignername==null?GlobalParam.PRINT_CONSIGNERNAME:text_consignername;
				g2.drawString(text_consignername,80,80);
				String sendAddress2 = "进口口岸:";
				g2.drawString(sendAddress2,180,80);
				String text_passport = GlobalParam.PRINT_PASSPORT;
				g2.drawString(text_passport,230,80);
				String sendAddress1 = "地址:";
				g2.drawString(sendAddress1,12,100);
				String sendphone = "电话:";
				g2.drawString(sendphone,180,100);
				String text_consignerphone = GlobalParam.PRINT_CONSIGNERPHONE;
				g2.drawString(text_consignerphone,210,100);
				String text_consigneraddr = GlobalParam.PRINT_CONSIGNERADDR;
				//drawStringMultiLine(g2, text_consigneraddr, 10, 15, 116);
				g2.drawString(text_consigneraddr,15,116);
				//收件人信息
				g2.drawLine(6, 140, 284, 140);// 第三条横线
				String acceptuser = "收件人/To:";
				g2.drawString(acceptuser,12,155);
				String text_consigneename = GlobalParam.PRINT_CONSIGNEENAME;
				g2.drawString(text_consigneename,80,155);
				String acceptphone = "电话:";
				g2.drawString(acceptphone,180,155);
				String text_consigneephone = GlobalParam.PRINT_CONSIGNEEPHONE;
				g2.drawString(text_consigneephone,210,155);
				String acceptAddress = "地址:";
				g2.drawString(acceptAddress,12,172);
				String[] splitAddr = GlobalParam.PRINT_CONSIGNEEADDR.split("\\|");
				//省市
				g2.setFont(font1);//设置字体
				g2.drawString(splitAddr[0]+" "+splitAddr[1],50,173);
				g2.setFont(font);//设置字体
				String text_consigneeaddr = splitAddr[2];
				if(text_consigneeaddr.length()>28){
					text_consigneeaddr = text_consigneeaddr.substring(0, 28)+"\r\n" +text_consigneeaddr.substring(28,text_consigneeaddr.length());
				}
				drawStringMultiLine(g2, text_consigneeaddr, 10, 15, 188);
				//g2.drawString(text_consigneeaddr,15,190);
				//货物内容描述
				g2.drawLine(6, 210, 284, 210);// 第四条横线
				String acceptA = "货物内容描述:";
				g2.drawString(acceptA,12,225);
				String text_cargoname_cargotype = GlobalParam.PRINT_CARGONAME_CARGOTYPE;
				drawStringMultiLine(g2, text_cargoname_cargotype, 10, 15, 242);
				//g2.drawString(text_cargoname_cargotype,12,245);
				g2.drawLine(180, 210, 180,300);//竖线
				String acceptAb = "实际重量:";
				g2.drawString(acceptAb,190,225);
				String text_weight = GlobalParam.PRINT_WEIGHT+"Kg";
				g2.drawString(text_weight,190,245);
				g2.drawLine(180, 255, 284, 255);// 货物内容横线
				String acceptAbC = "原产地:";
				g2.drawString(acceptAbC,190,270);
				String text_consignercountry = GlobalParam.COUNTRYIDS.get(GlobalParam.PRINT_CONSIGNERCOUNTRY);
				drawStringMultiLine(g2, text_consignercountry, 10, 190,286);
				//g2.drawString(text_consignercountry,190,286);
				//签名
				g2.drawLine(7, 300, 284, 300);// 第五条横线
				String acceptqm = "收件人签名:";
				g2.drawString(acceptqm,12,312);
				String qssj = "签收时间:         年    月   日";
				g2.drawString(qssj,140,312);
				//报关单号条码
				g2.drawLine(7, 320, 284, 320);// 第六条横线
				//String canvas_declarenum2 = "条码";
				//g2.drawRenderedImage(img, xform)dImage(img, at);
				g2.drawImage(bufimag, null,10,330);
				//g2.drawString(canvas_declarenum2,50,350);
				String text_1 = GlobalParam.PRINT_DECLARENUM;
				g2.drawString(text_1,20,380);
				g2.drawLine(7, 390, 284, 390);// 第七条横线
				String gldh = "关联单号箱号:";
				g2.drawString(gldh,12,405);
				String text_expressnum = GlobalParam.PRINT_EXPRESSNUM;
				g2.drawString(text_expressnum,80,405);
				g2.drawLine(7, 420, 284, 420);// 下边框横线
				return PAGE_EXISTS;
			default:
				return NO_SUCH_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NO_SUCH_PAGE;

	}

	/**
	 * @param Graphic指明打印的图形环境
	 * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
	 * @param pageIndex指明页号
	 **/
	public int print_DEFAULT(Graphics gra, PageFormat pf, int pageIndex){
		try {
			//转换成Graphics2D
			Graphics2D g2 = (Graphics2D) gra;
			//打印起点坐标
			/*double x = pf.getImageableX();
 	      double y = pf.getImageableY();*/
			switch(pageIndex){
			case 0:
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
				//设置打印颜色为黑色
				g2.setColor(Color.black);
				//设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
				//Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
				Font font = new Font("Microsoft YaHei UI", Font.PLAIN,7);
				Font font1 = new Font("Microsoft YaHei UI", Font.BOLD,20);
				Font font2 = new Font("Microsoft YaHei UI", Font.PLAIN,9);
				Font font3 = new Font("Microsoft YaHei UI", Font.BOLD,13);
				Font font4 = new Font("Microsoft YaHei UI", Font.PLAIN,8);
				g2.setFont(font2);//设置字体
				//BasicStroke   bs_3=new   BasicStroke(0.5f);   
				float[]   dash1   =   {2.0f}; 
				//设置打印线的属性。
				//1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
				g2.setStroke(new   BasicStroke(0.5f,   BasicStroke.CAP_BUTT,   BasicStroke.JOIN_MITER,   2.0f,   dash1,   0.0f));  
				//g2.setStroke(bs_3);//设置线宽
				//BufferedImage bufimag =  MatrixUtil.toBufferedImage(MatrixUtil.toBarCodeMatrix(GlobalParam.PRINT_DECLARENUM, 230, null));
				BufferedImage bufimag =  MatrixUtil.write2D(GlobalParam.PRINT_DECLARENUM, 150, 25);
				BufferedImage bufimgJGLogo = ImageIO.read(this.getClass().getResourceAsStream(GlobalParam.SOURCE_ISLOOKBWNAME));
				//BufferedImage bufimgJGLogo = ImageIO.read(this.getClass().getResourceAsStream(GlobalParam.SOURCE_HYTLOGONAME));
				//面单头
				//g2.drawLine(6, 10, 284, 10);// 上边框横线
				//g2.drawLine(6, 10, 8,420);//左边框竖线
				//g2.drawLine(284, 10, 284,420);//右边框竖线
				/*g2.setFont(font2);
				String sendName ="LEADER EXPRESS 立达国际快递有限公司";
				g2.drawString(sendName,2,20);
				String sendTelephone = "LEADER EXPRESS CO.ltd";
				g2.drawString(sendTelephone,30,35);
				g2.setFont(font);*/
				//g2.drawImage(bufimgJGLogo, null,40,8);
				String toptips = "First part YTO's copy\r\n" +
						"(上联：此联由圆通速递留存)";
				//drawStringMultiLine2(g2, toptips,20,172,8);
 	            g2.drawLine(4, 4, 294, 4);// 第一条横线
				g2.drawLine(2, 4, 292, 4);
				g2.setFont(font1);
				String text_bigpen = GlobalParam.PRINT_BIGPEN;
 	            g2.drawString(text_bigpen, 90,25);
 	            g2.setFont(font2);
				g2.drawLine(4, 30, 294, 30);// 第二条横线
				g2.drawLine(2, 30, 292, 30);
				g2.drawImage(bufimag, null,80,33);
				g2.drawLine(4, 60, 294, 60);// 第三条横线
				g2.drawLine(2, 60, 292, 60);
				String text_declarenum1 = GlobalParam.PRINT_DECLARENUM;
				g2.drawString(text_declarenum1, 120,71);
				g2.drawLine(4, 73, 294, 73);// 第四条横线
				g2.drawLine(2, 73, 292, 73);
				g2.drawLine(15, 73, 15, 241);// 第一条竖线
				g2.drawLine(15, 75, 15, 243);
				g2.drawLine(174, 73, 174, 241 );// 第二条竖线
				g2.drawLine(174, 75, 174, 243);
				String acceptuser = "收\r\n件\r\n人\r\nT\r\no";
				drawStringMultiLine2(g2, acceptuser,1,4,83);
				String text_consigneename = GlobalParam.PRINT_CONSIGNEENAME;
				g2.drawString(text_consigneename,110,125);
				
				String[] splitAddr = GlobalParam.PRINT_CONSIGNEEADDR.split("\\|");
				//省市
				g2.setFont(font3);//设置字体
				g2.drawString(splitAddr[0]+" "+splitAddr[1],20,87);
				g2.setFont(font2);//设置字体
				String text_consigneeaddr = splitAddr[2];
				if(text_consigneeaddr.length()>16){
					text_consigneeaddr = text_consigneeaddr.substring(0, 16)+"\r\n" +(
							text_consigneeaddr.length()>32?(text_consigneeaddr.substring(16, 32)+"\r\n"+text_consigneeaddr.substring(32, text_consigneeaddr.length())):
								text_consigneeaddr.substring(16,text_consigneeaddr.length()));
				}
				drawStringMultiLine2(g2, text_consigneeaddr, 10, 20, 101);
				String postcode = "邮编：\r\nPostcode";
				drawStringMultiLine2(g2, postcode,20,180,83);
				g2.drawLine(174, 110, 294, 110);// 内横线
				g2.drawLine(176, 110, 292, 110);
				String acceptphone = "电话:";
				g2.drawString(acceptphone,150,125);
				String text_consigneephone = GlobalParam.PRINT_CONSIGNEEPHONE;
				if(text_consigneephone.length()>11){
					text_consigneephone = text_consigneephone.substring(0, 11)+"\r\n" +text_consigneephone.substring(11,text_consigneephone.length());
				}
				drawStringMultiLine2(g2, text_consigneephone, 10, 180, 125);
				//货物内容描述
				g2.drawLine(4, 130, 294, 130);// 第五条横线
				g2.drawLine(2, 130, 292, 130);
				String acceptA = "详\r\n细\r\n内\r\n容\r\nC\r\no\r\nn\r\nt\r\ne\r\nn\r\nt";
				g2.setFont(font4);
				drawStringMultiLine2(g2, acceptA,1,4,140);
				g2.setFont(font2);
				String acceptB = "内容品名：\r\nName of contents";
				drawStringMultiLine2(g2, acceptB,10,20,140);
				String text_cargoname_cargotype = GlobalParam.PRINT_CARGONAME_CARGOTYPE;
				drawStringMultiLine(g2, text_cargoname_cargotype, 10, 20, 160);
				String receName = "收件人签名：\r\nSignature of Receiver";
				drawStringMultiLine2(g2, receName,20,180,140);
				String cardid = "证件号：\r\nID NO       年Y  月M  日D";
				drawStringMultiLine2(g2, cardid,20,180,163);
				g2.drawLine(15, 220, 174, 220);// 货物内容横线
				g2.drawLine(17, 220, 174, 220);
				String amount = "数量:\r\nAmount";
				drawStringMultiLine2(g2, amount,20,20,229);
				String weightG = GlobalParam.PRINT_WEIGHT;
				String weight = "重量:\r\nWeight  "+weightG+"kg";
				drawStringMultiLine2(g2, weight,20,100,229);
				g2.drawLine(175, 176, 292, 176);// 货物内容横线
				g2.drawLine(177, 176, 294, 176);
				String remark = "Once the recipient or the\r\n" +
						"authorized recipient signed\r\n" +
						"for the parcel.the delivery\r\n" +
						"service shall be deemed\r\n" +
						"completed.\r\n" +
						"圆通快递将快件送达收件人地\r\n" +
						"址,经收件人或收件人(寄件人)\r\n" +
						"允许的代收人签字,视为送达.)";
				g2.setFont(font);
				drawStringMultiLine2(g2, remark,20,180,185);
				g2.setFont(font2);
				g2.drawLine(4, 243, 294, 243);// 第五条横线
				g2.drawLine(2, 243, 292, 243);
				String text_track = "运单号：\r\nTracking";
				drawStringMultiLine2(g2, text_track,20,4,253);
				String track = GlobalParam.PRINT_EXPRESSNUM;
				g2.drawString(track,40,253);
				String secodepart = "Second part Recipient copy\r\n" +
						"（下联：此联由收件人留存）";
				drawStringMultiLine2(g2, secodepart,20,172,253);
				g2.drawLine(4, 268, 294, 268);// 第六条横线
				g2.drawLine(2, 268, 292, 268);
				g2.drawLine(4, 280, 294, 280);// 第七条横线
				g2.drawLine(2, 280, 292, 280);
				g2.drawLine(15, 280, 15, 433);// 第一条竖线
				g2.drawLine(15, 282, 15, 435);
				g2.drawLine(174, 340, 174, 433);// 第二条竖线
				g2.drawLine(174, 342, 174, 435);
				drawStringMultiLine2(g2,acceptuser,1,4,293);
				g2.drawString(text_consigneename,110,335);
				//省市
				g2.setFont(font3);//设置字体
				g2.drawString(splitAddr[0]+" "+splitAddr[1],20,295);
				g2.setFont(font2);//设置字体
				drawStringMultiLine2(g2, text_consigneeaddr, 10, 20, 310);
				g2.drawImage(bufimgJGLogo, null,205,280);
				drawStringMultiLine2(g2, postcode,10,180,295);
				g2.drawString(acceptphone,150,335);
				drawStringMultiLine2(g2, text_consigneephone, 10, 180, 335);
				g2.drawLine(4, 340, 294, 340);// 第五条横线
				g2.drawLine(2, 340, 292, 340);
				g2.setFont(font);
				drawStringMultiLine2(g2, acceptA,1,4,350);
				g2.setFont(font2);
				String orderid = "订单号：\r\nOrder ID";
				drawStringMultiLine2(g2, orderid,20,20,350);
				g2.drawLine(17, 370, 174, 370);// 第七条横线
				g2.drawLine(15, 370, 172, 370);
				String from = "发件人：" + GlobalParam.PRINT_CONSIGNERNAME + "\r\nFrom";
				drawStringMultiLine2(g2, from,20,20,380);
				g2.drawLine(17, 400, 172, 400);// 第七条横线
				g2.drawLine(15, 400, 174, 400);
				String address = "发件地址：\r\nAddress";
				drawStringMultiLine2(g2, address,20,20,410);
				String endEG = "The waybill shall only be\r\n" +
						"used for YTO Express's \r\n" +
						"contract customer.respo\r\n" +
						"nsibilities and obligations\r\n" +
						"are subject to the contract.";
				g2.setFont(font);
				drawStringMultiLine2(g2, endEG,20,180, 352);
				g2.setFont(font2);
				g2.drawLine(174, 400, 292, 400);// 第七条横线
				g2.drawLine(176, 400, 294, 400);
				g2.setFont(font1);
				String logoName = "YTO馨翔";
				g2.drawString(logoName,185,425);
				g2.setFont(font2);
				g2.drawLine(4, 435, 294, 435);// 第七条横线
				g2.drawLine(2, 435, 292, 435);
				return PAGE_EXISTS;
			default:
				return NO_SUCH_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NO_SUCH_PAGE;

	}

	/**
	 * 圆通打印面单模板
	 * @param gra
	 * @param pf
	 * @param pageIndex
	 * @return
	 */
	public int print_TYPE3(Graphics gra, PageFormat pf, int pageIndex){
		try {
			//转换成Graphics2D
			Graphics2D g2 = (Graphics2D) gra;
			//打印起点坐标
			/*double x = pf.getImageableX();
 	      double y = pf.getImageableY();*/
			switch(pageIndex){
			case 0:
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
				//设置打印颜色为黑色
				g2.setColor(Color.black);
				//设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
				//Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
				Font font = new Font("Microsoft YaHei UI", Font.PLAIN,10);
				Font font1 = new Font("Microsoft YaHei UI", Font.BOLD,13);
				Font font2 = new Font("Microsoft YaHei UI", Font.BOLD,25);
				Font font3 = new Font("Microsoft YaHei UI", Font.PLAIN,8);
				Font font4 = new Font("Microsoft YaHei UI", Font.PLAIN,9);
				Font font5 = new Font("Microsoft YaHei UI", Font.PLAIN,30);
				g2.setFont(font);//设置字体
				//BasicStroke   bs_3=new   BasicStroke(0.5f);   
				float[]   dash1   =   {2.0f}; 
				//设置打印线的属性。
				//1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
				g2.setStroke(new   BasicStroke(0.5f,   BasicStroke.CAP_BUTT,   BasicStroke.JOIN_MITER,   2.0f,   dash1,   0.0f));  
				//g2.setStroke(bs_3);//设置线宽
				//BufferedImage bufimag =  MatrixUtil.toBufferedImage(MatrixUtil.toBarCodeMatrix(GlobalParam.PRINT_DECLARENUM, 230, null));
				BufferedImage bufimag =  MatrixUtil.write2D(GlobalParam.PRINT_DECLARENUM, 100, 26);
				BufferedImage bufimag1 =  MatrixUtil.write2D(GlobalParam.PRINT_DECLARENUM, 110, 28);
				BufferedImage bufimgJGLogo = ImageIO.read(this.getClass().getResourceAsStream(GlobalParam.SOURCE_YTOLOGONAME));
				//面单头
				g2.drawLine(6, 7, 284, 7);// 上边框横线
				g2.drawLine(8, 7, 286, 7);
				g2.drawLine(6, 7, 6,410);//左边框竖线
				g2.drawLine(6, 9, 6,412);
				g2.drawLine(286, 7, 286,410);//右边框竖线
				g2.drawLine(286, 9, 286,412);
				g2.setFont(font2);
				String sendName ="YTO";
				g2.drawString(sendName,12,37);
				g2.setFont(font1);
				String sendTelephone = "圆通速递";
				g2.drawString(sendTelephone,110,37);
				g2.setFont(font);
				g2.drawImage(bufimgJGLogo, null,165,10);
				//寄件人信息
				g2.drawLine(6, 45, 284, 45);// 第二条横线
				g2.drawLine(8, 45, 286, 45);
				g2.drawImage(bufimag, null,140,73);
				String text_declarenum1 = GlobalParam.PRINT_DECLARENUM;
				g2.setFont(font3);
				g2.drawString(text_declarenum1, 170,108);
				g2.setFont(font2);
				g2.drawString(GlobalParam.PRINT_BIGPEN,60,68);
				g2.setFont(font);
				g2.drawLine(6, 70, 284, 70);// 横线
				g2.drawLine(8, 70, 286, 70);
				g2.drawString("订单号: "+GlobalParam.PRINT_EXPRESSNUM, 15,105);
				g2.drawLine(6, 110, 284,110);// 第三条横线
				g2.drawLine(8, 110, 286,110);
				g2.setFont(font4);
				String sendAddress1 = "寄件人:";
				g2.drawString(sendAddress1,15,123);
				String text_consignername = GlobalParam.NERNAME_OF_COUNTRYS.get(GlobalParam.PRINT_CONSIGNERCOUNTRY);
				text_consignername =  text_consignername==null?GlobalParam.PRINT_CONSIGNERNAME:text_consignername;
				String text_consigneraddr = GlobalParam.PRINT_CONSIGNERADDR;
				//String text_consignerphone = GlobalParam.PRINT_CONSIGNERPHONE;
				String sendstr = text_consignername + " " + text_consigneraddr;
				g2.drawString(sendstr.substring(0, sendstr.length()>56?56:sendstr.length()),15,135);
				g2.setFont(font);
				//收件人信息
				g2.drawLine(6, 142, 284, 142);// 第四条横线
				g2.drawLine(8, 142, 286, 142);
				String acceptuser = "收件人:";
				g2.drawString(acceptuser,15,157);
				String text_consigneename = GlobalParam.PRINT_CONSIGNEENAME;
				String text_consigneephone = GlobalParam.PRINT_CONSIGNEEPHONE;
				g2.drawString(text_consigneename + "   " +text_consigneephone,50,157);
				String[] splitAddr = GlobalParam.PRINT_CONSIGNEEADDR.split("\\|");
				/*
				//省市
				g2.setFont(font1);//设置字体
				g2.drawString(splitAddr[0]+" "+splitAddr[1],50,173);
				g2.setFont(font);//设置字体*/
				String text_consigneeaddr = splitAddr.length>2?splitAddr[2]:GlobalParam.PRINT_CONSIGNEEADDR;
				if(text_consigneeaddr.length()>23){
					text_consigneeaddr = text_consigneeaddr.substring(0, 23)+"\r\n" +text_consigneeaddr.substring(23,text_consigneeaddr.length());
				}
				drawStringMultiLine(g2, text_consigneeaddr, 10, 50, 172);
				g2.drawLine(6, 192, 284, 192);// 第五条横线
				g2.drawLine(8, 192, 286, 192);
				//g2.drawString(text_cargoname_cargotype,12,245);
				g2.drawString("收件人/寄件人:",15,212);
				g2.drawLine(100, 192, 100,228);//竖线
				g2.drawLine(100, 194, 100,230);
				String acceptAb = "签收时间:";
				g2.drawString(acceptAb,110,212);
				g2.drawString("年    月   日",160,227);
				//签名
				g2.drawLine(6, 230, 284, 230);// 第六条横线
				g2.drawLine(8, 230, 286, 230);
				g2.drawImage(bufimag1, null,130,232);
				g2.setFont(font3);
				g2.drawString(text_declarenum1,160,267);
				g2.drawLine(6, 272, 274, 272);// 第七条横线
				g2.drawLine(8, 272, 276, 272);
				g2.setFont(font4);
				String sendsUser = "寄件人：";
				String sendstr1 = sendsUser + text_consignername + " " +text_consigneraddr;
				g2.drawString(sendstr1.substring(0,sendstr1.length()>49?49:sendstr1.length()),15,287);
				g2.setFont(font);
				//报关单号条码
				g2.drawLine(6, 292, 284, 292);// 第八条横线
				g2.drawLine(8, 292, 286, 292);
				g2.drawString(acceptuser,15,305);
				g2.drawString(text_consigneename + "    " +text_consigneephone,50,305);
				drawStringMultiLine(g2, text_consigneeaddr, 10, 50, 322);
				g2.drawLine(6, 340, 284, 340);// 第九条横线
				g2.drawLine(8, 340, 286, 340);
				g2.setFont(font3);
				String acceptA = "内件描述/Name & Description of Contents:";
				g2.drawString(acceptA,15,350);
				String text_cargoname_cargotype = GlobalParam.PRINT_CARGONAME_CARGOTYPE;
				drawStringMultiLine(g2, text_cargoname_cargotype, 5, 15, 360);
				g2.drawLine(6, 410, 284, 410);// 下边框横线
				g2.drawLine(8, 410, 286, 410);
				return PAGE_EXISTS;
			default:
				return NO_SUCH_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NO_SUCH_PAGE;

	}
	
	/**
	 * 青岛打印面单模板
	 * @param Graphic指明打印的图形环境
	 * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
	 * @param pageIndex指明页号
	 **/
	public int print_TYPE2(Graphics gra, PageFormat pf, int pageIndex){
		try {
			//转换成Graphics2D
			Graphics2D g2 = (Graphics2D) gra;
			//打印起点坐标
			/*double x = pf.getImageableX();
 	      double y = pf.getImageableY();*/
			switch(pageIndex){
			case 0:
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
				//设置打印颜色为黑色
				g2.setColor(Color.black);
				//设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
				//Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
				Font font = new Font("Microsoft YaHei UI", Font.PLAIN,11);
				Font font1 = new Font("Microsoft YaHei UI", Font.BOLD,12);
				Font font2 = new Font("Microsoft YaHei UI", Font.BOLD,9);
				g2.setFont(font);//设置字体
				//BasicStroke   bs_3=new   BasicStroke(0.5f);   
				float[]   dash1   =   {2.0f}; 
				//设置打印线的属性。
				//1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
				g2.setStroke(new   BasicStroke(0.5f,   BasicStroke.CAP_BUTT,   BasicStroke.JOIN_MITER,   2.0f,   dash1,   0.0f));  
				//g2.setStroke(bs_3);//设置线宽
				//BufferedImage bufimag =  MatrixUtil.toBufferedImage(MatrixUtil.toBarCodeMatrix(GlobalParam.PRINT_DECLARENUM, 230, null));
				BufferedImage bufimag =  MatrixUtil.write2D(GlobalParam.PRINT_DECLARENUM, 120, 30);
				BufferedImage bufimagEXP =  MatrixUtil.write2D(GlobalParam.PRINT_EXPRESSNUM, 80, 25);
				BufferedImage bufimgJGLogo = ImageIO.read(this.getClass().getResourceAsStream(GlobalParam.SOURCE_QDLOGONAME));
				BufferedImage bufimgISLLogo = ImageIO.read(this.getClass().getResourceAsStream(GlobalParam.SOURCE_ISLOOKNAME));
				BufferedImage bufimgISSLogo = ImageIO.read(this.getClass().getResourceAsStream(GlobalParam.SOURCE_ISSECURITYNAME));
				//面单头
				//g2.drawLine(6, 10, 284, 10);// 上边框横线
				//g2.drawLine(6, 10, 8,420);//左边框竖线
				//g2.drawLine(284, 10, 284,420);//右边框竖线
				g2.drawImage(bufimgJGLogo, null,4,8);
				g2.drawImage(bufimag, null,120,11);
				g2.drawImage(bufimgISLLogo, null,120,80);
				g2.drawImage(bufimgISSLogo, null,120,250);
				String text_declarenum1 = GlobalParam.PRINT_DECLARENUM;
				wirteTextPU(g2, bufimag.getWidth(),120,36, text_declarenum1);
				//g2.drawString(text_declarenum1, 150,45);
				//寄件人信息
				g2.drawLine(4, 53, 294, 53);// 第二条横线
				g2.drawLine(2, 53, 292, 53);
				g2.drawLine(4, 54, 294, 54);// 第三条横线
				g2.drawLine(2, 54, 292, 54);
				String senduser = "寄件人:";
				g2.drawString(senduser,10,66);
				String text_consignername = GlobalParam.NERNAME_OF_COUNTRYS.get(GlobalParam.PRINT_CONSIGNERCOUNTRY);
				text_consignername = text_consignername==null?GlobalParam.PRINT_CONSIGNERNAME:text_consignername;
				g2.drawString(text_consignername,45,66);
				String sendAddress2 = "邮编:";
				g2.drawString(sendAddress2,180,66);
				String text_ub = "";
				g2.drawString(text_ub,210,66);
				String sendAddress1 = "地址:";
				g2.drawString(sendAddress1,10,82);
				String text_consigneraddr = GlobalParam.PRINT_CONSIGNERADDR;
				g2.drawString(text_consigneraddr,40,82);
				String weight = "重量:（KG）:";
				g2.drawString(weight,10,100);
				String text_weight = GlobalParam.PRINT_WEIGHT+"Kg";
				g2.drawString(text_weight,150,100);
				//收件人信息
				g2.drawLine(4, 108, 294, 108);// 第四条横线
				g2.drawLine(2, 108, 292, 108);
				g2.drawLine(4, 107, 294, 107);// 第四条横线
				g2.drawLine(2, 107, 292, 107);
				String acceptuser = "收件人:";
				g2.drawString(acceptuser,10,125);
				String text_consigneename = GlobalParam.PRINT_CONSIGNEENAME;
				g2.drawString(text_consigneename,50,125);
				String reciverCS = "寄达市:";
				g2.drawString(reciverCS,180,125);
				String acceptphone = "电话:";
				g2.drawString(acceptphone,10,140);
				String text_consigneephone = GlobalParam.PRINT_CONSIGNEEPHONE;
				/*if(text_consigneephone.length()>11){
					text_consigneephone = text_consigneephone.substring(0, 11)+"\r\n" +text_consigneephone.substring(11,text_consigneephone.length());
				}*/
				drawStringMultiLine(g2, text_consigneephone, 10, 50,140);
				g2.drawString(sendAddress2,180,140);
				g2.drawString(text_ub,210,140);
				String acceptAddress = "地址:";
				g2.drawString(acceptAddress,10,155);
				String[] splitAddr = GlobalParam.PRINT_CONSIGNEEADDR.split("\\|");
				//省市
				g2.setFont(font1);//设置字体
				g2.drawString(splitAddr[0]+" "+splitAddr[1],50,155);
				g2.setFont(font);//设置字体
				String text_consigneeaddr = splitAddr[2];
				if(text_consigneeaddr.length()>22){
					text_consigneeaddr = text_consigneeaddr.substring(0, 22)+"\r\n" +text_consigneeaddr.substring(22,text_consigneeaddr.length());
				}
				drawStringMultiLine(g2, text_consigneeaddr, 10, 50,170);
				//g2.drawString(text_consigneeaddr,15,190);
				//货物内容描述
				String acceptqm = "收件人签名:";
				g2.drawString(acceptqm,10,200);
				String qssj = "签收时间:        	  年     月     日";
				g2.drawString(qssj,140,200);
				g2.drawLine(4, 203, 294, 203);// 第五条横线
				g2.drawLine(2, 203, 292, 203);
				g2.drawLine(4, 204, 294, 204);
				g2.drawLine(2, 204, 292, 204);
				String expNum = "订单ID:";
				g2.drawString(expNum, 10, 230);
				g2.drawImage(bufimagEXP, null,80,210);
				wirteTextPU(g2, bufimagEXP.getWidth(),80,230, GlobalParam.PRINT_EXPRESSNUM);
				String acceptA = "内件:";
				g2.drawString(acceptA,10,260);
				String text_cargoname_cargotype = GlobalParam.PRINT_CARGONAME_CARGOTYPE;
				drawStringMultiLine(g2, text_cargoname_cargotype, 20, 50, 260);
				g2.drawString(senduser,10,275);
				g2.drawString(text_consignername,50,275);
				g2.drawString(weight,160,275);
				g2.drawString(text_weight,225,275);
				g2.drawString(acceptAddress,10,290);
				drawStringMultiLine(g2, text_consigneraddr, 10,50, 290);
				g2.drawLine(4, 294, 294, 294);// 第六条横线
				g2.drawLine(2, 294, 292, 294);
				g2.drawLine(4, 295, 294, 295);
				g2.drawLine(2, 295, 292, 295);
				g2.drawString(acceptuser,10,310);
				g2.drawString(text_consigneename,50,310);
				g2.drawString(acceptphone,160,310);
				drawStringMultiLine(g2, text_consigneephone, 10,190,310);
				g2.drawString(acceptAddress,10,325);
				g2.setFont(font1);//设置字体
				g2.drawString(splitAddr[0]+" "+splitAddr[1],50,325);
				g2.setFont(font);//设置字体
				drawStringMultiLine(g2, text_consigneeaddr, 10, 30, 340);
				g2.drawLine(4, 362, 294, 362);// 第七条横线
				g2.drawLine(2, 362, 292, 362);
				g2.drawLine(4, 361, 294, 361);// 第七条横线
				g2.drawLine(2, 361, 292, 361);
				//String canvas_declarenum2 = "条码";
				//g2.drawRenderedImage(img, xform)dImage(img, at);
				//报关单号条码
				g2.drawImage(bufimag, null,5,365);
				//g2.drawString(canvas_declarenum2,50,350);
				wirteTextPU(g2, bufimag.getWidth(),5,390, text_declarenum1);
				g2.drawImage(bufimgJGLogo, null,180,365);
				return PAGE_EXISTS;
			default:
				return NO_SUCH_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NO_SUCH_PAGE;

	}
	
	public static void wirteTextPU(Graphics2D gr,int withd,int x,int y,String text) throws Exception{
		String[] str = text.split("");
		//计算平均宽度
		int avgw = withd/str.length+1;
		for(int i=0;i<str.length;i++){
			gr.drawString(str[i],x-5+i*avgw,y+15);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(GlobalParam.NERNAME_OF_COUNTRYS.get(""));
		//doPrint();
		//System.out.println("辽宁锦州市太和区天桥街道经济技术开发区渤海大道2段金".length());
	}
	public static void doPrint() {

		//    通俗理解就是书、文档
		Book book = new Book();
		//    设置成竖打
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		//    通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper p = new Paper();
		p.setSize(300,480);//纸张大小 
		p.setImageableArea(0,0, 300,480);//A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
		pf.setPaper(p);
		//    把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new PrintSplane18(), pf);

		//获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();      
		// 设置打印类
		job.setPageable(book);

		try {
			//可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
			//boolean a=job.printDialog();
			//if(a)
			//{        
			job.print();
			//}
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
}