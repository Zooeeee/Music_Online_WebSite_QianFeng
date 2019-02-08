package cn.edu.whpu.music.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.whpu.music.dao.ListMusicRelationDAO;

@WebServlet("/CancelCollectMusicServlet")
public class CancelCollectMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接收数据
		String musicId = request.getParameter("musicId");
		String listId = request.getParameter("listId");
		System.out.println("删除歌曲信息" + musicId+"   "+listId);
		//根据接收到的音乐id和列表id删除对应的一条数据
		ListMusicRelationDAO relationDAO = new ListMusicRelationDAO();
		int mid = Integer.parseInt(musicId);
		int lid = Integer.parseInt(listId);
		
		boolean b = relationDAO.cancelCollectByMidLid(lid, mid);
		System.out.println("是否取消？"+b);
	}

}
