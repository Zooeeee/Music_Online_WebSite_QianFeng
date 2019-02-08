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

import cn.edu.whpu.music.dao.MusicDAO;
import cn.edu.whpu.music.dto.MusicDTO;

/**
 * Servlet implementation class LocalMusicServlet
 */
@WebServlet("/LocalMusicServlet")
public class LocalMusicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String string = request.getParameter("value");
//		System.out.println(string);
		MusicDAO musicDAO = new MusicDAO();
		List<MusicDTO>  mList = musicDAO.listMusics();
//		for (MusicDTO musicDTO : mList) {
//			System.out.println(musicDTO.toString());
//		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		JSONArray jsonArray = new JSONArray();
		try {
			for (int i = 0; i < mList.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("musicId",mList.get(i).getMusicId());
				jsonObject.put("musicName",mList.get(i).getMusicName());
				jsonObject.put("musicArt",mList.get(i).getMusicArt());
				jsonObject.put("musicAlbum",mList.get(i).getMusicAlbum());
				jsonObject.put("musicPath",mList.get(i).getMusicPath());
				
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
