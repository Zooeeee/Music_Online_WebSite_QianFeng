package cn.edu.whpu.music.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.whpu.music.dao.MusicListDAO;
import cn.edu.whpu.music.dao.UserDAO;
import cn.edu.whpu.music.dto.MusicListDTO;
import cn.edu.whpu.music.dto.UserDTO;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收用户名和密码
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		//调用Use人DAO中checkLogin方法
		UserDAO uDao = new UserDAO();
		UserDTO user = uDao.checkLogin(name, pwd);
		System.out.println(name +"  "+ pwd);
		if (user == null) {
			//登录失败
			request.setAttribute("tips", "<h4 style='color:red'>用户名或密码错误</h4>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			//登录成功
			request.getSession().setAttribute("user",user);
			
			
			MusicListDAO musicListDAO = new MusicListDAO();
			List<MusicListDTO> list =  musicListDAO.listMusicLists(user.getUserId());
			list.forEach(ele->System.out.println(ele.getListName()));
			request.setAttribute("musicLists", list);
			
			/* response.sendRedirect("index/index.jsp"); */
			request.getRequestDispatcher("index/index.jsp").forward(request, response);
		}
	}

}
