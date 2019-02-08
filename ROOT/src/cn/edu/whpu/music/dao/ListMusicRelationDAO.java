package cn.edu.whpu.music.dao;
/**
 * 此类用于完成对 tb_list_music 表的数据库操作
 * @author WW
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.whpu.music.dto.ListMusicRelationDTO;
import cn.edu.whpu.music.utils.DBManager;

public class ListMusicRelationDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * 添加列表和音乐之间的联系关系信息
	 * @param relation 需要添加到数据库中的关系信息
	 * @return 如果添加成功放回true，失败返回false
	 */
	public boolean insertListMusic(ListMusicRelationDTO relation) {
		boolean b = false;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "insert into tb_list_music values(?,?)";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//给sql中的问号赋值
			ps.setInt(1,relation.getListId() );
			ps.setInt(2, relation.getMusicId());
			//执行SQL
			int i = ps.executeUpdate();//i返回对于数据库影响了多少行
			//处理结果
			b = i>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(null, ps, conn);
		}
		return b;
	}
	/**
	 * 根据音乐的id和列表的id取消列表中对应音乐的收藏
	 * @param listId
	 * @param musicId
	 * @return
	 */
	public boolean cancelCollectByMidLid(int listId,int musicId) {
		boolean b  = false;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "delete from tb_list_music where lid = ? and mid=?";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//给sql中的问号赋值
			ps.setInt(1,listId );
			ps.setInt(2, musicId);
			//执行SQL
			int i = ps.executeUpdate();//i返回对于数据库影响了多少行
			//处理结果
			b = i>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(null, ps, conn);
		}
		return b;
	}
	/**
	 * 根据列表id查询
	 * @param uid 需要查询的列表id
	 * @return 返回查询到的关系信息，如果没有查询到信息则返回null
	 */
	public List<ListMusicRelationDTO> queryUserById(int listId) {
		List<ListMusicRelationDTO> list = new ArrayList<ListMusicRelationDTO>();
		try {
			conn = DBManager.getConn();
			String sql = "select * from tb_list_music where lid = ?";
			ps = conn.prepareStatement(sql);
			//给？赋值
			ps.setInt(1, listId);
			//执行SQL
			rs = ps.executeQuery();
			//处理结果
			while(rs.next()) {
				ListMusicRelationDTO relation = 
						new ListMusicRelationDTO(rs.getInt("lid"),rs.getInt("mid"));
				list.add(relation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(rs, ps, conn);
		}
		return list;
	}
}
