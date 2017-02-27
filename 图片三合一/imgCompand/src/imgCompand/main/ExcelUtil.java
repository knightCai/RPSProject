package imgCompand.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * @throws Exception 
	 * @time:2016-10-27 上午10:07:28
     * @throws IOException
     */
    public List<ArrayList<String>> read(String fileName)
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
        
        try
        {
            /** 调用本类提供的根据流读取的方法 */
            dataLst = read(new FileInputStream(file), isExcel2003);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
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
     * @throws IOException
     */
    public List<ArrayList<String>> read(InputStream inputStream,
            boolean isExcel2003) throws IOException
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
    private List<ArrayList<String>> read(Workbook wb)
    {
        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
        
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
        for (int r = 1; r < this.totalRows; r++)
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
	public static boolean createExcel(Map<String,String> errormap,String filePath) throws Exception  
    {  
    	boolean flag = true;
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("三合一异常信息");  
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
        cell.setCellValue("报关单号");  									
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("异常信息");  
        cell.setCellStyle(style);  
        
        int i = 1;
        // 第五步，写入实体数据   
        for (String key:errormap.keySet())  
        {  
            row = sheet.createRow(i);  
         // 第四步，创建单元格，并设置值 
            cell = row.createCell((short) 0);
            cell.setCellValue(key);
            cell.setCellStyle(styleLeft);
            cell = row.createCell((short) 1);
            cell.setCellValue(errormap.get(key)); 
            cell.setCellStyle(styleLeft);
            i++;
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
