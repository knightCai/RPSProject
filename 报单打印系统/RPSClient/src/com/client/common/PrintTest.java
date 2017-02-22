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

/**
 * 打印面单类
 * @author knight
 *
 */
public class PrintTest   implements Printable{
	
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
   * @param Graphic指明打印的图形环境
   * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
   * @param pageIndex指明页号
   **/
   public int print(Graphics gra, PageFormat pf, int pageIndex){
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
	           g2.drawLine(6, 10, 290, 10);// 上边框横线
	           g2.drawLine(6, 10, 8,420);//左边框竖线
	           g2.drawLine(290, 10, 290,420);//右边框竖线
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
	           g2.drawLine(6, 65, 290, 65);// 第二条横线
	           String senduser = "寄件人/Form:";
	           g2.drawString(senduser,12,80);
	           String text_consignername = GlobalParam.PRINT_CONSIGNERNAME;
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
	           g2.drawLine(6, 140, 290, 140);// 第三条横线
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
	           g2.drawLine(6, 210, 290, 210);// 第四条横线
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
	           g2.drawLine(180, 255, 290, 255);// 货物内容横线
	           String acceptAbC = "原产地:";
	           g2.drawString(acceptAbC,190,270);
	           String text_consignercountry = GlobalParam.COUNTRYIDS.get(GlobalParam.PRINT_CONSIGNERCOUNTRY);
	           drawStringMultiLine(g2, text_consignercountry, 10, 190,286);
	           //g2.drawString(text_consignercountry,190,286);
	           //签名
	           g2.drawLine(7, 300, 290, 300);// 第五条横线
	           String acceptqm = "收件人签名:";
	           g2.drawString(acceptqm,12,312);
	           String qssj = "签收时间:         年    月   日";
	           g2.drawString(qssj,140,312);
	           //报关单号条码
	           g2.drawLine(7, 320, 290, 320);// 第六条横线
	           //String canvas_declarenum2 = "条码";
	           //g2.drawRenderedImage(img, xform)dImage(img, at);
	           g2.drawImage(bufimag, null,10,330);
	           //g2.drawString(canvas_declarenum2,50,350);
	           String text_1 = GlobalParam.PRINT_DECLARENUM;
	           g2.drawString(text_1,20,380);
	           g2.drawLine(7, 390, 290, 390);// 第七条横线
	           String gldh = "关联单号箱号:";
	           g2.drawString(gldh,12,405);
	           String text_expressnum = GlobalParam.PRINT_EXPRESSNUM;
	           g2.drawString(text_expressnum,80,405);
	           g2.drawLine(7, 420, 290, 420);// 下边框横线
	         return PAGE_EXISTS;
	         default:
	         return NO_SUCH_PAGE;
	   	}
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return NO_SUCH_PAGE;
      
   }
   public static void main(String[] args) {
	   doPrint();
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
	    p.setSize(700,860);//纸张大小 
	    p.setImageableArea(0,0, 310,430);//A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
	    pf.setPaper(p);
	    //    把 PageFormat 和 Printable 添加到书中，组成一个页面
	    book.append(new PrintTest(), pf);

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