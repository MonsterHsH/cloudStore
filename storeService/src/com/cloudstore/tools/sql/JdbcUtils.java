package com.cloudstore.tools.sql;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JdbcUtils 
{
	private static ComboPooledDataSource cloudStore;     //数据库连接池
	static
	{
		cloudStore =new ComboPooledDataSource("cloudStore");   //初始化数据库连接池
	}
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		return cloudStore.getConnection();
	}
	/**
	 * 释放连接
	 * @param conn
	 */
	public static void releaseConnection(Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
