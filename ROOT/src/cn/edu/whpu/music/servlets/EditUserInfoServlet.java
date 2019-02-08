package cn.edu.whpu.music.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.whpu.music.dao.UserDAO;
import cn.edu.whpu.music.dto.UserDTO;

@WebServlet("/EditUserInfoServlet")
public class EditUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接收信息
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userNick = request.getParameter("userNick");
		String userSex = request.getParameter("inlineRadioOptions");
		String userDesc = request.getParameter("userDesc");
		System.out.println("用户信息"+userId+userPwd+userNick+userSex+userDesc);
		int id = Integer.parseInt(userId);
		//根据用户的id查询到该用户之后进行修改
		UserDAO userDAO = new UserDAO();
		UserDTO user = userDAO.querUserById(id);
		user.setUserPwdString(userPwd);
		user.setNick(userNick);
		user.setSex(userSex);
		user.setDesc(userDesc);
		boolean b = userDAO.updateUser(user);
		System.out.print(b);
	}

}
