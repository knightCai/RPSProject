package com.client.common;

import java.util.HashMap;
import java.util.Map;

import com.service.service.Userinfo;

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
	public static String PRINT_CONSIGNERADDR = "";//"dfasdfsdfSDFA ";	//寄件人地址
	public static String PRINT_CONSIGNERPHONE = "";//"100860100";	//寄件人电话
	public static String PRINT_CONSIGNEENAME = "";//"test";	//收件人名称
	public static String PRINT_CONSIGNEEADDR = "";//"阿斯|顺丰|蒂芬";	//收件人地址
	public static String PRINT_CONSIGNEEPHONE = "";//"13411373219,1234545122";	//收件人电话
	public static String PRINT_CARGONAME_CARGOTYPE = "";//"品名+品牌+数量+规格型号\r\n品名+品牌+数量+规格型号\r\n品名+品牌+数量+规格型号\r\n品名+品牌+数量+规格型号\r\n品名+品牌+数量+规格型号\r\n";	//货物内容描述：品名+品牌+数量+规格型号
	public static String PRINT_WEIGHT = "0";			//实际重量
	public static String PRINT_EXPRESSNUM = "";//"13213213213";		//运单号码
	public static String PRINT_DECLARENUM = "";//"9758887571199";		//报关单号
	public static String PRINT_CONSIGNERCOUNTRY = "";//"501";//发件人国家
	public static String PRINT_PASSPORT = "西安";		//过关口岸默认"西安"
	public static String PRINT_IMAGEPATH = System.getProperty("user.dir")+"/rps_images/";		//生成的图片保存路径，也是打印图片路径
	public static String PRINT_IMPORTSER = "0";		//导入批次
	public static String PRINT_BIGPEN = "";//"05415-123-2312";		//大头笔信息
	
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
	 * =================================国别对应发货人代码表========================================
	 */
	public static Map<String, String> NERNAME_OF_COUNTRYS = new HashMap<String, String>();
	static{
		NERNAME_OF_COUNTRYS.put("501", "Quick logistis");
		NERNAME_OF_COUNTRYS.put("502", "soonda");
		NERNAME_OF_COUNTRYS.put("601", "Longmen express");
	}
	
	/**
	 *  =================================资源路径========================================
	 */
	//模板
	public static String SOURCE_IMPORTTEMPLE = "/source/importTemple.xls";		//导入模板文件的存放路径
	public static String SOURCE_IMPORTTEMPLEYTO = "/source/importTempleYTO.xls";		//导入模板文件的存放路径YTO模板
	public static String SOURCE_UPDATETEMPLE = "/source/updateLogisTemple.xls";		//修改模板文件的存放路径
	//音效
	public static String SOURCE_PRINTMUSIC = "/source/music/b_print.wav";		//扫码正确音效
	public static String SOURCE_TWOPRINTMUSIC = "/source/music/twoprint.wav";	//重复扫描提示音效
	public static String SOURCE_ERRORMUSIC = "/source/music/p_error.wav";		//系统错误音效
	//图标
	public static String SOURCE_LOGONAME = "/images/system/logo.ico";		//系统logo图标
	public static String SOURCE_YTOLOGONAME = "/images/print/jglogo.png";		//圆通logo图标
	//背景
	public static String SOURCE_BGLOGIN = "/images/system/bg-login.jpg";		//登录界面背景
	public static String SOURCE_CS1 = "/images/system/cs3.jpg";		//登录界面背景
	/**
	 *  =================================系统信息========================================
	 */
	public static String SYSTEM_LOGINUSER = "admin";	//当前登录用户名
	public static Userinfo SYSTEM_USER;	//当前登录用户
	public static String SYSTEM_ADMINUSER = "admin";	//系统管理员
	public final static String SYSTEM_VERRSION = "1.7.8";	//系统当前版本
	public static final String SYSTEM_SYSNAME = "报关打印系统";	//系统名称
	public static final String DES_PASSWORD = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";		//DES密钥
	
	//货物内容显示行数
	public static int CAGLEN_DEFUALT = 3;	//默认
	public static int CAGLEN_YTO = 5;	//圆通
	
	//后端访问地址
	public static String SYSTEM_SERVICEURL = "http://127.0.0.1:8080";	//http://47.91.140.252:7001 生产 http://127.0.0.1:8080 测试
}
