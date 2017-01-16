package com.client.frame.print;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.client.common.ExcelUtil;
import com.client.common.FileUtil;
import com.client.common.GlobalParam;
import com.client.frame.print.oddnumer.OddnumberDialog;
import com.client.model.contrllo.LogisticsListContrllo;
import com.service.service.impl.Logisticslisting;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.SWTResourceManager;

public class printComp extends Composite {
	private Text text;
	private Text text_1;
	private OddnumberDialog oddnumDialog = null;
	private Table table;
	private Composite composite = null; 
	private Spinner spinner;	//导入批次
	private DateTime dateCreate;	//导入时间
	private Combo comisprint;	//打印状态
	private List condition;	//查询参数
	private String expressnum;//运单号
	private LogisticsListContrllo logisContrllo; //运单控制层
	private Button btn_isshama;	//是否扫码枪模式

	public printComp(Composite parent, int style) {  
        super(parent, style);
        composite = this;
        /*InputStream in = this.getClass().getResourceAsStream("/images/system/bg-main.jpg");
        composite.setBackgroundImage(new  Image(this.getShell().getDisplay(), in));*/
        GridLayout gridLayout = new GridLayout(13, false);
        gridLayout.verticalSpacing = 10;
        gridLayout.horizontalSpacing = 10;
        gridLayout.marginBottom = 10;
        gridLayout.marginTop = 10;
        gridLayout.marginRight = 5;
        gridLayout.marginLeft = 5;
        setLayout(gridLayout);
        
        Label label = new Label(this, SWT.NONE);
        label.setText("运单打印：");
        
        text = new Text(this, SWT.BORDER);
        text.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
        GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_text.heightHint = 35;
        gd_text.widthHint = 189;
        //text.setFont(getFont);
        text.setLayoutData(gd_text);
        
        btn_isshama = new Button(this, SWT.CHECK);
        btn_isshama.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        btn_isshama.setText("扫码枪");
        btn_isshama.setSelection(true);
        
        Button button = new Button(this, SWT.NONE);
        GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
        gd_button.heightHint = 37;
        gd_button.widthHint = 153;
        button.setLayoutData(gd_button);
        button.setText("打印面单");
        //打印运单按钮：添加单击事件
        button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				expressnum = text.getText();//获取运单号
				//如果有运单查询框有值，则用填写的内容查询运单信息进行打印，否则判断并使用选择的列进行打印
				if(expressnum.equals("")){
					TableItem[] items = table.getSelection();
					if(items.length < 1){
						MessageDialog.openConfirm(getShell(), "系统提示","请选择需要打印的内容");
					}else{
						expressnum = items[0].getText(3);
						printLogistics(0);//预览打印
					}
				}else{
					printLogistics(0);
				}
			}
		});
        
        Button button_1 = new Button(this, SWT.NONE);
        button_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
        button_1.setText("报关清单导入");
        
        Button button_2 = new Button(this, SWT.NONE);
        button_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        button_2.setText("报关清单导出");
        
        Button btn_exportpic = new Button(this, SWT.NONE);
        btn_exportpic.setText("面单图片导出");
        new Label(this, SWT.NONE);
        new Label(this, SWT.NONE);
        
        Label label_1 = new Label(this, SWT.NONE);
        label_1.setText("导入批次：");
        
        String max = new LogisticsListContrllo().findMaxImportnum("admin");
		max = max==null?"1":max;
        spinner = new Spinner(this, SWT.BORDER);
        GridData gd_spinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_spinner.widthHint = 122;
        spinner.setSelection(Integer.parseInt(max));
        spinner.setLayoutData(gd_spinner);

        Label label_2 = new Label(this, SWT.NONE);
        label_2.setAlignment(SWT.RIGHT);
        GridData gd_label_2 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1);
        gd_label_2.widthHint = 113;
        label_2.setLayoutData(gd_label_2);
        label_2.setText("打印状态：");
        
        comisprint = new Combo(this, SWT.NONE);
        GridData gd_comisprint = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_comisprint.widthHint = 93;
        comisprint.setLayoutData(gd_comisprint);
        comisprint.add("未打印");
        comisprint.add("已打印");
        
        Label label_3 = new Label(this, SWT.NONE);
        label_3.setAlignment(SWT.RIGHT);
        GridData gd_label_3 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
        gd_label_3.widthHint = 115;
        label_3.setLayoutData(gd_label_3);
        label_3.setText("导入时间：");
        
        dateCreate = new DateTime(this, SWT.BORDER);
        GridData gd_dateCreate = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
        gd_dateCreate.widthHint = 144;
        dateCreate.setLayoutData(gd_dateCreate);
        
        Label label_4 = new Label(this, SWT.NONE);
        label_4.setEnabled(false);
        label_4.setText("航班号：");
        new Label(this, SWT.NONE);
        
        text_1 = new Text(this, SWT.BORDER);
        text_1.setEnabled(false);
        text_1.setEditable(false);
        GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_text_1.widthHint = 87;
        text_1.setLayoutData(gd_text_1);
        
        Button btnNewButton = new Button(this, SWT.NONE);
        GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
        gd_btnNewButton.widthHint = 67;
        btnNewButton.setLayoutData(gd_btnNewButton);
        btnNewButton.setText("查询");
        //运单号文本框：添加键盘输入事件，当输入回车字符时，直接查询并打印面单
        text.addTraverseListener(new TraverseListener() {
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.keyCode == 13) {
					expressnum = text.getText();//获取运单号
					//运单号不为空才进行处理
					if(!expressnum.equals("")){
						if(btn_isshama.getSelection()){
							printLogistics(1);//直接打印
						}else{
							printLogistics(0);//直接打印
						}
					}
					text.setText("");
					text.setFocus();
				}
			}
		});
        button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(oddnumDialog == null){
				}
				oddnumDialog =  new OddnumberDialog(getShell(),SWT.NULL);
				oddnumDialog.open();
			}
		});
        //物流清单导出
        button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String importnum = spinner.getText();
				String nowdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
				FileDialog filedialog = new FileDialog(getShell(),SWT.SAVE);
				filedialog.setFileName(nowdate+"物流清单导出-批次【"+importnum+"】");
				String filepath = filedialog.open();
				if(logisContrllo == null){
		    		logisContrllo = new LogisticsListContrllo();
		    	}
				condition  = new ArrayList();
				condition.add("importnum:"+spinner.getText());
				List<Logisticslisting> logislist = logisContrllo.findLlistByParams(condition);
				try {
					ExcelUtil.createExcel(logislist,filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				table.removeAll();
				loadTable(table);
			}
		});
        //面单导出按钮
        btn_exportpic.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {
        		String importnum = spinner.getText();
        		FileDialog file = new FileDialog(getShell(),SWT.SAVE);
        		String nowdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        		file.setFileName(nowdate+"面单图片导出-批次【"+importnum+"】.zip");
        		String zipFileName = file.open();
        		if(zipFileName != null){
	        		String filepath = GlobalParam.PRINT_IMAGEPATH+importnum+"/";
						FileUtil.downloadPics(zipFileName,filepath);
						MessageDialog.openInformation(getShell(), "系统提示", "文件导出成功！");
				}
        		} catch (Exception e1) {
        			e1.printStackTrace();
        			MessageDialog.openInformation(getShell(), "系统提示", "文件导出失败！错误信息："+e1.getMessage());
        		}
        	}
		});
        createTable();
        text.setFocus();
    }  
	
	
	// 创建表格  
    private void createTable()  
    {  
    	// 表格布局  
        GridData gridData = new org.eclipse.swt.layout.GridData();  
        gridData.horizontalSpan = 13;
        gridData.horizontalAlignment = SWT.FILL;  
        gridData.grabExcessHorizontalSpace = true;  
        gridData.grabExcessVerticalSpace = true;  
        gridData.verticalAlignment = SWT.FILL;  
    	// 创建表格，使用SWT.FULL_SELECTION样式，可同时选中一行  
        table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);  
        table.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 11, 1));
        table.setHeaderVisible(true);// 设置显示表头  
        table.setLayoutData(gridData);// 设置表格布局  
        table.setLinesVisible(true);
        
        // 创建表头的字符串数组  
        String[] tableHeader = {"        导入序号        ", "        打印状态        ", "        报关单号        ", "        物流单号        ","        收件人姓名        ",
        		"        收件人地址        ","        收件人电话        ","        导入批次        ",
        		"        实际重量        ","        货品名称        ","        规格型号        "};  
        for (int i = 0; i < tableHeader.length; i++)  
        {  
            TableColumn tableColumn = new TableColumn(table, SWT.NONE);  
            tableColumn.setText(tableHeader[i]);  
            // 设置表头可移动，默认为false  
            tableColumn.setMoveable(true);  
        }  
        // 设置图标  
        // item.setImage( ImageFactory.loadImage(  
        // table.getDisplay(),ImageFactory.ICON_GIRL));  
  
        // 创建TableCursor对象，使用上下左右键可以控制表格  
        final TableCursor cursor = new TableCursor(table, SWT.NONE);  
        // 创建可编辑的控件  
        final ControlEditor editor = new ControlEditor(cursor);
        editor.grabHorizontal = true;  
        editor.grabVertical = true;  
        // 为TableCursor对象注册事件  
        cursor.addSelectionListener(new SelectionAdapter()  
        {  
            // 移动光标到一个单元格上所触发的事件  
            public void widgetSelected(SelectionEvent e)  
            {  
                table.setSelection(new TableItem[]{cursor.getRow()});  
            }  
        });  
        // ******************************************************/  
        // 重新布局表格  
        for (int i = 0; i < tableHeader.length; i++)  
        {  
            table.getColumn(i).pack();  
        }  
        // /****************************************************  
        // 为单元格注册选中事件  
        table.addSelectionListener(new SelectionAdapter()  
        {  
            public void widgetSelected(SelectionEvent e)  
            {  
                // 获得所有的行数  
                int total = table.getItemCount();  
                // 循环所有行  
                for (int i = 0; i < total; i++)  
                {  
                    TableItem item = table.getItem(i);  
                    // 如果该行为选中状态，改变背景色和前景色，否则颜色设置  
                    if (table.isSelected(i))  
                    {  
                        item.setBackground(getShell().getDisplay().getSystemColor(  
                                SWT.COLOR_BLUE));  
                        item.setForeground(getShell().getDisplay().getSystemColor(  
                                SWT.COLOR_RED));  
                    }  
                    else  
                    {  
                        item.setBackground(null);  
                        item.setForeground(null);  
                    }  
                }  
            }  
  
        });  
    }  
    
    /**
     * 组装查询条件list
     * webservice client端会把数组转换为list
     */
    private void putCondition(){
    	condition  = new ArrayList();
    	String importnum= spinner.getText();
    	int isprint = comisprint.getSelectionIndex();
    	String expressnum = text.getText();//获取运单号
    	if(isprint>=0){
    		condition.add("isprint:"+isprint);
    	}
    	if(!expressnum.equals("")){
    		condition.add("expressnum:"+expressnum);
    	}
    	condition.add("importnum:"+spinner.getText());
		condition.add("createtime:"+dateCreate.getYear()+"-"+(dateCreate.getMonth()+1)+"-"+dateCreate.getDay());	//SWT 日期组件需要年月日单独拼接
	}
    /**
	 * 查询后台数据，加载table
	 * @param table
	 */
    private void loadTable(Table table){
    	if(logisContrllo == null){
    		logisContrllo = new LogisticsListContrllo();
    	}
    	putCondition();
		List<Logisticslisting> logislist = logisContrllo.findLlistByParams(condition);
		TableItem item;
		Logisticslisting logis;
		for(int i = 0;i < logislist.size(); i++){
			logis = logislist.get(i);
			item = new TableItem(table, SWT.NONE);
			// {"导入序号", "打印状态", "报关单号", "物流单号","收件人姓名","收件人地址","收件人电话","导入批次","实际重量","货品名称","规格型号"};  
			String str = logis.getConsigneeaddr();
			item.setText(new String[]{
				logis.getSerialnum(),
				logis.getIsprint().equals("1")?"已打印":"未打印",
				logis.getDeclarenum(),
				logis.getExpressnum(),
				/*logis.getDeclaretype(),
				logis.getPaytype(),
				logis.getDeclarecount(),
				logis.getDeclareweight(),
				logis.getDeclarevalue (),
				logis.getCurrency(),*/
				logis.getConsigneename(),
				logis.getConsigneeaddr().replaceAll("^",""),
				logis.getConsigneephone(),
				/*logis.getConsignercardtype(),
				logis.getConsignercardid(),
				logis.getConsignername(),
				logis.getConsignercity (),
			   	logis.getConsigneraddr(),
			   	logis.getConsignerphone (),
			   	logis.getFromcountry(),
			   	logis.getConsigneecountry(),
			   	logis.getConsignercountry(),
			   	logis.getCargoid(),*/
				logis.getImportnum(),
				logis.getDeclareweight(),
			   	logis.getCargoname(),
			   	logis.getCargotype()
			   	/*logis.getCount(),
			   	logis.getUnit(),
			   	logis.getTrademode(),
			   	logis.getPacktype(),
			   	logis.getNetweight(),
			   	logis.getLegalnum()*/
			});
            //item.setText(new String[]{oddnum.getStartnum(), oddnum.getEndnum(),oddnum.getSuplusnum() + "",oddnum.getUsenum(),oddnum.getState().equals("0")?"未使用":oddnum.getState().equals("1")?"正在使用":"禁用",oddnum.getWarnnum()+"",oddnum.getCreatetime().toString().substring(0,10),oddnum.getPkid()}); 
		}
	} 
    
    
    /**
     * 打印物流面单
     * @param operType 1-直接打印 0-预览打印
     */
    private void printLogistics(int operType){
    	try {
    		if(logisContrllo == null){
        		logisContrllo = new LogisticsListContrllo();
        	}
	    	condition = new ArrayList();
			condition.add("expressnum:"+expressnum);
			condition.add("importnum:"+spinner.getText());
			//通过运单号查询运单信息
			List<Logisticslisting> logislist = logisContrllo.findLlistByParams(condition);
			//若无数据不进行操作
			//遍历查询结果，一个运单号可包含多个货品，面单打印时需要统计为一个面单进行打印
			//每次打印时需重置累加部分的值。
			if(logislist.size() > 0){
				GlobalParam.PRINT_WEIGHT = "0";
				GlobalParam.PRINT_CARGONAME_CARGOTYPE = "";
				String isprint="0";
				String lineChg = "\r\n";
				for(Logisticslisting logis : logislist){
					isprint = logis.getIsprint();
					GlobalParam.PRINT_CONSIGNERNAME = logis.getConsignername();
					GlobalParam.PRINT_CONSIGNERADDR = logis.getConsigneraddr();
					GlobalParam.PRINT_CONSIGNERPHONE = logis.getConsignerphone();
					GlobalParam.PRINT_CONSIGNEENAME = logis.getConsigneename();
					GlobalParam.PRINT_CONSIGNEEADDR = logis.getConsigneeaddr().replaceAll("|","".replaceAll("|",""));
					GlobalParam.PRINT_CONSIGNEEPHONE = logis.getConsigneephone();
					GlobalParam.PRINT_CARGONAME_CARGOTYPE += logis.getCargoname() + "*"+ logis.getCount()+ "  " + logis.getBrand() + "，"  + logis.getCargotype()+ lineChg;
					GlobalParam.PRINT_WEIGHT = (Float.parseFloat(GlobalParam.PRINT_WEIGHT) + Float.parseFloat(logis.getDeclareweight())) + "";
					GlobalParam.PRINT_EXPRESSNUM = logis.getExpressnum();
					GlobalParam.PRINT_DECLARENUM = logis.getDeclarenum();
					GlobalParam.PRINT_CONSIGNERCOUNTRY = logis.getConsignercountry();
					GlobalParam.PRINT_IMPORTSER = logis.getImportnum()+"";
				}
				if(isprint.equals("1")&&operType == 1){
					MessageDialog.openWarning(getShell(),"系统提示","当前运单已打印，扫码枪打印不支持重复打印！运单号："+GlobalParam.PRINT_EXPRESSNUM);
					return;
				}
				printImage pm = new printImage(operType);	
				pm.open();
				String[] msgcode = logisContrllo.updateLlistBySql(" isprint = '1' where expressnum = '"+GlobalParam.PRINT_EXPRESSNUM+"' and importnum = '"+spinner.getText()+"'" ).split(",");
				if(msgcode[0].equals("999")){
					MessageDialog.openWarning(getShell(), "系统提示", "修改打印状态失败："+msgcode[1]);
				}
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
