package com.client.common;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * 窗口工具类
 * @author knight
 *
 */
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
    /**
     * data日期对象转换为xml传输的日期对象
     * @param date
     * @return
     */
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
    
    public static void play(InputStream in){
    	try{
    		//用输入流打开一个音频文件
    		//InputStream in = new FileInputStream(in);
    		//从输入流中创建一个AudioStream对象
    		AudioStream as = new AudioStream(in);
    		AudioPlayer.player.start(as);//用静态成员播放音乐
    		//AudioPlayer.player.stop(as);//用静态成员关闭音乐
    		/*AudioData data = as.getData(); 
    	  　　ContinuousAudioDataStream gg= new ContinuousAudioDataStream (data); 
    	  　　AudioPlayer.player.start(gg);// Play audio. 
    	  　　*/ 
    		//如果要用一个 URL 做为声音流的源(source)，则用下面的代码所示替换输入流来创建声音流： 
    		/*AudioStream as = new AudioStream (url.openStream()); 
    	  　　*/
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public static void main(String[] args) throws Exception {
    	for(int i=0;i<10000;i++){
    		play(new FrameUtil().getClass().getResourceAsStream("/source/music/b_print.wav")); 
    	}
    	/*ImageLoader imageLoader = new ImageLoader();   
    	 ImageData[] imageData = imageLoader.load("D:\\1000000000006.jpg");
    	 File directiory=new File("D:\\TEST/ETS");
    	 if(!directiory.exists()){
    		 directiory.mkdirs();
    	 }
         imageLoader.data = imageData;   
         imageLoader.save("D:\\TEST/ETS/TETS.JPG", SWT.IMAGE_JPEG);*/   
	}
}
