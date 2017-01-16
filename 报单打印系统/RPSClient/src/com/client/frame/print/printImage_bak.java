package com.client.frame.print;

import java.awt.Font;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.poi.ss.formula.functions.Roman;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.barcodelib.barcode.Linear;
import com.client.common.FrameUtil;
import com.client.common.GlobalParam;
import com.client.common.ImagePrinterExample;
import com.client.common.SWTUtils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.ImageAndMessageArea;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Table;

public class printImage_bak {

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
	
	
	public printImage_bak(int type) {
		printType = type;
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			printImage_bak window = new printImage_bak(0);
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
		shell.setSize(581, 825);
		FrameUtil.center(shell);
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(29, 23, 98, 30);
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
		//判断打印模式
		if(printType == 1){
			doprint();
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
		InputStream in = this.getClass().getResourceAsStream("/images/print/printmodel_new1.png");
		printimg = new  Image(shell.getDisplay(), in);
		composite.setBackgroundImage(printimg);
		composite.setBounds(10, 59, 538, 713);
		
		text_consignername = new Text(composite, SWT.WRAP);
		text_consignername.setBounds(87, 124, 178, 26);

		text_consigneraddr = new Text(composite, SWT.WRAP);
		text_consigneraddr.setBounds(23, 200, 481, 50);
		
		text_consignerphone = new Text(composite, SWT.NONE);
		text_consignerphone.setBounds(366, 168, 138, 26);
		
		text_consigneename = new Text(composite, SWT.NONE);
		text_consigneename.setBounds(116, 262, 178, 26);
		
		text_consigneeaddr = new Text(composite, SWT.WRAP);
		text_consigneeaddr.setBounds(23, 318, 493, 39);
		
		text_consigneephone = new Text(composite, SWT.NONE);
		text_consigneephone.setBounds(366, 262, 138, 26);
		
		text_cargoname_cargotype = new Text(composite, SWT.WRAP | SWT.MULTI);
		text_cargoname_cargotype.setBounds(23, 391, 333, 93);
		text_weight = new Text(composite, SWT.NONE);
		text_weight.setBounds(386, 391, 130, 26);
		
		text_expressnum = new Text(composite, SWT.NONE);
		text_expressnum.setBounds(159, 627, 215, 26);
		
		text_passport = new Text(composite, SWT.NONE);
		text_passport.setBounds(368, 124, 138, 26);
		
		canvas_declarenum1 = new Canvas(composite, SWT.NONE);
		canvas_declarenum1.setBounds(288, 27, 187, 50);
		
		canvas_declarenum2 = new Canvas(composite, SWT.NONE);
		canvas_declarenum2.setBounds(94, 539, 179, 50);
		
		text_consignercountry = new Text(composite, SWT.NONE);
		text_consignercountry.setBounds(374, 459, 142, 33);
		
		text = new Text(composite, SWT.CENTER);
		text.setBounds(306, 83, 178, 26);
		
		text_1 = new Text(composite, SWT.CENTER);
		text_1.setBounds(104, 595, 178, 26);
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
		canvas_declarenum1.setBounds(296, 27,rct.width,rct.height);
		canvas_declarenum2.setBounds(94, 539,rct.width,rct.height);
		canvas_declarenum1.setBackgroundImage(printimg);
		canvas_declarenum2.setBackgroundImage(printimg);
		text.setText(GlobalParam.PRINT_DECLARENUM);
		text_1.setText(GlobalParam.PRINT_DECLARENUM);
		text_consignercountry.setText(GlobalParam.COUNTRYIDS.get(GlobalParam.PRINT_CONSIGNERCOUNTRY));
	}
	
	public void doprint(){
		try {
			//生成打印的图片并保存在指定目录
			FrameUtil.captureCompositeToImage(shell, composite,0,0,1126,1110,GlobalParam.PRINT_IMAGEPATH + GlobalParam.PRINT_IMPORTSER +"/"+GlobalParam.PRINT_DECLARENUM + ".jpg");
			//调用打印机进行打印
			ImagePrinterExample.ImagePrinter(shell);
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
	        Linear barcode = new Linear();
	        barcode.setType(Linear.CODABAR);
	        barcode.setData(data);
	        barcode.setN(3);
	        barcode.setUOM(Linear.UOM_PIXEL);
	        barcode.setX(1f);
	        barcode.setY(50f);
	        barcode.setLeftMargin(0f);
	        barcode.setRightMargin(0f);
	        barcode.setTopMargin(0f);
	        barcode.setBottomMargin(0f);
	        barcode.setResolution(72);
	        barcode.setShowText(false);
	        //barcode.setTextFont(new Font("Microsoft YaHei UI", 50, SWT.NORMAL));
	        barcode.setRotate(Linear.ANGLE_0);
	        ImageData imagedata = SWTUtils.convertToSWT(barcode.renderBarcode());
	        return new Image(null, imagedata);
	    }
}
