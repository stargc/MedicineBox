package com.zhiyi.medicinebox.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.type.Type;

/**
 * - Base DAO Class
 *
 * @author ZhaoFeng
 * @creation 2015年8月18日
 *
 */
public interface BaseDAO<T> {

	public SessionFactory getSessionFactory();
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public boolean save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public boolean delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public boolean update(T o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public boolean saveOrUpdate(T o);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return 返回是否执行成功
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 *            timeparam 时间查询
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Object[] timeparam);

	/**
	 * 查询集合 按分页
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            第几页 从1开始
	 * @param rows
	 *            每页几条数据
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 查询集合 按分页
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            第几页 从1开始
	 * @param rows
	 *            每页几条数据
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, Object[] param);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * 得到对象的数目
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * 得到对象的数目
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * 得到对象的数目
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 执行hql语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行hql语句
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 执行hql语句
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);

	/**
	 * 执行hql语句 调用 增删改类 存储过程
	 * 
	 * @param hql
	 * @param param
	 * @return 返回是否执行成功
	 */
	
	/**
	 * 清除 session中的缓存对象
	 * @param o 需要清除的对象
	 * @return 
	 */
	public void evict(T o);
	
	/***
	 * 合并缓存中相同的数据
	 * @param o
	 */
	public boolean merge(T o);
	
	/***
	 * 
	 * 清空hibernate的一级缓存
	 */
	public void clear();
	
	public int executeProcedure(String hql, Object[] param);

	/**
	 * 执行hql语句, 调用数据库中存储过程
	 * 
	 * @param hql
	 * @param param Map类型
	 *            key: hql中的参数; value: hql参数对应的类型(data-date; time-time; 其余的可以传null)
	 * @param output Map类型
	 *           key: 存储过程中定义的返回值参数; value: 存储过程中定义的返回值参数的类型
	 * @param c
	 *            返回值对应的entity类
	 * @return list
	 */
	public List<T> executeProcedureForList(final String hql,
			Map<Object, String> param, Map<String, Type> output, final Class<?> c);

	/**
	 * 执行hql语句, 调用数据库中存储过程
	 * 
	 * @param hql
	 * @param param
	 *            hql中的参数
	 * @param output Map类型
	 *           key: 存储过程中定义的返回值参数; value: 存储过程中定义的返回值参数的类型
	 * @param c
	 *            返回值对应的entity类
	 * @return list
	 */
	public List<T> executeProcedureForList(String hql, Object[] param,
			Map<String, Type> output, Class<?> c);

	/***
	 * 直接执行sql语句
	 * 
	 * @param sql
	 *            纯sql语句
	 * @return
	 */
	public List<T> queryBySql(String sql,Class<?> cls);

	/***
	 * 直接执行sql语句
	 * 
	 * @param sql
	 *            纯sql语句
	 * @param page
	 *            第几页 从1开始
	 * @param rows
	 *            每页几条数据
	 * @return
	 */
	public List<T> queryBySql(String sql, final int page, final int rows, Class<?> cls);

	/***
	 * 直接执行sql语句
	 * 
	 * @param sql
	 *            纯sql语句
	 * @return
	 */
	public Integer excuteBySql(String sql);
	
}
