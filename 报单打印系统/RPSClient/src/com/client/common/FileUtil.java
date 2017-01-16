package com.client.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream; //这个包在ant.jar里，要到官方网下载

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
        InputStream inStream = new FileUtil().getClass().getResourceAsStream("/source/importTemple.xls");// 文件的存放路径
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
	 * 本地面单图片压缩下载
	 * @param filepath
	 * @throws Exception 
	 */
	public static void downloadPics(String zipFileName,String filepath) throws Exception{
		new FileUtil().zip(zipFileName,filepath);
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
	    public static void main(String[] args) throws Exception
	     {
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
