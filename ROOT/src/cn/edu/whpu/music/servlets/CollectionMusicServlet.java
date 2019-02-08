package cn.edu.whpu.music.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.whpu.music.dao.ListMusicRelationDAO;
import cn.edu.whpu.music.dao.MusicDAO;
import cn.edu.whpu.music.dto.ListMusicRelationDTO;
import cn.edu.whpu.music.dto.MusicDTO;

@WebServlet("/CollectionMusicServlet")
public class CollectionMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接收数据
		String Id = request.getParameter("collectId");
		int listId = Integer.parseInt(Id);
		String musicName = request.getParameter("musicTitle");
		String musicArt = request.getParameter("musicAuthor");
		String musicPath = request.getParameter("musicPath");
		String musicAlbum = request.getParameter("musicAlbum");
		System.out.println("在线歌单信息"+listId+musicName+musicArt+musicPath+musicAlbum);
		//接受到的歌曲信息，将该歌曲首先存入数据库中，然后将该歌曲的id取出
		MusicDAO musicDAO = new MusicDAO();
		MusicDTO music = new MusicDTO(0,musicName,musicArt,musicAlbum,musicPath);
		boolean b = musicDAO.insertMusic(music);
		System.out.println(b);
		//查找到刚刚加入数据库 中的歌曲的id，连同列表的id一起加入到关系表中
		MusicDTO music2 = musicDAO.queryMusicByName(musicName,musicArt);
		System.out.print(music2.getMusicId());
		int musicId = music2.getMusicId();
		System.out.println(musicId);

		ListMusicRelationDAO relationDAO = new ListMusicRelationDAO(); boolean a =
		relationDAO.insertListMusic(new ListMusicRelationDTO(listId,musicId));
		System.out.println(a);
	}

}
