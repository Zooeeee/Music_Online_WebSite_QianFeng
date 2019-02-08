package cn.edu.whpu.music.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.whpu.music.dao.MusicListDAO;
import cn.edu.whpu.music.dto.MusicListDTO;

@WebServlet("/AddMusicListServlet")
public class AddMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接收信息
		String listUid = request.getParameter("listUid");
		String listDesc = request.getParameter("listDesc");
		int uid = Integer.parseInt(listUid);
		String listName = request.getParameter("listName");
		System.out.println("用户id"+listUid + " 用户名字 " + listName+"详情" +listDesc);
		//接收到用户id和歌单的名字用来创建一个新的歌单
		MusicListDAO listDAO = new MusicListDAO();
		MusicListDTO musicList = new MusicListDTO(0,listName,listDesc,"abc",uid);
		Boolean b = listDAO.insertMusicList(musicList);
		//System.out.println(b);
	}

}
