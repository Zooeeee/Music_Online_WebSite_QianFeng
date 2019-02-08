package cn.edu.whpu.music.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.whpu.music.dao.MusicListDAO;
import cn.edu.whpu.music.dto.MusicDTO;

@WebServlet("/ShowMusicsFromListServlet")
public class ShowMusicsFromListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收传入的歌单id
		request.setCharacterEncoding("UTF-8");
		String listId = request.getParameter("listId");
		System.out.println(listId);
		listId = ""+listId;
		int id = Integer.parseInt(listId);
		//获得歌单的id查询到歌单之下的所有的音乐信息
		
		 MusicListDAO musicListDAO = new MusicListDAO(); 
		 List<MusicDTO> list =musicListDAO.queryMusicByListId(id); 
		
		/*
		 * for (int i = 0; i < list.size(); i++) {
		 * System.out.print(list.get(i).toString()); }
		 */
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		 JSONArray jsonArray = new JSONArray();
			try {
				for (int i = 0; i < list.size(); i++) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("musicId",list.get(i).getMusicId());
					jsonObject.put("musicName",list.get(i).getMusicName());
					jsonObject.put("musicArt",list.get(i).getMusicArt());
					jsonObject.put("musicAlbum",list.get(i).getMusicAlbum());
					jsonObject.put("musicPath",list.get(i).getMusicPath());
					
					jsonArray.add(jsonObject);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(jsonArray.size());
			PrintWriter pWriter = response.getWriter();
			pWriter.write(jsonArray.toString());
			pWriter.flush();
			pWriter.close();
		}

}
