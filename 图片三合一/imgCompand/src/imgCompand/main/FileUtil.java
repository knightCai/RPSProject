package imgCompand.main;

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

public class FileUtil {
	
	/**
	 * 下载客户端本地的导入模板
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static boolean downloadLocal(String filepath,String temppath,String fileName) throws FileNotFoundException {
        // 下载本地文件
        // 读到流中
        InputStream inStream = new FileUtil().getClass().getResourceAsStream(temppath);// 文件的存放路�?
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        // 设置输出的格�?
        // 循环取出流中的数�?
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
	       ZipOutputStream zos = new ZipOutputStream(bos);//压缩�?
	       ZipEntry ze = new ZipEntry(f.getName());//这是压缩包名里的文件�?
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
