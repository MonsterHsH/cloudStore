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
 * 璇ョ被瀹炵幇浜嗗鏁版嵁搴撶殑澧炲垹鏀规煡鎿嶄綔锛屽寘鎷幏鍙栨暟鎹簱瀵硅薄涓殑涓�鏉℃暟鎹紝澶氭潯鏁版嵁锛�
 * 鏌愪釜瀛楁鐨勫�硷紝鍙互閫愭潯鎿嶄綔锛屼篃鍙互鎵归噺鎿嶄綔
 * @author panda
 * @param <T>  瀹炵幇璇ョ被鐨勫瓙绫诲搴旂殑琛ㄦ垨瑙嗗浘绫�
 */
public class JdbcDao<T> implements DAO<T>
{
	private QueryRunner qr=new QueryRunner();      //鏁版嵁搴撳鍒犳敼鏌ュ伐鍏风被
	private Class<T> clazz;
	@SuppressWarnings("unchecked")
	public JdbcDao()
	{
		Type superClass =getClass().getGenericSuperclass();
		//鑾峰彇杩愯鏃剁被鐨勬硾鍨�
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
	 * update delete insert鎿嶄綔
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
	 * 鎵归噺澶勭悊锛寀pdate delete insert
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
	 * 鑾峰彇涓�鏉¤褰曠殑鏌愪釜瀛楁鐨勫��
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
	 * 鑾峰彇缁撴灉闆�
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
	 * 鑾峰彇涓�鏉＄粨鏋�
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
	 * 鑾峰彇鏌愪竴鍒楃殑鍊�
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
