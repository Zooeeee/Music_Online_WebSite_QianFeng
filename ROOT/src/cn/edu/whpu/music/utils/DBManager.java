package cn.edu.whpu.music.utils;

import java.sql.*;

/**
 * 用于管理数据库连接：创建连接 关闭连接
 * 
 * @author ZY
 *
 */
public class DBManager {
	/*
	 * 1.将数据库连接驱动jar包导入到当前项目中 ： a.将jar文件拷贝到项目的webContent/WEB-INF/lib文件夹
	 * b.选择lib中jar文件右键->构建路径-> 添加到构建路径
	 */
	/* 2. 准备数据库连接信息 */
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";

	/**
	 * 3.在静态块中通过反射加载数据库驱动
	 */
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个数据库连接
	 * 
	 * @return 数据库连接
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
