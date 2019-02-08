package cn.edu.whpu.music.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.whpu.music.dao.MusicListDAO;
import cn.edu.whpu.music.dto.MusicListDTO;

@WebServlet("/UpdateMusicListServlet")
public class UpdateMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接收信息
		String id = request.getParameter("listId");
		int listId = Integer.parseInt(id);
		String listName = request.getParameter("listName");
		String listDesc = request.getParameter("listDesc");
		//System.out.print(Id +"  "+ listName +"  "+ listDesc);
		//根据传递过来的歌单id得到原始的歌单数据
		MusicListDAO musicListDAO = new MusicListDAO();
		MusicListDTO musicList = musicListDAO.queryMusicListById(listId);
		musicList.setListName(listName);
		musicList.setListDesc(listDesc);
		boolean b = musicListDAO.updateMusicList(musicList);
		System.out.print(b);
		
	}

}
