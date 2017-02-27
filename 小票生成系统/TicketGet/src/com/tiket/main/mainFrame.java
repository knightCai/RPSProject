package com.tiket.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class mainFrame extends ApplicationWindow {
	private Text text_filepath;
	private Text text_prcimage;
	private ProgressBar progbar;	//进度条
	private DateTime date_starttime; //开始时间
	private DateTime date_endtime; //结束时间
	private SimpleDateFormat sdf;
	private String classpath = "";
	private Text text_savepath;
	/**
	 * Create the application window.
	 */
	public mainFrame() {
		super(null);
		sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout gl_container = new GridLayout(8, false);
		gl_container.horizontalSpacing = 10;
		gl_container.verticalSpacing = 10;
		container.setLayout(gl_container);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.widthHint = 108;
		label.setLayoutData(gd_label);
		label.setText("导入文件：");
		
		text_filepath = new Text(container, SWT.BORDER);
		GridData gd_text_filepath = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_filepath.widthHint = 427;
		text_filepath.setLayoutData(gd_text_filepath);
		
		//浏览文件按钮
		Button btn_opfile = new Button(container, SWT.NONE);
		btn_opfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog file = new FileDialog(getShell());
				String filepath = file.open();
				text_filepath.setText(filepath==null?"":filepath);
			}
		});
		GridData gd_btn_opfile = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btn_opfile.widthHint = 98;
		btn_opfile.setLayoutData(gd_btn_opfile);
		btn_opfile.setText("\u6D4F\u89C8");
		
		Button btn_downtemp = new Button(container, SWT.NONE);
		btn_downtemp.setText("模板下载");
		btn_downtemp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					DirectoryDialog dd=new DirectoryDialog(getShell());  
					dd.setMessage("setMessage");  
					dd.setText("setText");  
					dd.setFilterPath("C://");  
					String saveFile=dd.open(); 
					if(saveFile != null){
						boolean flg= FileUtil.downloadLocal(saveFile,"/source/ticketTemple.xls","小票导入模板.xls");
						if(flg){
							MessageDialog.openInformation(getShell(), "系统提示", "模板下载成功!保存路径为："+saveFile+"\\小票导入模板.xls");
						}else{
							MessageDialog.openError(getShell(), "系统提示", "模板下载失败！");
						}
					}
				 }catch(Exception ex){
					 System.out.print("创建失败");
				 }  
			}
		});
		GridData gd_btn_downtemp = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btn_downtemp.widthHint = 107;
		btn_downtemp.setLayoutData(gd_btn_downtemp);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u5C0F\u7968\u65F6\u95F4\u6BB5\uFF1A");
		
		date_starttime = new DateTime(container, SWT.BORDER | SWT.LONG);
		GridData gd_date_starttime = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_date_starttime.widthHint = 159;
		date_starttime.setLayoutData(gd_date_starttime);
		
		date_endtime = new DateTime(container, SWT.BORDER | SWT.LONG);
		GridData gd_date_endtime = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_date_endtime.widthHint = 158;
		date_endtime.setLayoutData(gd_date_endtime);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setText("图片保存路径:");
		
		text_savepath = new Text(container, SWT.BORDER);
		GridData gd_text_savepath = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_savepath.widthHint = 409;
		text_savepath.setLayoutData(gd_text_savepath);
		
		Button btn_selectsavepath = new Button(container, SWT.NONE);
		btn_selectsavepath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog savefile = new DirectoryDialog(getShell());
				//设置文件对话框的标题
				savefile.setText("保存目录选择");
				//设置初始路径
				savefile.setFilterPath("SystemDrive");
				String savestr = savefile.open();
				if(savestr != null){
					text_savepath.setText(savestr);
					classpath = savestr+"\\receiptFile";
				}
			}
		});
		GridData gd_btn_selectsavepath = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btn_selectsavepath.widthHint = 97;
		btn_selectsavepath.setLayoutData(gd_btn_selectsavepath);
		btn_selectsavepath.setText("选择");
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					File dire = new File(classpath);
					if(dire.exists()){
						Runtime.getRuntime().exec("explorer "+classpath);
					}else{
						MessageDialog.openInformation(getShell(), "系统提示", "请先生成图片！");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 125;
		button.setLayoutData(gd_button);
		button.setText("打开保存文件夹");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u5F53\u524D\u8FDB\u5EA6\uFF1A");
		
		progbar = new ProgressBar(container, SWT.NONE);
		GridData gd_progressBar = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_progressBar.heightHint = 16;
		gd_progressBar.widthHint = 389;
		progbar.setMinimum(0);
		progbar.setMaximum(100);
		progbar.setLayoutData(gd_progressBar);
		
		Button btn_create = new Button(container, SWT.NONE);
		GridData gd_btn_create = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btn_create.widthHint = 97;
		btn_create.setLayoutData(gd_btn_create);
		//生成图片
		btn_create.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取导入文件信息
				String filepath= text_filepath.getText();
				if(filepath != null){
					if(filepath==null || filepath.equals("") || classpath.equals("") || text_savepath.equals("")){
						MessageDialog.openWarning(getShell(), "系统提示", "请选择路径信息！");
						return;
					}
					progbar.setSelection(0);
					text_prcimage.setText("");
					Map<String,List<List<String>>> ticketmap = new ExcelUtil().read(filepath);
					createImageBatch(ticketmap);
				}
			}
		});
		btn_create.setText("生成图片");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		text_prcimage = new Text(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_prcimage = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_text_prcimage.heightHint = 257;
		text_prcimage.setLayoutData(gd_text_prcimage);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel = new Label(container, SWT.NONE);

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			mainFrame window = new mainFrame();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("小票生成系统");
		newShell.setMinimumSize(920, 560);
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(945, 560);
	}
	
	//批量生产图片
	private void createImageBatch(Map<String, List<List<String>>> ticketmap){
		//抬头信息集合
		List<List<String>> riseList = ticketmap.get("0");
		//物品信息集合
		List<List<String>> logisticsList = ticketmap.get("1");
		//物品临时信息
		List<List<String>> tmplist;
		//提单号及订单号map
		Map<String, String> map = new HashMap<String, String>();
		/**
		 * 遍历物品信息集合，去除重复的：序号+订单号-提单号信息
		 */
		for(List<String> list1:logisticsList){
			if(list1.get(2).equals("")||list1.get(1).equals("")){
				continue;
			}
			map.put(list1.get(0)+ "-" + list1.get(2), list1.get(1));
		}
		/**
		 * 通过"订单号-提单号"筛选同类信息，打印在同一张小票内
		 */
		int prog = 0;
		int count = map.size();
		for(String key_ddh:map.keySet()){
			tmplist = new ArrayList<List<String>>();
			String value_tdh = map.get(key_ddh);
			//获取一个随机数，随机获取抬头信息
			int x=(int)(Math.random()*riseList.size());
			//遍历集合
			for(List list2:logisticsList){
				//筛选
				if(list2.get(1).equals(value_tdh) && (list2.get(0).toString()+"-" +list2.get(2).toString()).equals(key_ddh)){
					tmplist.add(list2);
				}
			}
			String oldinfo = text_prcimage.getText();
			//把当前筛选出来的单个物品信息集合写入图片
			if(drawTicketImage(riseList.get(x),tmplist)){
				oldinfo += "序号-订单号:"+key_ddh + ",小票生成成功！" + "\r\n";
			}else{
				oldinfo += "----->序号-订单号:"+key_ddh + ",小票生成失败！<-------" + "\r\n";
			}
			text_prcimage.setText(oldinfo);
			prog++;
			progbar.setSelection(prog/count*100);
		}
	}
	
	/**
	 * 生成小票图片
	 * @param logisctis
	 */
	private boolean drawTicketImage(List rise,List<List<String>> logisctis){
		try 
		{ 
			ImageIcon imgIcon=new ImageIcon(getClass().getResource("/source/template.png"));
			Image theImg =imgIcon.getImage(); 
			int width = 250;//theImg.getWidth(null)==-1?300:theImg.getWidth(null); 
			int height = 600;//theImg.getHeight(null)==-1?600:theImg.getHeight(null); 
			BufferedImage bimage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB); 
			Graphics2D g=bimage.createGraphics(); 
			g.setColor(Color.BLACK); 
			g.setBackground(Color.WHITE); 
			g.drawImage(theImg, 0, 0, null ); 
			g.setFont(new Font(null,Font.BOLD,11)); //字体、字型、字号
			//写入文字信息
			drawString(g, rise, logisctis);
			
			g.dispose(); 
			File dire = new File(classpath+"/"+logisctis.get(0).get(1));
			if(!dire.exists()){
				dire.mkdirs();
			}
			FileOutputStream out=new FileOutputStream(classpath+"/"+logisctis.get(0).get(1)+"/"+logisctis.get(0).get(0)+"-"+logisctis.get(0).get(2)+".jpg"); //先用一个特定的输出文件名 
			JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out); 
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage); 
			param.setQuality(2, true); 
			encoder.encode(bimage, param); 
			out.close(); 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		return true; 
	}
	
	private void drawString(Graphics2D g,List<String> rise,List<List<String>> logisctis){
		/**
		 * 抬头信息
		 */
		//店铺标签
		g.drawString(rise.get(0),15,30);
		g.setFont(new Font(null,Font.PLAIN,11)); //字体、字型、字号
		//地址
		g.drawString(rise.get(1),15,50);
		//电话
		g.drawString("Phone: " + rise.get(2),15,70);
		//传真
		g.drawString("Fax: " + rise.get(3),15,90);
		
		g.drawString("SALES RECEIPT",90,125);
		g.drawString("Item Name",15, 160);
		g.drawString("Amt.",140, 160);
		g.drawString("Amnt.",180, 160);
		//g.drawString("Gst",260, 160);
		g.drawString("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ",10,190);
		
		/**
		 * 物品内容
		 */
		int y = 0;
		float amount = 0f;
		//币种
		String bz = "$";
		for(List<String> list:logisctis){
			int amt =Integer.parseInt(list.get(6));
			//折扣后的单价
			float amnt = Float.parseFloat(list.get(7));
			bz = list.get(8);
			//折扣随机 6~0 折，中国折扣 10 6折 = 60
			//int gst = 10-(int)(new Random().nextInt(5));
			//品牌 物品名称 规格
			g.drawString(list.get(4) +" " +list.get(3)+" "+  list.get(5),15,200+y);
			//购买数量
			g.drawString(amt+"",150,200+y);
			//购买单价
			//g.drawString(String.format("%.2f",amnt/(gst*0.1))+ "",180, 200+y);
			g.drawString(String.format("%.2f",amnt)+ "",180, 200+y);
			//折扣%
			//g.drawString("*"+gst*10+"%",260, 200+y);
			amount += amt * amnt;
			y += 20;
		}
		g.drawString(bz,180, 180);
		/**
		 * 金额统计信息
		 * amt * amnt * gst
		 */
		g.drawString("Total",15, 220+y);
		g.drawString(bz+String.format("%.2f", amount),180, 220+y);
		g.drawString("Amount to Pay",15, 240+y);
		g.drawString(bz+String.format("%.2f", amount),180, 240+y);
		g.drawString("Cash change",15, 260+y);
		g.drawString(bz+"0.00",180, 260+y);
		//生成日期
		String beginDate = date_starttime.getYear()+"-"+date_starttime.getMonth()+"-"+date_starttime.getDay();
		String endDate = date_endtime.getYear()+"-"+date_endtime.getMonth()+"-"+date_endtime.getDay();
		Date randomDate=randomDate(beginDate,endDate); 
		g.drawString(sdf.format(randomDate), 70, 285+y);
		//欢送语
		String str_hsy = rise.get(4);
		if(str_hsy.length()>35){
			g.drawString(str_hsy.substring(0,35),20,300+y);
			String tempstr = str_hsy.substring(35,str_hsy.length());
			g.drawString(tempstr,(250-tempstr.length()*6)/2,320+y);
		}else{
			g.drawString(str_hsy,(250-str_hsy.length()*6)/2,300+y);
		}
	}
	
	
	/** 
	* 生成随机时间 
	* @param beginDate 
	* @param endDate 
	* @return 
	*/ 
	private static Date randomDate(String beginDate,String  endDate ){  

	try {  
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Date start = format.parse(beginDate);//构造开始日期  
		Date end = format.parse(endDate);//构造结束日期  
		//getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
		long oneday = 0;
		oneday = 24*60*60*1000;  
		long date = random(start.getTime(),end.getTime()+oneday);  
		return new Date(date);  
	} catch (Exception e) {  
		e.printStackTrace();  
	}  
	return null;  
	}  

	private static long random(long begin,long end){  
		long rtn = begin + (long)(Math.random() * (end - begin));  
		//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
		if(rtn == begin || rtn == end){  
			return random(begin,end);  
		}  
		return rtn;  
	}  
}
