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
