package cn.edu.whpu.music.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.whpu.music.dao.MusicListDAO;
import cn.edu.whpu.music.dto.MusicListDTO;
/**
 * 根据传递过来的歌单的id删除对应的歌单
 * @author DYC
 *
 */
@WebServlet("/DeleteMusicListServlet")
public class DeleteMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接收信息
		String listId = request.getParameter("listId");
		int MLid = Integer.parseInt(listId);
		//System.out.println(listId);
		//根据歌单的id来删除对应的歌单
		MusicListDAO listDAO = new MusicListDAO();
		boolean b = listDAO.deleteMusicList(MLid);
		//System.out.print(b);
	}

}
