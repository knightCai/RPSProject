package com.yiya.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ActionUpload {
	
	/**
	 * action上传文件
	 * @param request
	 * @param savePath
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(HttpServletRequest request,String savePath) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String filename = "";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("上传文件的大小:" + item.getSize());
						System.out.println("上传文件的类型:" + item.getContentType());
						// item.getName()返回上传文件在客户端的完整路径名称
						System.out.println("上传文件的名称:" + item.getName());

						filename = item.getName();
						filename = request.getAttribute("type")+new SimpleDateFormat("yyyyMMddmmhhss").format(new Date())+filename.substring(filename.lastIndexOf("."),filename.length());

						//上传文件的保存路径
						File file = new File(request.getSession().getServletContext().getRealPath("/") + savePath, filename);
						item.write(file);
					}else{
						throw new Exception("没有选择上传文件！");
					}
				}
			}
		}catch(FileUploadException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}
	
	/**
	 * 文件下载
	 * @param fileName
	 * @param filepath
	 * @param response
	 */
	public void downFiel(String fileName,String filepath,HttpServletResponse response){
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"ISO-8859-1"));
			InputStream is = new FileInputStream(new File(filepath));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while((length = is.read(b)) > 0){
				os.write(b,0,length);
			}
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
