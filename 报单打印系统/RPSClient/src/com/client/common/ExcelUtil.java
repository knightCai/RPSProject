package com.client.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.service.service.Logisticslisting;

/**
 * Excel 工具类
 * @author knight
 *
 */
public class ExcelUtil {
	 /** 总行数 */
    private int totalRows = 0;
    
    /** 总列数 */
    private int totalCells = 0;
    
    /** 构造方法 */
    public ExcelUtil() {
	}
    /**
	 * 方法描述：根据文件名读取excel文件
	 * 
	* @param fileName
     * @return
	 * @author 蔡鑫
     * @throws FileNotFoundException 
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     * @throws IOException
     */
    public List<ArrayList<String>> read(String fileName) throws FileNotFoundException, Exception
    {
        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
        
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$"))
        {
            return dataLst;
        }
        
        boolean isExcel2003 = true;
        /** 对文件的合法性进行验证 */
        if (fileName.matches("^.+\\.(?i)(xlsx)$"))
        {
            isExcel2003 = false;
        }
        
        /** 检查文件是否存在 */
        File file = new File(fileName);
        if (file == null || !file.exists())
        {
            return dataLst;
        }
        
        /** 调用本类提供的根据流读取的方法 */
        dataLst = read(new FileInputStream(file), isExcel2003);
        
        /** 返回最后读取的结果 */
        return dataLst;
    }
    
    /**
	 * 方法描述：根据流读取Excel文件
	 * 
	 * @param inputStream
     * @param isExcel2003
     * @return
	 * @author 蔡鑫
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     */
    public List<ArrayList<String>> read(InputStream inputStream,
            boolean isExcel2003) throws Exception
    {
        List<ArrayList<String>> dataLst = null;
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream)
                    : new XSSFWorkbook(inputStream);
            dataLst = read(wb);
        return dataLst;
    }
    
    /**
	 * 方法描述：得到总行数
	 * 
	 * @param  
	 * @return  
	 * @author 蔡鑫
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     * @throws IOException
     */
    public int getTotalRows()
    {
        return totalRows;
    }
    
    /**
	 * 方法描述：得到总列数
	 * 
	 * @param  
	 * @return  
	 * @author 蔡鑫
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     * @throws IOException
     */
    public int getTotalCells()
    {
        return totalCells;
    }
    
    /**
	 * 方法描述：读取数据
	 * 
	 * @param wb 
	 * @return List
	 * @author 蔡鑫
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     * @throws IOException
     */
    private List<ArrayList<String>> read(Workbook wb) throws Exception
    {
    	List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
    	try{
	        /** 得到第一个shell */
	        Sheet sheet = wb.getSheetAt(0);
	        this.totalRows = sheet.getPhysicalNumberOfRows();
	        if (this.totalRows >= 1 && sheet.getRow(0) != null)
	        {
	            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
	        }
	        
	        /** 循环Excel的行 
	         * 过滤第一行列名
	         * */
	        for (int r = 1; r <= this.totalRows; r++)
	        {
	            Row row = sheet.getRow(r);
	            if (row == null)
	            {
	                continue;
	            }
	            
	            ArrayList<String> rowLst = new ArrayList<String>();
	            /** 循环Excel的列 */
	            for (short c = 0; c < this.getTotalCells(); c++)
	            {
	                Cell cell = row.getCell(c);
	                String cellValue = "";
	                if (cell == null)
	                {
	                    rowLst.add(cellValue);
	                    continue;
	                }
	                
	                /** 处理数字型的,自动去零 */
	                if (Cell.CELL_TYPE_NUMERIC == cell.getCellType())
	                {
	                    /** 在excel里,日期也是数字,在此要进行判断 */
	                    if (HSSFDateUtil.isCellDateFormatted(cell))
	                    {
	                        cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
	                    }
	                    else
	                    {
	                        cellValue = getRightStr(cell.getNumericCellValue() + "");
	                    }
	                }
	                /** 处理字符串型 */
	                else if (Cell.CELL_TYPE_STRING == cell.getCellType())
	                {
	                    cellValue = cell.getStringCellValue();
	                }
	                /** 处理布尔型 */
	                else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType())
	                {
	                    cellValue = cell.getBooleanCellValue() + "";
	                }
	                /** 其它的,非以上几种数据类型 */
	                else
	                {
	                	cellValue = cell.toString() + "";
	                }
	                rowLst.add(cellValue);
	            }
	            dataLst.add(rowLst);
	        }
	        this.totalRows--;//数据总数除去列名行
    	}catch (Exception e) {
    		throw new Exception("导入清单格式必须为文本格式，不能包含其他格式内容,详细错误:"+e.getMessage());
		}
        return dataLst;
    }
    
    /**
	 * 方法描述：正确地处理整数后自动加零的情况
	 * 
	 * @param sNum 
	 * @author 蔡鑫
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     * @throws IOException
     */
    private String getRightStr(String sNum)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String resultStr = decimalFormat.format(new Double(sNum));
        if (resultStr.matches("^[-+]?\\d+\\.[0]+$"))
        {
            resultStr = resultStr.substring(0, resultStr.indexOf("."));
        }
        if(new Double(sNum) < 1){
        	resultStr = "0" + resultStr;
        }
        return resultStr;
    }
  
    /**
	 * 方法描述：生成excel文件
	 * 
	 * @param list	代扣文件对象
	 * @param filePath 文件导出路径 
	 * @author 蔡鑫
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
	 * @throws IOException
	 */
    @SuppressWarnings("deprecation")
	public static boolean createExcel(List<Logisticslisting> list,String filePath) throws Exception  
    {  
    	boolean flag = true;
    	Logisticslisting llist = null;
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("报关清单");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFCellStyle styleLeft = wb.createCellStyle();  
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个左对齐格式
        HSSFCellStyle styleRight = wb.createCellStyle();  
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 创建一个右对齐格式

        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("序号");  									
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("分运单号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("报关单号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("货品名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);  
        cell.setCellValue("品牌");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 5);  
        cell.setCellValue("申报类型");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 6);
        cell.setCellValue("成交方式");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("件数");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("申报重量");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("申报价值");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 10);
        cell.setCellValue("币制");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 11);
        cell.setCellValue("收货人名称(货主单位名称)");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 12);
        cell.setCellValue("地址");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 13);
        cell.setCellValue("电话");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 14);
        cell.setCellValue("收发件人证件类型");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 15);
        cell.setCellValue("收发件人证件号ַ");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 16);
        cell.setCellValue("发件人名称");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 17);
        cell.setCellValue("发件人城市");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 18);
        cell.setCellValue("地址");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 19);
        cell.setCellValue("电话");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 20);
        cell.setCellValue("生产国别");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 21);
        cell.setCellValue("收件人国家");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 22);
        cell.setCellValue("发件人国家");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 23);
        cell.setCellValue("商品编号");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 24);
        cell.setCellValue("规格型号");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 25);
        cell.setCellValue("数量");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 26);
        cell.setCellValue("重量");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 27);
        cell.setCellValue("单位");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 28);
        cell.setCellValue("贸易方式");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 29);
        cell.setCellValue("包装种类");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 30);
        cell.setCellValue("经营单位编码");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 31);
        cell.setCellValue("经营单位名称");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 32);
        cell.setCellValue("货主单位地区代码");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 33);
        cell.setCellValue("货主单位代码");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 34);
        cell.setCellValue("合同号");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 35);
        cell.setCellValue("运费标记");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 36);
        cell.setCellValue("运费币制");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 37);
        cell.setCellValue("运费/率");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 38);
        cell.setCellValue("保险费标记");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 39);
        cell.setCellValue("保险费币制");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 41);
        cell.setCellValue("保险费/率");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 41);
        cell.setCellValue("杂费标记");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 42);
        cell.setCellValue("杂费币制");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 43);
        cell.setCellValue("杂费/率");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 44);
        cell.setCellValue("净重");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 45);
        cell.setCellValue("关联编号字段");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 46);
        cell.setCellValue("码头/货场代码");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 47);
        cell.setCellValue("用途");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 48);
        cell.setCellValue("第一(法定)数量");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 49);
        cell.setCellValue("第二数量");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 50);
        cell.setCellValue("第一(法定)计量单位");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 51);
        cell.setCellValue("第二计量单位");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 52);
        cell.setCellValue("随附单证类型");
        cell.setCellStyle(styleLeft);
        cell = row.createCell((short) 53);
        cell.setCellValue("随附单证号码");
        cell.setCellStyle(styleLeft);
        
        // 第五步，写入实体数据   
        for (int i = 0; i < list.size(); i++)  
        {  
        	llist = list.get(i);
            row = sheet.createRow(i+1);  
         // 第四步，创建单元格，并设置值 
            cell = row.createCell((short) 0);
            cell.setCellValue(llist.getSerialnum());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 1);
            cell.setCellValue(llist.getExpressnum()); 
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 2);
            cell.setCellValue(llist.getDeclarenum()); 
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 3);
            cell.setCellValue(llist.getCargoname()+"/"+llist.getBrand());  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 4);
            cell.setCellValue(llist.getBrand());  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 5);
            cell.setCellValue("B");  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 6);
            cell.setCellValue("1");  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 7);
            cell.setCellValue("1");  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 8);
            cell.setCellValue(llist.getDeclareweight());  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 9);
            cell.setCellValue(llist.getDeclarepricesum());  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 10);
            cell.setCellValue("142");  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 11);
            cell.setCellValue(llist.getConsigneename());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 12);
            String[] addSplit = llist.getConsigneeaddr().split("\\|");
            cell.setCellValue(addSplit.length>1?addSplit[2]:llist.getConsigneeaddr());  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 13);
            cell.setCellValue(llist.getConsigneephone());  
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 14);
            cell.setCellValue("1"); 
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 15);
            cell.setCellValue(llist.getConsignercardid());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 16);
            String text_consignername = GlobalParam.NERNAME_OF_COUNTRYS.get(llist.getConsignercountry());
            text_consignername = text_consignername==null?llist.getConsignername():text_consignername;
            cell.setCellValue(text_consignername);
            //cell.setCellValue(llist.getConsignername());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 17);
            cell.setCellValue(llist.getConsignercountry());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 18);
            cell.setCellValue(llist.getConsigneraddr());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 19);
            cell.setCellValue(llist.getConsignerphone());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 20);
            cell.setCellValue(llist.getConsignercountry());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 21);
            cell.setCellValue("142");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 22);
            cell.setCellValue(llist.getConsignercountry());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 23);
            cell.setCellValue(llist.getCargoid());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 24);
            cell.setCellValue(llist.getBrand()+"/"+llist.getCargotype());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 25);
            cell.setCellValue(llist.getPackagecount());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 26);
            cell.setCellValue(llist.getNetweight());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 27);
            cell.setCellValue(llist.getLegalunit());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 28);
            cell.setCellValue("3039");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 29);
            cell.setCellValue("2");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 30);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 31);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 32);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 33);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 34);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 35);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 36);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 37);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 38);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 39);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 40);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 41);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 42);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 43);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 44);
            cell.setCellValue(llist.getNetweight());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 45);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 46);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 47);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 48);
            cell.setCellValue(llist.getLegalnum());
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 49);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 50);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 51);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 52);
            cell.setCellValue("");
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 53);
            cell.setCellValue("");
        }  
     // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(filePath +".xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();
            return false;
        }  
        return flag;
    }  
    /**
	 * 方法描述：测试方法
	 * 
	 * @param args 
	 * @author 蔡鑫
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     * @throws IOException
     */
    public static void main(String[] args) throws Exception
    {
    	String  str= "云南省^保山市^隆阳区永昌街道隆阳龙泉路延长线起亚4S店".replace("^","");
    	System.out.println(str);
    }
}
