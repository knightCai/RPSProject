package com.client.common;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class FrameUtil {
	/**
     * 设置窗口位于屏幕中间
     * @param shell 要调整位置的窗口对象
     */
    public static void center(Shell shell)
    {
        //获取屏幕高度和宽度
        int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
        //获取对象窗口高度和宽度
        int shellH = shell.getBounds().height;
        int shellW = shell.getBounds().width;
        
        //如果对象窗口高度超出屏幕高度，则强制其与屏幕等高
        if(shellH > screenH)
            shellH = screenH;
        
        //如果对象窗口宽度超出屏幕宽度，则强制其与屏幕等宽
        if(shellW > screenW)
            shellW = screenW;
        
        //定位对象窗口坐标
        shell.setLocation(((screenW - shellW) / 2), ((screenH - shellH) / 2));
    }
    
    public static String getUUID(){
    	String str = UUID.randomUUID().toString();
    	return str.replace("-", "");
    } 
    
    public static void captureCompositeToImage(Shell shell,Composite composite,int xDifference,int yDifference,int imageWidth, int imageHeight,String imgUrl) {   
        
        GC gc = new GC(composite);  
        //设置默认的宽度  
        if(imageWidth<=0){  
         imageWidth=1050;  
        }  
          
        //设置默认的高度  
        if(imageHeight<=0){  
         imageHeight=450;  
        }  
           
        
       //始终最前  (50:x轴,50:主轴,1050:图像的x轴的长度,450:图像的y轴的长度)  
       OS.SetWindowPos(shell.handle , OS.HWND_TOPMOST, 50 , 50 ,imageWidth, imageHeight , SWT.NULL);   
       shell.setVisible(true);   
       shell.open();  
       shell.layout();  
       try {  
            Thread.sleep(30);  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
       Point size = composite.getSize();   
              
       File directiory=new File(GlobalParam.PRINT_IMAGEPATH+GlobalParam.PRINT_IMPORTSER+"/");
	  	 if(!directiory.exists()){
	  		 directiory.mkdirs();
	  	 }
       Image image = new Image(shell.getDisplay(), size.x-xDifference, size.y-yDifference);    
       gc.copyArea(image, 0, 0);   
       shell.setVisible(false);   
         
       ImageLoader imageLoader = new ImageLoader();   
       imageLoader.data = new ImageData[] {image.getImageData()};   
       imageLoader.save(imgUrl, SWT.IMAGE_JPEG);   
       //gc.dispose();   
       //image.dispose();   
       //display.dispose();  
         
        
       //System.out.println("captured");   
   }  
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {

             e.printStackTrace();
        }
        return gc;
    }
    public static void main(String[] args) throws Exception {
    	 ImageLoader imageLoader = new ImageLoader();   
    	 ImageData[] imageData = imageLoader.load("D:\\1000000000006.jpg");
    	 File directiory=new File("D:\\TEST/ETS");
    	 if(!directiory.exists()){
    		 directiory.mkdirs();
    	 }
         imageLoader.data = imageData;   
         imageLoader.save("D:\\TEST/ETS/TETS.JPG", SWT.IMAGE_JPEG);   
	}
}
