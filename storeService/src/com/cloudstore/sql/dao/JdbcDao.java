package com.cloudstore.sql.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.cloudstore.sql.tools.JdbcUtils;

/**
 * 
 * @author panda
 * @param <T>
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
	 * 更新操作
	 */
	public int update(Connection conn, String sql, Object... args)
	{
		try {
			return qr.update(conn, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();	
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
		return 0;
	}
	
	/**
	 * 插入多条数据
	 * @param conn
	 * @param sql
	 * @param obj
	 * @return
	 */
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
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <E> E getForValue(Connection conn, String sql, Object... args) 
	{
		try {
			return (E) qr.query(conn, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
		return null;
	}

	/**
	 * 获取结果集
	 */
	public List<T> getForList(Connection conn, String sql, Object... args) 
	{
		try {
			return  (List<T>) qr.query(conn, sql, new BeanListHandler<>(clazz) , args);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
	}
	
	/**
	 * 获取一条结果
	 */
	public T get(Connection conn, String sql, Object... args) 
	{
		try {
			return qr.query(conn, sql, new BeanHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(conn);
		}
		return null;
	}
}
