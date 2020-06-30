package edu.prj.util;

import java.io.IOException;
import java.sql.*;

public class DbPub {
	// 连接数据库的四大金刚：驱动类全名STD_DRIVER/数据库地址STD_URL/账号STD_USERNAME/密码STD_PASSWORD
	// 连接数据库的三大对象：连接对象Connection/语句对象Statement/结果集对象Resultset

	// MYSQL 的四大金刚
	public static String STD_DRIVER;
	public static String STD_URL;
	public static String STD_USERNAME;
	public static String STD_PASSWORD;

	public static String filename = "/app.properties";
	static {
		// 1.从配置文件获取四大金刚
		// Properties类专门用于读取扩展名为properties的配置文件
		java.util.Properties prop = new java.util.Properties();
		try {
			prop.load(DbPub.class.getResourceAsStream(filename));
			STD_DRIVER = prop.getProperty("STD_DRIVER");
			STD_URL = prop.getProperty("STD_URL");
			STD_USERNAME = prop.getProperty("STD_USERNAME");
			STD_PASSWORD = prop.getProperty("STD_PASSWORD");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("【" + filename + "】不存在!");
		}

		// 2.加载驱动类名
		try {
			Class.forName(DbPub.STD_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("类全名【" + DbPub.STD_DRIVER + "】没有找到。");
		}
	}

	public static Connection getConn() {
		Connection conn = null;
		// 使用驱动管理器类DriverManager.创建一个连接对象conn
		try {
			conn = DriverManager.getConnection(DbPub.STD_URL, DbPub.STD_USERNAME, DbPub.STD_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			// 不为空，才能调用close()
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, Statement stmt) {
		// 及时释放三大对象
		close(conn, stmt, null);
	}

	public static void close(Connection conn) {
		// 及时释放三大对象
		close(conn, null, null);
	}

	/**
	 * 执行查询SQL，返回rs
	 * 
	 * @param conn   连接对象
	 * @param sql    SQL语句
	 * @param params 数组形式的SQL占位符对应的参数
	 * @return rs
	 */
	public static ResultSet query(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 使用conn ，创建stmt
		try {
			stmt = conn.prepareStatement(sql);

			if (params != null) {
				// 设置参数列表
				for (int i = 0; i < params.length; i++) {
					// 因为问号参数的索引是从1开始，所以是i+1
					// preStmt.setObject(i+1,params[i]+
					// "");//将所有值都转为字符串形式，好让setObject成功运行
					stmt.setObject(i + 1, params[i]);// 不转为字符串形式的版本
				}
			}
			// stmt,执行sql语句，会返回一个rs
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("执行【：" + sql + "】出现错误。");
		}
		return rs;

	}

	/**
	 * 执行增删改 返回受影响行数
	 * 
	 * @param conn   连接对象
	 * @param sql    SQL语句
	 * @param params 数组形式的SQL占位符对应的参数
	 * @return 受影响的行数
	 */

	public static Long update(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		Integer num = 0;
		// 使用conn ，创建stmt
		try {
			stmt = conn.prepareStatement(sql);
			

			if (params != null) {
				// 设置参数列表
				for (int i = 0; i < params.length; i++) {
					// 因为问号参数的索引是从1开始，所以是i+1
					// preStmt.setObject(i+1,params[i]+
					// "");//将所有值都转为字符串形式，好让setObject成功运行
					stmt.setObject(i + 1, params[i]);// 不转为字符串形式的版本
				}
			}
			// stmt,执行sql语句，会返回一个整数，代表受影响的行数
			num = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("执行【：" + sql + "】出现错误。");
		}
		return num.longValue();

	}
	public static Long queryScalarLong(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long result =0L;
		// 使用conn ，创建stmt
		try {
			stmt = conn.prepareStatement(sql);

			if (params != null) {
				// 设置参数列表
				for (int i = 0; i < params.length; i++) {
					// 因为问号参数的索引是从1开始，所以是i+1
					// preStmt.setObject(i+1,params[i]+
					// "");//将所有值都转为字符串形式，好让setObject成功运行
					stmt.setObject(i + 1, params[i]);// 不转为字符串形式的版本
				}
			}
			// stmt,执行sql语句，会返回一个rs
			rs = stmt.executeQuery();
			if(rs.next()) {
				result=rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("执行【：" + sql + "】出现错误。");
		}
		return result;

	}
}
