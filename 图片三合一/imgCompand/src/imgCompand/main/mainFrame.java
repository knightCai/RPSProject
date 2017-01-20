package imgCompand.main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.FileDialog;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.wb.swt.SWTResourceManager;

public class mainFrame {

	protected Shell shell;
	private Text text_mdtp;
	private Label lblNewLabel;
	private Text text_sfztp;
	private Button btnNewButton_1;
	private Label label_1;
	private Text text_xptp;
	private Button button;
	private Label label_2;
	private Label label_3;
	private Text text_hctp;
	private Button btnNewButton_2;
	private Label label_4;
	private Text text;
	private Button btnexecl;
	private Label label_5;
	private Button button_1;
	private Button btn_doCompImg;
	private Text text_info;
	private Label label_6;
	private ProgressBar progressBar;
	private Label lblNewLabel_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			merge(new String[]{"D:\\receiptFile\\999160101010.jpg","D:\\receiptFile\\20170120094926.jpg","D:\\receiptFile\\999160101011.jpg"}, 1,"C:\\Users\\knight\\Desktop\\sdfasf.jpg");
			mainFrame window = new mainFrame();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(mainFrame.class, "/imgCompand/main/logo.png"));
		shell.setSize(935, 650);
		shell.setMinimumSize(920, 650);
		center(shell);
		shell.setText("三合一");
		shell.setLayout(new GridLayout(8, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("面单图片:");
		
		text_mdtp = new Text(shell, SWT.BORDER);
		text_mdtp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd=new DirectoryDialog(shell);  
				dd.setMessage("setMessage");  
				dd.setText("选择保存面单图片的文件夹");  
				dd.setFilterPath("C://");  
				String mdtp=dd.open();
				if(mdtp!=null){
					text_mdtp.setText(mdtp);
				}
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_btnNewButton.widthHint = 95;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("浏览");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 87;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("身份证图片:");
		
		text_sfztp = new Text(shell, SWT.BORDER);
		text_sfztp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd=new DirectoryDialog(shell);  
				dd.setMessage("setMessage");  
				dd.setText("选择保存身份证图片的文件夹");  
				dd.setFilterPath("C://");  
				String sfztp=dd.open();
				if(sfztp!=null){
					text_sfztp.setText(sfztp);
				}
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_btnNewButton_1.widthHint = 95;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("浏览");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setText("小票图片:");
		
		text_xptp = new Text(shell, SWT.BORDER);
		text_xptp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd=new DirectoryDialog(shell);  
				dd.setText("选择保存小票图片的文件夹");  
				dd.setFilterPath("C://");  
				String xptp=dd.open();
				if(xptp!=null){
					text_xptp.setText(xptp);
				}
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_button.widthHint = 95;
		button.setLayoutData(gd_button);
		button.setText("浏览");
		
		label_2 = new Label(shell, SWT.NONE);
		label_2.setText("                           ");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setText("             ");
		new Label(shell, SWT.NONE);
		
		label_5 = new Label(shell, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("三合一导入模板下载，请点击........");
		new Label(shell, SWT.NONE);
		
		button_1 = new Button(shell, SWT.NONE);
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 95;
		button_1.setLayoutData(gd_button_1);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					DirectoryDialog dd=new DirectoryDialog(shell);  
					dd.setText("请选择文件保存的位置");  
					dd.setFilterPath("C://");  
					String saveFile=dd.open();  
					if(saveFile!=null){
						boolean flg = FileUtil.downloadLocal(saveFile, "/template.xls", "三合一导入模板.xls");
						if(flg){
							MessageDialog.openInformation(shell, "系统提示", "模板下载成功!保存路径为："+saveFile+"/三合一导入模板.xls");
						}else{
							MessageDialog.openError(shell, "系统提示", "模板下载失败！");
						}
					}
				 }catch(Exception ex){
					 System.out.print("创建失败");
				 }  
			}
		});
		button_1.setText("导入模板");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setText("合成信息：");
		
		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		btnexecl = new Button(shell, SWT.NONE);
		btnexecl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog filedia = new FileDialog(shell, SWT.SINGLE);
				String filepath = filedia.open()+"";
				text.setText(filepath.replace("null",""));
			}
		});
		GridData gd_btnexecl = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnexecl.widthHint = 95;
		btnexecl.setLayoutData(gd_btnexecl);
		btnexecl.setText("导入execl");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setText("合成图片:");
		
		text_hctp = new Text(shell, SWT.BORDER);
		text_hctp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd=new DirectoryDialog(shell);  
				dd.setText("选择合成图片将要保存的文件夹");  
				dd.setFilterPath("C://");  
				String hctp=dd.open();
				if(hctp!=null){
					text_hctp.setText(hctp);
				}
			}
		});
		GridData gd_btnNewButton_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_2.widthHint = 95;
		btnNewButton_2.setLayoutData(gd_btnNewButton_2);
		btnNewButton_2.setText("保存位置");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setText("进度：");
		
		progressBar = new ProgressBar(shell, SWT.NONE);
		GridData gd_progressBar = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_progressBar.widthHint = 524;
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setLayoutData(gd_progressBar);
		new Label(shell, SWT.NONE);
		
		btn_doCompImg = new Button(shell, SWT.NONE);
		btn_doCompImg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//导入信息
				String drxx = text.getText();
				//保存路径
				String hctp = text_hctp.getText();
				//面单图片路径
				String mdtp = text_mdtp.getText();
				//身份证图片路径
				String sfztp = text_sfztp.getText();
				//小票图片路径
				String xptp = text_xptp.getText();
				if(!drxx.equals("")&&!hctp.equals("")&&!mdtp.equals("")&&!sfztp.equals("")&&!xptp.equals("")){
					//解析后的数据
					List<ArrayList<String>> datalist = new ExcelUtil().read(drxx);
					File dir  = new File(hctp);
					if(!dir.exists()){
						try {
							dir.mkdirs();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					int prog = 1;
					for(List pic:datalist){
						String[] files = {mdtp,sfztp,xptp};
						String savepath = hctp;
						files[0] += "\\"+pic.get(1)+".jpg";
						files[1] += "\\"+pic.get(2)+".jpg";
						files[2] += "\\"+pic.get(3)+".jpg";
						savepath += "\\"+pic.get(0)+".jpg";
						String oldinfo = text_info.getText();
						if(merge(files, 1, savepath)){
							oldinfo += "报关单号:"+pic.get(0) + ",图片合成成功！" + "\r\n";
						}else{
							oldinfo += "----->报关单号:"+pic.get(0) + ",图片合成失败！<-------" + "\r\n";
						}
						text_info.setText(oldinfo);
						progressBar.setSelection(prog/datalist.size()*100);
						prog++;
					}
				}else{
					MessageDialog.openWarning(shell, "系统提示","各路径选项不能为空！");
				}
			}
		});
		GridData gd_btn_doCompImg = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btn_doCompImg.widthHint = 95;
		btn_doCompImg.setLayoutData(gd_btn_doCompImg);
		btn_doCompImg.setText("立即合成");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		text_info = new Text(shell, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_info = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_info.heightHint = 217;
		text_info.setLayoutData(gd_text_info);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

	}
	
	/** 
     * 图片拼接 
     * @param files  要拼接的文件列表 
     * @param type   1 横向拼接， 2 纵向拼接 
     * @return 
     */  
    public static boolean merge(String[] files, int type,String savepath) {  
    	try {  
	        int len = files.length;  
	        if (len < 1) {  
	            System.out.println("图片数量小于1");  
	            return false;  
	        }  
	          
	        File[] src = new File[len];  
	        BufferedImage[] images = new BufferedImage[len];  
	        int[][] ImageArrays = new int[len][];  
	        for (int i = 0; i < len; i++) {  
	            src[i] = new File(files[i]);  
	            images[i] = ImageIO.read(src[i]);  
	            int width = images[i].getWidth();  
	            int height = images[i].getHeight();  
	            ImageArrays[i] = new int[width * height];// 从图片中读取RGB  
	            ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);  
	        }  
	  
	        int newHeight = 0;  
	        int newWidth = 0;  
	        for (int i = 0; i < images.length; i++) {  
	            //横向  
	            if(type == 1){  
	                newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();  
	                newWidth += images[i].getWidth();  
	            }else if(type == 2){//纵向  
	                newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();  
	                newHeight += images[i].getHeight();  
	            }  
	        }  
	          
	        System.out.println("拼接后图像宽度："+newWidth);  
	        System.out.println("拼接后图像高度："+newHeight);  
	        if (type == 1 && newWidth < 1) {  
	        	 System.out.println("拼接后图像宽度小于1");  
	            return false;  
	        }  
	        if (type == 2 && newHeight < 1) {  
	        	 System.out.println("拼接后图像高度小于1");  
	            return false;  
	        }  
  
	        //生成新图片  
        	ImageIcon imgIcon=new ImageIcon(new Object().getClass().getResource("/bg.png"));
    		Image theImg =imgIcon.getImage(); 
            BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g=ImageNew.createGraphics(); 
            g.drawImage(theImg, 0, 0, null ); 
            int height_i = 0;  
            int width_i = 0;  
            for (int i = 0; i < images.length; i++) {  
                if(type == 1){  
                    ImageNew.setRGB(width_i, 0, images[i].getWidth(), images[i].getHeight(), ImageArrays[i], 0, images[i].getWidth());  
                    width_i += images[i].getWidth();  
                }else if(type == 2){  
                    ImageNew.setRGB(0, height_i, images[i].getWidth(), images[i].getHeight(), ImageArrays[i], 0, images[i].getWidth());  
                    height_i += images[i].getHeight();  
                }  
            }  
	          File outFile = new File(savepath);  
	          ImageIO.write(ImageNew, "jpg", outFile);// 写图片  
              
//            ByteArrayOutputStream out = new ByteArrayOutputStream();  
//            ImageIO.write(ImageNew, "jpg", out);// 图片写入到输出流中  
              
//            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());  
//            return in;  
              
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    	return true;
    }  
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
}
