package com.client.frame.system;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.SwingUtilities;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.client.common.FrameUtil;
import com.client.model.contrllo.SysparamContrllo;
import com.service.service.Sysparam;

import org.eclipse.wb.swt.SWTResourceManager;

public class systemUp {

	protected Shell shell;
	private ProgressBar prgBar;
	private Label prst;
	private float bytesRead;
	private float filesize;
	private Thread downth;
	private SysparamContrllo syscon;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			systemUp window = new systemUp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		FrameUtil.center(shell);
		syscon = new SysparamContrllo();
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		label.setBounds(147, 22, 215, 30);
		label.setText("系统升级中，请稍后......");
		//创建一个下载线程
		downth = new Thread(){
			@Override
			public void run() {
				super.run();
				try {
					String uppath = syscon.findSysByParamname("SYS_ONLINEUPPATH").getValue();
					//下载更新包
					downLoadFromUrl(uppath, "rpsclientUp.zip", System.getProperty("user.dir"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//下载完成后提示用户,执行bat脚本进行exe文件的替换，已达到系统更新的目的
				Display.getDefault().syncExec(new Runnable() {
                    public void run() {  
						MessageDialog.openQuestion(shell, "系统提示", "系统更新已完成，请点击'OK'按钮，稍后系统将重新启动！");
						try {
							Runtime.getRuntime().exec("cmd.exe   /C   start    updateBat.bat");
						} catch (IOException e) {
							e.printStackTrace();
						}
                    }
                });
			}
		};
		downth.start();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.APPLICATION_MODAL);    
		shell.setSize(526, 233);
		shell.setText("SWT Application");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(35, 86, 75, 20);
		lblNewLabel.setText("更新进度：");
		
		prgBar = new ProgressBar(shell, SWT.NONE);
		prgBar.setBounds(116, 86, 318, 21);
		
		prst = new Label(shell, SWT.NONE);
		prst.setBounds(440, 86, 45, 20);
		prst.setText("0%");
		
		/**
		 * 系统更新取消按钮
		 */
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		button.setBounds(199, 132, 98, 30);
		button.setText("取消");
	}
	
	/** 
     * 下载客户端更新，从网络Url中下载文件 
     * @param urlStr url地址
     * @param fileName 文件保存名称
     * @param savePath 保存路径
     * @throws IOException 
     */  
    public void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        filesize = conn.getContentLength();//文件长度  
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
    public  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        bytesRead = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        int byteCount;  
        while(bytesRead < filesize){   
                 //从输入流中读取一定数量的字节，并将其存储在缓冲区数组 buffer中  
                 //以整数形式返回实际读取的字节数。存储在缓冲区整数 byteCount中。  
                 byteCount = inputStream.read(buffer);  
                 if(byteCount == -1){  
                     break;  
                 }else{  
                	 bos.write(buffer, 0, byteCount);  
                     bytesRead += byteCount;  
                     //刷新进度条和completeLabel：是AWT时间线程与下载线程同步  
                     Display.getDefault().syncExec(new Runnable() {
                         public void run() {  
                             prgBar.setSelection((int)(bytesRead/filesize * 100));  
                             prst.setText((int)(bytesRead/filesize * 100) +"%");  
                         }  
                     }); 
                 }  
        }
        bos.close();    
        return bos.toByteArray();    
    }    
}
