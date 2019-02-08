package cn.edu.whpu.music.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cn.edu.whpu.music.dto.ListMusicRelationDTO;
import cn.edu.whpu.music.dto.MusicDTO;
import cn.edu.whpu.music.dto.MusicListDTO;
import cn.edu.whpu.music.utils.DBManager;

/**
 * 此类用于完成对 tb_musiclists操作
 * 
 * @author ZY
 *
 */

public class MusicListDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * 此方法添加歌单
	 * @param musicList 需要添加到数据库的歌单信息
	 * @return 如果添加成功 则返回true 失败则返回false
	 */
	public boolean insertMusicList(MusicListDTO musicList ) {
		boolean b = false;
		try {
			// 1.创建连接
			conn = DBManager.getConn();
			// 2.准备SQL指令
			String sql = "insert into tb_musiclists(list_name,list_desc,list_uid) values(?,?,?)";
			// 3.加载sql
			ps = conn.prepareStatement(sql);
			// 4.给sql中的？赋值
			ps.setString(1, musicList.getListName());
			ps.setString(2, musicList.getListDesc());
			/* ps.setString(3, musicList.getListTime()); */
			ps.setInt(3, musicList.getListUid());
			// 5.执行sql i表示执行sql语句之后对数据库的几行数据造成了影响
			int i = ps.executeUpdate();
			// 6.处理结果
			b = i>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(null, ps, conn);
		}		
		return b ;
	}
	/**
	 * 根据歌单的id来查询该id下的所有的音乐信息
	 * @param listId 歌单的id
	 * @return 返回对应id下的所有的音乐信息
	 */
	public List<MusicDTO> queryMusicByListId(int listId){
		//通过list_id需要先获得对应音乐的id
		ListMusicRelationDAO relation = new ListMusicRelationDAO();
		List<ListMusicRelationDTO> listRelation = relation.queryUserById(listId);
		//通过获得对应的音乐id在音乐表中查找对应的音乐
		List<MusicDTO> list = new ArrayList<MusicDTO>();
		MusicDAO musicDAO = new MusicDAO();
		for (int i = 0; i < listRelation.size(); i++) {
			MusicDTO music = musicDAO.queryMusicById(listRelation.get(i).getMusicId());
			list.add(music);
		}
		return list;
	}
	/**
	 * 此方法根据listId来删除歌单
	 * @param listId 需要删除的歌单id
	 * @return  如果删除成功 则返回true 失败则返回false
	 */
	public boolean deleteMusicList(int listId) {
		boolean b = false;
		try {
			//1.创建连接
			conn = DBManager.getConn();
			// 2.准备SQL指令
			String sql = "delete from tb_musiclists where list_id = ?";
			// 3.加载sql
			ps = conn.prepareStatement(sql);
			// 4.给sql中的？赋值
			ps.setInt(1, listId);
			// 5.执行sql i表示执行sql语句之后对数据库的几行数据造成了影响
			int i = ps.executeUpdate();
			// 6.处理结果
			b = i>0?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, ps, conn);
		}
		
		return b ;
	}
	
	/**
	 * 根据歌单id来修改歌单的其他数据
	 * @param musicList   包含修改的歌单的id和其他字段
	 * @return 如果修改成功 则返回true 失败则返回false
	 */
	public boolean updateMusicList(MusicListDTO musicList) {
		boolean b = false;
		try {
			// 1.创建连接
			conn = DBManager.getConn();
			// 2.准备SQL指令
			String sql = "update tb_musiclists set list_name = ? ,list_desc = ? ,list_uid = ? where list_id = ?";
			// 3.加载sql
			ps = conn.prepareStatement(sql);
			// 4.给sql中的？赋值
			ps.setString(1, musicList.getListName());
			ps.setString(2, musicList.getListDesc());
			/* ps.setString(3, musicList.getListTime()); */
			ps.setInt(3, musicList.getListUid());
			ps.setInt(4, musicList.getListId());
			// 5.执行sql i表示执行sql语句之后对数据库的几行数据造成了影响
			int i = ps.executeUpdate();
			// 6.处理结果
			b = i>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(null, ps, conn);
		}		
		return b ;
	}
	
	
	/**
	 * 根据歌单id来查找歌单信息
	 * @param uid 歌单id
	 * @return 查询到的歌单信息 如果查询不到 则返回null
	 */
	public MusicListDTO	queryMusicListById(int listId) {
		MusicListDTO musicList = null;
		try {
			// 1.创建连接
			conn = DBManager.getConn();
			// 2.准备SQL指令
			String sql = "select * from tb_musiclists where list_id = ?";
			// 3.加载sql
			ps = conn.prepareStatement(sql);
			// 4.给sql中的？赋值
			ps.setInt(1, listId);
			// 5.执行sql i表示执行sql语句之后对数据库的几行数据造成了影响
			rs = ps.executeQuery();
			// 6.处理结果
			while (rs.next()) {
				musicList = new MusicListDTO();
				musicList.setListId(listId);
				musicList.setListName(rs.getString("list_name"));
				musicList.setListDesc(rs.getString("list_desc"));
				musicList.setListTime(rs.getString("list_time"));
				musicList.setListUid(rs.getInt("list_uid"));	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(rs, ps, conn);
		}		
		return musicList;
	}
	
	/**
	 * 查询所有的歌单信息
	 * @return 包含多个歌单的集合
	 */
	public List<MusicListDTO> listMusicLists(int Id) {
		List<MusicListDTO> musicLists = new ArrayList<MusicListDTO>();
		try {
			// 1.创建连接
			conn = DBManager.getConn();
			// 2.准备SQL指令
			String sql = "select * from tb_musiclists where list_uid = ?";
			// 3.加载sql
			ps = conn.prepareStatement(sql);
			// 4.给sql中的？赋值
			ps.setInt(1, Id);
			// 5.执行sql i表示执行sql语句之后对数据库的几行数据造成了影响
			rs = ps.executeQuery();		
			// 6.处理结果
			while (rs.next()) {
				MusicListDTO musicList = null;
				musicList = new MusicListDTO();
				musicList.setListId(rs.getInt("list_id"));
				musicList.setListName(rs.getString("list_name"));
				musicList.setListDesc(rs.getString("list_desc"));
				musicList.setListTime(rs.getString("list_time"));
				musicList.setListUid(rs.getInt("list_uid"));	
				musicLists.add(musicList);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(rs, ps, conn);
		}		
		return musicLists;
	}
	
	public static void main(String[] args) {
	
		
	}	
}

