package com.cloudstore.sql.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


/**
 * 数据库操作接口，提供增删改查方法
 * @author panda
 * @param <T> 指定查询的表所对应的数据类
 */
public interface DAO<T> 
{
	/**
	 * 更新数据方法，传入数据库连接，sql语句，查询条件
	 * @param conn  数据库连接Connection
	 * @param sql   sql语句
	 * @param args  查询条件
	 * @return      返回查询结果行数
	 */
	public int update(Connection conn,String sql, Object ... args)throws SQLException;
	/**
	 * 批量处理
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public int[] batch(Connection conn,String sql, Object[][] args)throws SQLException;
	/**
	 * 获取某一行的某一个字段的值
	 * @param conn  数据库连接Connection
	 * @param sql   sql语句
	 * @param args  查询条件
	 * @return 返回查询结果
	 */
	public <E> E getValue(Connection conn,String sql, Object ... args)throws SQLException;
	/**
	 * 获取符合args条件的结果集
	 * @param conn  数据库连接Connection
	 * @param sql  sql语句
	 * @param args 查询条件
	 * @return 返回查询结果集
	 * @throws SQLException 
	 */
	public List<T> getBeanList(Connection conn,String sql,Object ...args) throws SQLException;
	
	/**
	 * 获取符合args条件的一行记录
	 * @param conn  数据库连接Connection
	 * @param sql  sql语句
	 * @param args 查询条件
	 * @return
	 */
	public T getBean(Connection conn,String sql, Object ... args)throws SQLException;
	
	/**
	 * 获取满足条件的一列的值
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public <E> List<E> getValueList(Connection conn,String sql, Object ... args) throws SQLException;
	
	
	
	
}
