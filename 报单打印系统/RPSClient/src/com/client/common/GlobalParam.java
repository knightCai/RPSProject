package com.client.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局参数类
 * 客户端不存在线程安全问题
 * @author knight
 *
 */
public class  GlobalParam {
	/**
	 * =================================打印参数========================================
	 */
	public static String PRINT_CONSIGNERNAME = " ";	//寄件人名称
	public static String PRINT_CONSIGNERADDR = " ";	//寄件人地址
	public static String PRINT_CONSIGNERPHONE = " ";	//寄件人电话
	public static String PRINT_CONSIGNEENAME = " ";	//收件人名称
	public static String PRINT_CONSIGNEEADDR = "阿斯|顺丰|蒂芬";	//收件人地址
	public static String PRINT_CONSIGNEEPHONE = " ";	//收件人电话
	public static String PRINT_CARGONAME_CARGOTYPE = "品名+品牌+数量+规格型号";	//货物内容描述：品名+品牌+数量+规格型号
	public static String PRINT_WEIGHT = "0";			//实际重量
	public static String PRINT_EXPRESSNUM = "13213213213";		//运单号码
	public static String PRINT_DECLARENUM = "1000000000007";		//报关单号
	public static String PRINT_CONSIGNERCOUNTRY = "501";//发件人国家
	public static String PRINT_PASSPORT = "西安";		//过关口岸默认"西安"
	public static String PRINT_IMAGEPATH = System.getProperty("user.dir")+"/rps_images/";		//生成的图片保存路径，也是打印图片路径
	public static String PRINT_IMPORTSER = "0";		//导入批次
	
	/**
	 * =================================国别地区代码表========================================
	 */
	//public static HashMap<String, String> COUNTRYIDS = new HashMap<String, String>().putAll("":"");;
	public static Map<String, String> COUNTRYIDS = new HashMap<String, String>();
	static{
		COUNTRYIDS.put("501", "Canada");
		COUNTRYIDS.put("502", "United States");
		COUNTRYIDS.put("303", "United Kingdom");
		COUNTRYIDS.put("304", "Germany");
		COUNTRYIDS.put("305", "France");
		COUNTRYIDS.put("309", "Netherlands");
		COUNTRYIDS.put("601", "Australia");
		COUNTRYIDS.put("133", "Korea, Rep.");
		COUNTRYIDS.put("116", "Japan");
	}
	
	/**
	 *  =================================资源路径========================================
	 */
	public static String SOURCE_IMPORTTEMPLE = "/source/importTemple.xls";		//导入模板文件的存放路径
	public static String SOURCE_PRINTMUSIC = "/source/music/b_print.wav";		//扫码正确音效
	public static String SOURCE_TWOPRINTMUSIC = "/source/music/twoprint.wav";	//重复扫描提示音效
	public static String SOURCE_ERRORMUSIC = "/source/music/p_error.wav";		//系统错误音效
	
	/**
	 *  =================================系统信息========================================
	 */
	public static String SYSTEM_LOGINUSER = "admin";	//当前登录用户
	public final static String SYSTEM_VERRSION = "1.6.5";	//系统当前版本
}
