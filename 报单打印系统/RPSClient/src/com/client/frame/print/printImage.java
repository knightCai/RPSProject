package com.client.frame.print;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.client.common.FrameUtil;
import com.client.common.GlobalParam;
import com.client.common.MatrixUtil;
import com.client.common.PrintTest;
import com.client.common.SWTUtils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;

public class printImage {

	protected Shell shell;
	protected Composite composite;	//用于生成打印图片的图层
	private Image printimg;	//设置背景图片为打印模板
	private String filename = "printmodel.png";
	private Text text_consignername;	//寄件人名称
	private Text text_consigneraddr;	//寄件人地址
	private Text text_consignerphone;	//寄件人电话
	private Text text_consigneename;	//收件人名称
	private Text text_consigneeaddr;	//收件人地址
	private Text text_consigneephone;	//收件人电话
	private Text text_cargoname_cargotype;	//货物内容描述：品名+品牌+数量+规格型号
	private Text text_weight;			//实际重量
	private Text text_expressnum;		//运单号码
	private Text text_consignercountry;	//发件人国家
	private Canvas canvas_declarenum1;		//报关单号1
	private Canvas canvas_declarenum2;		//报关单号2
	private Text text_passport;			//过关口岸
	private int printType = 0;		//打印模式，0-预览打印 1-直接打印
	private Text text;
	private Text text_1;
	private Text txtLeaderExpress;
	private Label lblto;
	private Label label_5;
	private Label label_6;
	private Label label_7;
	private Label label_8;
	private Label label_11;
	private Label label_12;
	private Label lblNewLabel;
	private Label label_13;
	private Label label_14;
	private Label label_15;
	private Label label_16;
	private Label label_17;
	private Label label_10;
	private Label label_18;
	private Label label_20;
	private Label label_21;
	private Label label_19;
	
	public printImage(int type) {
		printType = type;
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			printImage window = new printImage(0);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws Exception 
	 */
	public void open() throws Exception {
		Display display = Display.getDefault();
		createContents();
//		Rectangle bs = printimg.getBounds();
		shell.setSize(498, 832);
		FrameUtil.center(shell);
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(30, 10, 98, 30);
		btnNewButton.setText("打印");
		
		initComposite();
		initPrintParams();
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doprint();
			}
		});
		shell.open();
		shell.layout();
		//判断打印模式,扫码枪，直接打印
		if(printType == 1){
			doprint();
			FrameUtil.play(this.getClass().getResourceAsStream(GlobalParam.SOURCE_PRINTMUSIC)); 
		}
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
		shell.setText("面单打印预览界面");
	}
	
	/**
	 * 初始化打印面板
	 * @throws Exception 
	 */
	private void initComposite() throws Exception{
		composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//composite.setBackgroundImage(printimg);
		composite.setBounds(10, 54, 460, 724);
		
		text_consignername = new Text(composite, SWT.WRAP);
		text_consignername.setBounds(88, 135, 125, 40);

		text_consigneraddr = new Text(composite, SWT.WRAP | SWT.MULTI);
		text_consigneraddr.setBounds(29, 206, 390, 33);
		
		text_consignerphone = new Text(composite, SWT.NONE);
		text_consignerphone.setBounds(269, 167, 130, 33);
		
		text_consigneename = new Text(composite, SWT.NONE);
		text_consigneename.setBounds(83, 269, 125, 26);
		
		text_consigneeaddr = new Text(composite, SWT.WRAP | SWT.MULTI);
		text_consigneeaddr.setBounds(31, 325, 398, 44);
		
		text_consigneephone = new Text(composite, SWT.NONE);
		text_consigneephone.setBounds(269, 262, 130, 33);
		
		text_cargoname_cargotype = new Text(composite, SWT.WRAP | SWT.MULTI);
		text_cargoname_cargotype.setBounds(31, 406, 249, 114);
		text_weight = new Text(composite, SWT.NONE);
		text_weight.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_weight.setBounds(304, 416, 105, 26);
		
		text_expressnum = new Text(composite, SWT.NONE);
		text_expressnum.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		text_expressnum.setBounds(146, 663, 215, 26);
		
		text_passport = new Text(composite, SWT.NONE);
		text_passport.setBounds(304, 135, 57, 20);
		
		canvas_declarenum1 = new Canvas(composite, SWT.NONE);
		canvas_declarenum1.setBounds(199, 23, 125, 50);
		
		canvas_declarenum2 = new Canvas(composite, SWT.NONE);
		canvas_declarenum2.setBounds(50, 570, 179, 50);
		
		text_consignercountry = new Text(composite, SWT.NONE);
		text_consignercountry.setBounds(294, 487, 125, 33);
		
		text = new Text(composite, SWT.CENTER);
		text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		text.setBounds(209, 79, 130, 26);
		
		text_1 = new Text(composite, SWT.CENTER);
		text_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		text_1.setBounds(41, 626, 147, 20);
		
		txtLeaderExpress = new Text(composite, SWT.MULTI);
		txtLeaderExpress.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		txtLeaderExpress.setText("LEADER EXPRESS\r\n立达国际快递有限公司 \r\nLEADER EXPRESS CO.\r\n,ltd");
		txtLeaderExpress.setBounds(23, 34, 165, 82);
		
		Label lblform = new Label(composite, SWT.NONE);
		lblform.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblform.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblform.setBounds(29, 136, 57, 39);
		lblform.setText("寄件人\r\n/Form:");
		
		Label label = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 122, 440, 3);
		
		Label label_1 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(10, 246, 440, 3);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(29, 181, 76, 20);
		label_2.setText("地址:");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_3.setBounds(219, 136, 76, 25);
		label_3.setText("进口口岸:");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_4.setBounds(219, 174, 47, 26);
		label_4.setText("电话:");
		
		lblto = new Label(composite, SWT.NONE);
		lblto.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblto.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblto.setBounds(29, 255, 57, 40);
		lblto.setText("收件人\r\n/To:");
		
		label_5 = new Label(composite, SWT.NONE);
		label_5.setText("地址:");
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(29, 299, 76, 20);
		
		label_6 = new Label(composite, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_6.setBounds(214, 262, 41, 27);
		label_6.setText("电话:");
		
		label_7 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_7.setBounds(10, 375, 440, 3);
		
		label_8 = new Label(composite, SWT.NONE);
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_8.setBounds(31, 382, 76, 20);
		label_8.setText("货物内容描述:");
		
		Label label_9 = new Label(composite, SWT.BORDER);
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_9.setBounds(286, 378, 3, 153);
		
		label_11 = new Label(composite, SWT.NONE);
		label_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_11.setBounds(304, 384, 76, 26);
		label_11.setText("实际重量:");
		
		label_12 = new Label(composite, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_12.setBounds(294, 458, 57, 23);
		label_12.setText("原产地:");
		
		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		lblNewLabel.setBounds(41, 536, 99, 22);
		lblNewLabel.setText("收件人签名:");
		
		label_13 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_13.setBounds(10, 529, 440, 3);
		
		label_14 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_14.setBounds(10, 563, 440, 3);
		
		label_15 = new Label(composite, SWT.NONE);
		label_15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_15.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_15.setBounds(215, 536, 177, 20);
		label_15.setText("签收时间:         年    月   日");
		
		label_16 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label_16.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_16.setBounds(10, 654, 429, 3);
		
		label_17 = new Label(composite, SWT.NONE);
		label_17.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 9, SWT.BOLD));
		label_17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_17.setBounds(41, 663, 99, 20);
		label_17.setText("关联单号箱号:");
		
		label_10 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_10.setBounds(286, 449, 164, 2);
		
		label_18 = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		label_18.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_18.setBounds(10, 11, 2, 709);
		
		label_20 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label_20.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_20.setBounds(10, 10, 440, 3);
		
		label_21 = new Label(composite, SWT.BORDER | SWT.SEPARATOR | SWT.HORIZONTAL);
		label_21.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_21.setBounds(10, 716, 440, 3);
		
		label_19 = new Label(composite, SWT.SEPARATOR);
		label_19.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_19.setBounds(448, 11, 2, 709);
	}
	/**
	 * 打印字段赋值
	 * @throws Exception 
	 */
	private void initPrintParams() throws Exception{
		text_consignername.setText(GlobalParam.PRINT_CONSIGNERNAME);
		text_consigneraddr.setText(GlobalParam.PRINT_CONSIGNERADDR);;
		text_consignerphone.setText(GlobalParam.PRINT_CONSIGNERPHONE);;
		text_consigneename.setText(GlobalParam.PRINT_CONSIGNEENAME);
		text_consigneeaddr.setText(GlobalParam.PRINT_CONSIGNEEADDR);;
		text_consigneephone.setText(GlobalParam.PRINT_CONSIGNEEPHONE);
		text_cargoname_cargotype.setText(GlobalParam.PRINT_CARGONAME_CARGOTYPE);
		text_weight.setText(GlobalParam.PRINT_WEIGHT + "Kg");
		text_expressnum.setText(GlobalParam.PRINT_EXPRESSNUM);
		text_passport.setText(GlobalParam.PRINT_PASSPORT);
		printimg = barcode(GlobalParam.PRINT_DECLARENUM);
		Rectangle rct = printimg.getBounds();
		canvas_declarenum1.setBounds(199, 23,rct.width,rct.height);
		canvas_declarenum2.setBounds(50, 570,rct.width,rct.height);
		canvas_declarenum1.setBackgroundImage(printimg);
		canvas_declarenum2.setBackgroundImage(printimg);
		text.setText(GlobalParam.PRINT_DECLARENUM);
		text_1.setText(GlobalParam.PRINT_DECLARENUM);
		text_consignercountry.setText(GlobalParam.COUNTRYIDS.get(GlobalParam.PRINT_CONSIGNERCOUNTRY));
	}
	
	public void doprint(){
		try {
			//生成打印的图片并保存在指定目录
			FrameUtil.captureCompositeToImage(shell, composite,0,0,650,877,GlobalParam.PRINT_IMAGEPATH + GlobalParam.PRINT_IMPORTSER +"/"+GlobalParam.PRINT_DECLARENUM + ".jpg");
			//调用打印机进行打印
			PrintTest.doPrint();
			//ImagePrinterExample.ImagePrinter(shell);
			shell.dispose();	//打印完成销毁窗口
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 截取指定区域的内容保存为图片 
	 * @param fileName
	 * @param rectangle
	 * @throws Exception
	 */
	  public static void captureScreen(String fileName, Rectangle rectangle)
	   throws Exception {
	  File directiory=new File(GlobalParam.PRINT_IMAGEPATH);
    	 if(!directiory.exists()){
    		 directiory.mkdirs();
	 }
	  // 获取窗体的位置
	  java.awt.Rectangle rct = new java.awt.Rectangle((rectangle.x + 4),
	    (rectangle.y + 54), rectangle.width, rectangle.height);
	  Robot robot = new Robot();
	  BufferedImage image = robot.createScreenCapture(rct);
	  ImageIO.write(image, "png", new File(fileName));

	 }
	  
	  /**
	     * 生成报关单号对应的条形码
	 * @return 
	     * 
	     * @throws Exception
	     */
	    public static Image barcode(String data) throws Exception{
	        /*Linear barcode = new Linear();
	        barcode.setAddCheckSum(true);
	        barcode.setType(Linear.CODE128);
	        barcode.setData(data);
	        barcode.setN(1);
	        barcode.setUOM(Linear.UOM_PIXEL);
	        barcode.setX(1f);
	        barcode.setY(50f);
	        barcode.setLeftMargin(0f);
	        barcode.setRightMargin(0f);
	        barcode.setTopMargin(0f);
	        barcode.setBottomMargin(0f);
	        //barcode.setResolution(72);
	        barcode.setBarcodeHeight(10f);
	        barcode.setBarcodeWidth(10f);
	        barcode.setShowText(false);
	        //barcode.setTextFont(new Font("Microsoft YaHei UI", 50, SWT.NORMAL));
	        barcode.setRotate(Linear.ANGLE_0);*/
	    	
	        ImageData imagedata = SWTUtils.convertToSWT(MatrixUtil.toBufferedImage(MatrixUtil.toBarCodeMatrix(data, 230, null)));
	        return new Image(null, imagedata);
	    }
}
