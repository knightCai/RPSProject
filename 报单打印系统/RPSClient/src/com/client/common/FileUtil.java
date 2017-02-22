package com.client.common;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream; //这个包在ant.jar里，要到官方网下载
import org.eclipse.swt.SWT;

import com.service.service.Logisticslisting;

public class FileUtil {
	
	/**
	 * 下载客户端本地的导入模板
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean downloadLocal(String filepath) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "报关单导入模板.xls".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileUtil().getClass().getResourceAsStream(GlobalParam.SOURCE_IMPORTTEMPLE);// 文件的存放路径
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        // 设置输出的格式
        // 循环取出流中的数据
        try {
        	File directiory=new File(filepath,fileName); 
            bis = new BufferedInputStream(inStream);
            bos = new BufferedOutputStream(new FileOutputStream( directiory));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }
	/** 
     * 下载客户端更新，从网络Url中下载文件 
     * @param urlStr url地址
     * @param fileName 文件保存名称
     * @param savePath 保存路径
     * @throws IOException 
     */  
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        int fileSize = conn.getContentLength();//文件长度  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);      
  
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
  
  
        System.out.println("info:"+url+" download success");   
  
    }  
  
  
  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
  
	/**
	 * 本地面单图片压缩下载
	 * @param filepath
	 * @throws Exception 
	 */
	public static void downloadPics(List<Logisticslisting> logislist,String zipFileName,String filepath) throws Exception{
		createPrintImg(logislist, filepath);
		new FileUtil().zip(zipFileName,filepath);
		deleatfangf(new File(filepath));
	}
	/*
    * inputFileName 输入一个文件夹
    * zipFileName 输出一个压缩文件夹
    */
    public void zip(String zipFileName,String inputFileName) throws Exception {
        System.out.println(zipFileName);
        zip(zipFileName, new File(inputFileName));
    }

    private void zip(String zipFileName, File inputFile) throws Exception {
    	if(!inputFile.exists()){
    		throw new Exception("未找到面单图片！");
        }
    	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
    	zip(out, inputFile, "");
    	System.out.println("zip done");
    	out.close();
    }

    private void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
           File[] fl = f.listFiles();
           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
           base = base.length() == 0 ? "" : base + "/";
           for (int i = 0; i < fl.length; i++) {
	           zip(out, fl[i], base + fl[i].getName());
	        }
        }else {
           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
           FileInputStream in = new FileInputStream(f);
           int b;
           System.out.println(base);
           while ( (b = in.read()) != -1) {
            out.write(b);
         }
         in.close();
       }
    }
   
    /**
     * 客户端生成面单图片，实时生成非截图方式
     * @param logislist
     * @param filepath
     * @throws IOException
     */
    public static void createPrintImg(List<Logisticslisting> logislist,String filepath) throws IOException{
    	//面单过滤条件集合
    	Map<String, String> numMap = new HashMap<String, String>();
    	//创建打印对象，需使用其绘图方法
    	PrintTest print = new PrintTest();
    	//定义bufferedImage、File对象
    	BufferedImage bufferimg;
    	File file,directiory;
    	//通过报关单号筛选同一打印面单
    	for (Logisticslisting logis : logislist) {
			numMap.put(logis.getDeclarenum(), logis.getExpressnum());
		}
    	//拼接同一面单的打印信息并生成对应图片
    	for(String key : numMap.keySet()){
    		//1、初始化面单参数
    		GlobalParam.PRINT_WEIGHT = "0";
			GlobalParam.PRINT_CARGONAME_CARGOTYPE = "";
			String lineChg = "\r\n";
    		for (Logisticslisting logis : logislist) {
    			if(key.equals(logis.getDeclarenum())&& numMap.get(key).equals(logis.getExpressnum())){
					GlobalParam.PRINT_CONSIGNERNAME = logis.getConsignername();
					GlobalParam.PRINT_CONSIGNERADDR = logis.getConsigneraddr();
					GlobalParam.PRINT_CONSIGNERPHONE = logis.getConsignerphone();
					GlobalParam.PRINT_CONSIGNEENAME = logis.getConsigneename();
					GlobalParam.PRINT_CONSIGNEEADDR = logis.getConsigneeaddr();
					GlobalParam.PRINT_CONSIGNEEPHONE = logis.getConsigneephone();
					GlobalParam.PRINT_CARGONAME_CARGOTYPE += logis.getCargoname() + "*"+ logis.getCount()+ "  " + logis.getBrand() + "，"  + logis.getCargotype()+ lineChg;
					GlobalParam.PRINT_WEIGHT = (Float.parseFloat(GlobalParam.PRINT_WEIGHT) + Float.parseFloat(logis.getDeclareweight())) + "";
					GlobalParam.PRINT_EXPRESSNUM = logis.getExpressnum();
					GlobalParam.PRINT_DECLARENUM = logis.getDeclarenum();
					GlobalParam.PRINT_CONSIGNERCOUNTRY = logis.getConsignercountry();
					GlobalParam.PRINT_IMPORTSER = logis.getImportnum()+"";
    			}
    		}
    		//2、生成图片
    		bufferimg = new BufferedImage(300,430, BufferedImage.TYPE_INT_RGB);
    		directiory=new File(filepath+"/");
	   	  	if(!directiory.exists()){
	   	  		directiory.mkdirs();
	   	  	}
    		file = new File(filepath+"/"+GlobalParam.PRINT_DECLARENUM + ".jpg");
    		//设置背景为白色
    		Graphics2D g2 = (Graphics2D) bufferimg.getGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0, 0, 300, 430);
            print.print(g2, null, 0);
    		ImageIO.write(bufferimg, "png", file);
    	}
    	
    }
    //删除指定文件夹下面的所有文件
    public static void deleatfangf(File f){
    	if (f==null) {
    		return;
    	}else if(f.exists()){//如果此抽象指定的对象存在并且不为空。
	    	if(f.isFile()){
	    		f.delete();//如果此抽象路径代表的是文件，直接删除。
	    	}else if(f.isDirectory()){//如果此抽象路径指代的是目录
	    		String[] str = f.list();//得到目录下的所有路径
		    	if(str==null){
		    		f.delete();//如果这个目录下面是空，则直接删除
		    	}else{//如果目录不为空，则遍历名字，得到此抽象路径的字符串形式。
			    	for (String st : str) {
			    	deleatfangf(new File(f,st));
			    	}//遍历清楚所有当前文件夹里面的所有文件。
			    	f.delete();//清楚文件夹里面的东西后再来清楚这个空文件夹
		    	}
	    	}
    	}
	}
    public static void main(String[] args) throws Exception
     {
    	downLoadFromUrl("http://47.91.140.252:7001/RPSService/imgCompand.zip", "rpsclient.zip", "D:\\");
       //new FileUtil().zip("D:\\rps_images\\");
       /*File f = new File("D:\\rps_images\\20000001.jpg");
       FileInputStream fis = new FileInputStream(f);
       BufferedInputStream bis = new BufferedInputStream(fis);
       byte[] buf = new byte[1024];
       int len;
       FileOutputStream fos = new FileOutputStream(f.getName()+".zip");
       BufferedOutputStream bos = new BufferedOutputStream(fos);
       ZipOutputStream zos = new ZipOutputStream(bos);//压缩包
       ZipEntry ze = new ZipEntry(f.getName());//这是压缩包名里的文件名
       zos.putNextEntry(ze);//写入新的 ZIP 文件条目并将流定位到条目数据的开始处

       while((len=bis.read(buf))!=-1)
       {
          zos.write(buf,0,len);
          zos.flush();
       }
       bis.close();
       zos.close(); */
     }

}
