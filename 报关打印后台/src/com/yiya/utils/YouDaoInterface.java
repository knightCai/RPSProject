package com.yiya.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import net.sf.json.JSONObject;

public class YouDaoInterface {
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //配置您申请的KEY
    public static final String APPKEY ="476501792";
    public static final String APPKEYFROM ="RPSService";
    //1.有道翻译
    public static String getRequest1(String q){
        String result =null;
        String url ="http://fanyi.youdao.com/openapi.do";//请求接口地址
        Map params = new HashMap();//请求参数
        	params.put("keyfrom",APPKEYFROM);
            params.put("key",APPKEY);//申请的KEY
            params.put("q",q);//q - 要翻译的文本，必须是UTF-8编码，字符长度不能超过200个字符，需要进行urlencode编码
            params.put("only","translate");//dict表示只获取词典数据，translate表示只获取翻译数据，默认为都获取
            params.put("type","data");//type - 返回结果的类型，固定为data
            params.put("doctype","json");//doctype - 返回结果的数据格式，xml或json或jsonp
            params.put("version","1.1");//version - 版本，当前最新版本为1.1
            
        try {
            result =net(url, params, "GET");
            System.out.println("转换前："+result);
        	result = result.substring(result.indexOf("[")+1, result.lastIndexOf("]")).replaceAll("\"", "");
        	System.out.println("转换后："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
 
 
    public static void main(String[] args) {
    	getRequest1("维生素C^辅酶");
    	//System.out.println("Necklace, coenzyme, pregnancy nutrients, eye essence, female treasure, enzyme, enzyme, essential nutrients, health skeletons, essential nutrients, probiotics, water cup, male treasure, probiotics, down jacket, children's clothes, puff, probiotics, rice noodles, ha, clothes, pants, clothes, clothing, clothing, protect liver treasure, vitamin C and coenzyme, health package, probiotics, and essential nutrients, package, short wallet, bag, eye essence, protect liver, treasure bag, liquid milk, sports underwear, cotton discharge makeup, skin care suit, mask, clothes, sports bra, toner".split(",").length);
    }
 
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
