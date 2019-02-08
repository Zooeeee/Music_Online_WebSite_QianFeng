package cn.edu.whpu.music.servlets;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.whpu.music.dao.UserDAO;
import cn.edu.whpu.music.dto.UserDTO;

@WebServlet("/UserRegistServlet")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收数据【文件上传】
		try {
			SmartUpload su = new SmartUpload();
			su.initialize(getServletConfig(),request,response);//初始化方法
			su.setAllowedFilesList("jpg,png,bmp");//允许上传的文件格式
			su.setMaxFileSize(2*1024*1024);//运行上传文件最大的大小2M
			su.upload();//执行上传
			//userPhoto用于存储用户上传的头像在项目中的相对路径  photos/42314321432.jpg
			String userPhoto = "photos/";
			//从su对象中取出文本信息和文件
			Files fs = su.getFiles();
			if (fs.getCount() > 0) {
				File f = fs.getFile(0);
				String ext = f.getFileExt();//取到文件的后缀名
				String fileName = new Date().getTime()+"." + f.getFileExt();
				
				userPhoto = fileName;
				//获取项目中photos目录在服务器中的路径
				 String path = getServletContext().getRealPath("photos").toString(); 
				System.out.println(path);
				String path1 = "C:\\Zy\\TomCat\\apache-tomcat-9.0.14\\wtpwebapps\\ROOT\\";
				String path2 = "C:\\Zy\\WorkSpaceWeb\\ROOT\\WebContent\\";
				/* f.saveAs("index/"+path+"/"+fileName); */
//				String path3 = "C:\\Zy\\TomCat\\apache-tomcat-9.0.14\\wtpwebapps\\ROOT\\photos\\";
//				String path2 = "C:\\Zy\\WorkSpaceWeb\\ROOT\\WebContent\\index\\photos\\";
//				f.saveAs(path2+fileName);
//				f.saveAs(path3+fileName);
				f.saveAs(path1+fileName);
				f.saveAs(path2+fileName);
			
			}
			//从su中取出问本信息
			Request req = su.getRequest();
			String userName = req.getParameter("userName");
			String userPwd = req.getParameter("userPwd");
			String userNick = req.getParameter("userNick");
			String userSex = req.getParameter("userSex");
			String userDesc = req.getParameter("userDesc");
			UserDTO user = new UserDTO(0,userName,userPwd,userNick,userSex,userPhoto,userDesc);
			//调用UserDAO中的inertUser方法完成用户注册信息的保存
			UserDAO uDao = new UserDAO();
			boolean b = uDao.insertUser(user);
			System.out.println(b);
			//根据保存结果进行跳转
			String page = "setup.jsp";
			String tipeStr = "<label style='color:red'>注册失败,请重新输入注册信息</label>";
			if (b) {
				page="login.jsp";
				tipeStr = "<label style='color:green'>注册成功，请登录</label>";
			}
			request.setAttribute("tips", tipeStr);
			request.getRequestDispatcher(page).forward(request, response);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
	}

}
