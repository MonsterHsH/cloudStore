package com.cloudstore.sql.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.cloudstore.tools.sql.JdbcUtils;

/**
 * 该类实现了对数据库的增删改查操作，包括获取数据库对象中的一条数据，多条数据，
 * 某个字段的值，可以逐条操作，也可以批量操作
 * @author panda
 * @param <T>  实现该类的子类对应的表或视图类
 */
public class JdbcDao<T> implements DAO<T>
{
	private QueryRunner qr=new QueryRunner();      //数据库增删改查工具类
	private Class<T> clazz;
	@SuppressWarnings("unchecked")
	public JdbcDao()
	{
		Type superClass =getClass().getGenericSuperclass();
		//获取运行时类的泛型
		if(superClass instanceof ParameterizedType)
		{
			ParameterizedType paramType=(ParameterizedType)superClass;
			Type[] typeArgs = paramType.getActualTypeArguments();
			if(typeArgs!=null&&typeArgs.length>0)
			{
				if(typeArgs[0] instanceof Class)
				{
					clazz=(Class<T>)typeArgs[0];
				}
			}
		}
	}
	/**
	 * update delete insert操作
	 * @throws SQLException
	 */
	@Override
	public int update(Connection conn, String sql, Object... args) throws SQLException
	{
		try {
			return qr.update(conn, sql, args);
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
	}
	
	/**
	 * 批量处理，update delete insert
	 * @param conn
	 * @param sql
	 * @param obj
	 * @return
	 */
	@Override
	public int[] batch(Connection conn, String sql, Object[][] obj)
	{
		try {
			return qr.batch(conn, sql, obj);
		} catch (SQLException e) {
			
			e.printStackTrace();	
			return null;
		}
	}
	/**
	 * 获取一条记录的某个字段的值
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <E> E getValue(Connection conn, String sql, Object... args) throws SQLException 
	{
		try {
			return (E) qr.query(conn, sql, new ScalarHandler(), args);
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
	}

	/**
	 * 获取结果集
	 * @throws SQLException 
	 */
	@Override
	public List<T> getBeanList(Connection conn, String sql, Object... args) throws SQLException 
	{
		try {
			return  (List<T>) qr.query(conn, sql, new BeanListHandler<>(clazz) , args);
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
	}
	/**
	 * 获取一条结果
	 * @throws SQLException 
	 */
	@Override
	public T getBean(Connection conn, String sql, Object... args) throws SQLException 
	{
		try {
			return qr.query(conn, sql, new BeanHandler<>(clazz), args);
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
	}
	
	/**
	 * 获取某一列的值
	 */
	@Override
	public <E> List<E> getValueList(Connection conn, String sql, Object... args) throws SQLException {
		try {
			return qr.query(conn, sql,new ColumnListHandler<>(),args);
		}finally {
			JdbcUtils.releaseConnection(conn);
		}
	}
}
