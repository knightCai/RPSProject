package com.client.frame.data.declare;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.client.model.contrllo.OddnumberContrllo;
import com.service.service.impl.Oddnumber;
import org.eclipse.swt.layout.GridLayout;

public class DeclareMgrFrame extends Composite {
	private Table table;
	private Composite composite = null; 
	private Shell sShell = null;
	private AddDeclareNumFrame addDecwindow;
	private OddnumberContrllo oddnumCon;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DeclareMgrFrame(Composite parent, int style) {
		super(parent, style);
		oddnumCon = new OddnumberContrllo();
		composite = this;
		//composite.setBackgroundImage(new  Image(composite.getDisplay(), "images/system/bg-main.jpg"));
		createView();
		createTable();
	}
	
	private void createView(){
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.marginRight = 5;
		gridLayout.marginLeft = 5;
		gridLayout.marginHeight = 10;
		gridLayout.horizontalSpacing = 10;
		setLayout(gridLayout);
		Label label = new Label(this, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.widthHint = 88;
		label.setLayoutData(gd_label);
		label.setText("使用状态：");
		Combo cboUseState = new Combo(this, SWT.NONE);
		GridData gd_cboUseState = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cboUseState.widthHint = 101;
		cboUseState.setLayoutData(gd_cboUseState);
		cboUseState.add("可使用");
		cboUseState.add("已用完");
		cboUseState.add("禁用");
		
		Button butQuery = new Button(this, SWT.NONE);
		GridData gd_butQuery = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_butQuery.widthHint = 117;
		butQuery.setLayoutData(gd_butQuery);
		butQuery.setText("查询");
		new Label(this, SWT.NONE);
		
		Button butAdd = new Button(this, SWT.NONE);
		GridData gd_butAdd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_butAdd.widthHint = 109;
		butAdd.setLayoutData(gd_butAdd);
		butAdd.setText("添加");
		
		Button butDel = new Button(this, SWT.NONE);
		butDel.setEnabled(false);
		GridData gd_butDel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_butDel.widthHint = 119;
		butDel.setLayoutData(gd_butDel);
		butDel.setText("删除");
		
		Button butOpen = new Button(this, SWT.NONE);
		butOpen.setEnabled(false);
		GridData gd_butOpen = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_butOpen.widthHint = 118;
		butOpen.setLayoutData(gd_butOpen);
		butOpen.setText("激活");
		
		Button butClose = new Button(this, SWT.NONE);
		butClose.setEnabled(false);
		GridData gd_butClose = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_butClose.widthHint = 112;
		butClose.setLayoutData(gd_butClose);
		butClose.setText("禁用");
		
		//绑定点击事件
		butQuery.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				table.removeAll();
				loadTable(table);
			}
		});
		butAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(addDecwindow == null){
					addDecwindow = new AddDeclareNumFrame();
				}
				addDecwindow.open();
			}
		});
		butDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem[] tabitems = table.getSelection();
				if(tabitems.length < 1){
					MessageDialog.openInformation(getShell(),"系统提示","请选择要操作的数据！");
				}else{
					Oddnumber oddnum;
					for(int i = 0;i < tabitems.length; i++){
						oddnum = new Oddnumber();
						oddnum.setPkid(tabitems[i].getText(7));
						oddnumCon.deleteOddNum(oddnum);
					}
					MessageDialog.openInformation(getShell(),"系统提示","删除成功！");
				}
			}
		});
		butOpen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
		butClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		});
	}
	
	
	// 创建表格  
    private void createTable()  
    {  
    	// 表格布局  
        GridData gridData = new org.eclipse.swt.layout.GridData();  
        gridData.widthHint = 626;
        gridData.horizontalSpan = 4;
        gridData.horizontalAlignment = SWT.FILL;  
        gridData.grabExcessHorizontalSpace = true;  
        gridData.grabExcessVerticalSpace = true;  
        gridData.verticalAlignment = SWT.FILL;  
    	// 创建表格，使用SWT.FULL_SELECTION样式，可同时选中一行  
        table = new Table(composite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);  
        GridData gd_table = new GridData(SWT.LEFT, SWT.CENTER, false, false, 4, 1);
        gd_table.widthHint = 500;
        table.setLayoutData(gd_table);
        table.setHeaderVisible(true);// 设置显示表头  
        table.setLayoutData(gridData);// 设置表格布局  
        table.setLinesVisible(true);
        
        // 创建表头的字符串数组  
        String[] tableHeader = {"           起始号码               ", "              结束号码                ",
        		"        剩余数量                ", "        发放号码                ",/*"使用状态",*/"        剩余提醒                ",
        		"        录入日期                ","主键"};
        for (int i = 0; i < tableHeader.length; i++)  
        {  
        	TableColumn tableColumn = new TableColumn(table, SWT.NONE);  
            tableColumn.setText(tableHeader[i]); 
            // 设置表头可移动，默认为false  
            tableColumn.setMoveable(true);  
            tableColumn.setWidth(500);
        }  
        // 添加数据  
        // 设置图标  
        //loadTable(table);
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
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	/**
	 * 查询后台数据，加载table
	 * @param table
	 */
	private void loadTable(Table table){
		List<Oddnumber> oddnumlist = oddnumCon.findAllOddnum();
		TableItem item;
		Oddnumber oddnum;
		for(int i = 0;i < oddnumlist.size(); i++){
			oddnum = oddnumlist.get(i);
			item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{oddnum.getStartnum(), oddnum.getEndnum(),oddnum.getSuplusnum() + "",oddnum.getUsenum(),/*oddnum.getState().equals("0")?"未使用":oddnum.getState().equals("1")?"正在使用":"禁用",*/
            		oddnum.getWarnnum()+"",oddnum.getCreatetime().toString().substring(0,10),oddnum.getPkid()}); 
		}
	} 
}
