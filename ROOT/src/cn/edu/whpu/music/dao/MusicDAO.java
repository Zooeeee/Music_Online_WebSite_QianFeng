package cn.edu.whpu.music.dao;
/**
 * 此类用于完成对 tb_musics 表的数据库操作
 * @author WW
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.whpu.music.dto.MusicDTO;
import cn.edu.whpu.music.utils.DBManager;

public class MusicDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * 添加音乐信息
	 * @param music 需要添加到数据库的音乐信息
	 * @return 如果添加成功放回true，失败返回false
	 */
	public boolean insertMusic(MusicDTO music) {
		boolean b = false;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "insert into tb_musics(music_name,music_art,music_album,music_path) values(?,?,?,?)";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//给sql中的问号赋值
			ps.setString(1, music.getMusicName());
			ps.setString(2, music.getMusicArt());
			ps.setString(3, music.getMusicAlbum());
			ps.setString(4, music.getMusicPath());
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
	 * 根据音乐id删除一条音乐信息
	 * @param musicId 需要删除的音乐的Id
	 * @return 如果删除成功放回true，失败返回false
	 */
	public boolean deleteMusic(int musicId) {
		boolean b = false;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "delete from tb_musics where music_id = ?";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//给sql中的问号赋值
			ps.setInt(1, musicId);
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
	 * 根据音乐Id修改用户的其他的信息
	 * @param music 包含需要修改的音乐Id和其它字段的值
	 * @return 如果修改成功放回true，失败返回false
	 */
	public boolean updateMusic(MusicDTO music) {
		boolean b = false;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "update tb_musics set music_name=?,music_art=?,music_album=?,music_path=? where music_id=?";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//给sql中的问号赋值
			ps.setString(1, music.getMusicName());
			ps.setString(2, music.getMusicArt());
			ps.setString(3, music.getMusicAlbum());
			ps.setString(4, music.getMusicPath());
			ps.setInt(5, music.getMusicId());
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
	 * 根据音乐的Id查询一条音乐信息
	 * @param musicId 需要查询的音乐Id
	 * @return  返回查询到的音乐信息，如果没有查询到音乐信息则返回null
	 */
	public MusicDTO queryMusicById(int musicId) {
		MusicDTO music = null;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "select * from tb_musics where music_id=?";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//给sql中的问号赋值
			ps.setInt(1, musicId);
			//执行SQL
			rs = ps.executeQuery();
			//处理结果
			while(rs.next()) {
				music = new MusicDTO();
				music.setMusicId(rs.getInt("music_id"));
				music.setMusicName(rs.getString("music_name"));
				music.setMusicArt(rs.getString("music_art"));
				music.setMusicAlbum(rs.getString("music_album"));
				music.setMusicPath(rs.getString("music_path"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(rs, ps, conn);
		}
		return music;
	}
	/**
	 * 根据音乐的Id查询一条音乐信息
	 * @param musicId 需要查询的音乐Id
	 * @return  返回查询到的音乐信息，如果没有查询到音乐信息则返回null
	 */
	public MusicDTO queryMusicByName(String musicName,String musicArt) {
		MusicDTO music = null;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "select * from tb_musics where music_name=? and music_art=?";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//给sql中的问号赋值
			ps.setString(1, musicName);
			ps.setString(2, musicArt);
			//执行SQL
			rs = ps.executeQuery();
			//处理结果
			while(rs.next()) {
				music = new MusicDTO();
				music.setMusicId(rs.getInt("music_id"));
				music.setMusicName(rs.getString("music_name"));
				music.setMusicArt(rs.getString("music_art"));
				music.setMusicAlbum(rs.getString("music_album"));
				music.setMusicPath(rs.getString("music_path"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(rs, ps, conn);
		}
		return music;
	}
	
	/**
	 * 查询所有的音乐信息
	 * @return 包含多个用户信息的集合
	 */
	public List<MusicDTO> listMusics() {
		List<MusicDTO> ms = new ArrayList<MusicDTO>();
		MusicDTO music = null;
		try {
			//创建连接
			conn = DBManager.getConn();
			//准备SQL指令
			String sql = "select * from tb_musics";
			//加载SQL
			ps = conn.prepareStatement(sql);
			//执行SQL
			rs = ps.executeQuery();
			//处理结果
			while(rs.next()) {
				music = new MusicDTO();
				music.setMusicId(rs.getInt("music_id"));
				music.setMusicName(rs.getString("music_name"));
				music.setMusicArt(rs.getString("music_art"));
				music.setMusicAlbum(rs.getString("music_album"));
				music.setMusicPath(rs.getString("music_path"));
				ms.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(rs, ps, conn);
		}
		return ms;
	}				
}



