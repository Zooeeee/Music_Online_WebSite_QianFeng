package cn.edu.whpu.music.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import cn.edu.whpu.music.dto.UserDTO;
import cn.edu.whpu.music.utils.DBManager;

/**
 * 此类用于对于数据库表 tb_users 的操作
 * 
 * @author SevenEyes
 *
 */
public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * 添加用户信息
	 * 
	 * @param user 需要添加到数据库的用户信息
	 * @return 如果添加成功返回true,失败则返回false
	 */
	public boolean insertUser(UserDTO user) {
		boolean b = false;
		try {
			conn = DBManager.getConn();
			String sql = "insert into tb_users values(null,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			// 给？赋值
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwdString());
			ps.setString(3, user.getNick());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getPic());
			ps.setString(6, user.getDesc());
			// 执行SQL
			int i = ps.executeUpdate();
			// 处理结果
			b = i > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, ps, conn);
		}
		return b;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user 需要修改到数据库的用户信息
	 * @return 如果添加成功返回true,失败则返回false
	 */
	public boolean updateUser(UserDTO user) {
		boolean b = false;
		try {
			conn = DBManager.getConn();
			String sql = "update tb_users set user_name = ?," + "user_pwd = ?,user_nick = ?,user_sex = ?,"
					+ "user_pic = ?,user_desc = ? where user_id = ?";
			ps = conn.prepareStatement(sql);
			// 给？赋值
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPwdString());
			ps.setString(3, user.getNick());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getPic());
			ps.setString(6, user.getDesc());
			ps.setInt(7, user.getUserId());
			// 执行SQL
			int i = ps.executeUpdate();
			// 处理结果
			b = i > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, ps, conn);
		}
		return b;
	}

	/**
	 * 查询一条用户信息
	 * 
	 * @param uid 需要查询的用户ID
	 * @return 如果无查询结果，则返回null
	 */
	public UserDTO querUserById(int uid) {
		UserDTO user = null;
		try {
			conn = DBManager.getConn();
			String sql = "select * from tb_users where user_id = ?";
			ps = conn.prepareStatement(sql);
			// 给？赋值
			ps.setInt(1, uid);
			// 执行SQL
			rs = ps.executeQuery();
			// 处理结果
			while (rs.next()) {
				user = new UserDTO(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_pwd"),
						rs.getString("user_nick"), rs.getString("user_sex"), rs.getString("user_pic"),
						rs.getString("user_desc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, ps, conn);
		}
		return user;
	}


	/**
	 * 通过用户名和密码查询用户信息是否存在
	 * 
	 * @param 用户名
	 * @param 密码
	 * @return UserDTO对象
	 */
	public UserDTO checkLogin(String name, String pwd) {
		UserDTO user = null;
		try {
			conn = DBManager.getConn();
			String sql = "select * from tb_users where user_name = ? and user_pwd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new UserDTO
				(rs.getInt("user_id"), 
				rs.getString("user_name"), 
				rs.getString("user_pwd"),
				rs.getString("user_nick"), 
				rs.getString("user_sex"), 
				rs.getString("user_pic"),
				rs.getString("user_desc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, ps, conn);
		}

		return user;
	}

	
}
