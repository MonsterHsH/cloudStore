package com.cloudstore.sql.dao;

import java.sql.Connection;
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
	public int update(Connection conn,String sql, Object ... args);
	/**
	 * 获取某一行的某一个字段的值
	 * @param conn  数据库连接Connection
	 * @param sql   sql语句
	 * @param args  查询条件
	 * @return 返回查询结果
	 */
	public <E> E getForValue(Connection conn,String sql, Object ... args);
	/**
	 * 获取符合args条件的结果集
	 * @param conn  数据库连接Connection
	 * @param sql  sql语句
	 * @param args 查询条件
	 * @return 返回查询结果集
	 */
	public List<T> getForList(Connection conn,String sql,Object ...args);
	
	/**
	 * 获取符合args条件的一行记录
	 * @param conn  数据库连接Connection
	 * @param sql  sql语句
	 * @param args 查询条件
	 * @return
	 */
	public T get(Connection conn,String sql, Object ... args);
	
}
