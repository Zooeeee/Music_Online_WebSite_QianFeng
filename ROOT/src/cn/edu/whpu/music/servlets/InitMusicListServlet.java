package cn.edu.whpu.music.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.whpu.music.dao.MusicListDAO;
import cn.edu.whpu.music.dto.MusicListDTO;

/**
 * 接收用户的id查询用户id对应的
 * @author DYC
 *
 */
@WebServlet("/InitMusicListServlet")
public class InitMusicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("userId");
		int uid = Integer.parseInt(id);
		//接收到用户的id 查询对应的歌单信息
		MusicListDAO musicListDAO = new MusicListDAO();
		List<MusicListDTO> list = musicListDAO.listMusicLists(uid);
		response.setContentType("application/json");
		 JSONArray jsonArray = new JSONArray();
			try {
				for (int i = 0; i < list.size(); i++) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("listId",list.get(i).getListId());
					jsonObject.put("listName",list.get(i).getListName());
					jsonObject.put("listDesc",list.get(i).getListDesc());
					jsonObject.put("listTime",list.get(i).getListTime());
					jsonObject.put("listUid",list.get(i).getListUid());	
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
