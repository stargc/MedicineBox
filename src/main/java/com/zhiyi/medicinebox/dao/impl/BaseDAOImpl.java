package com.zhiyi.medicinebox.dao.impl;

import com.zhiyi.medicinebox.dao.BaseDAO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Administrator
 */
@Repository(value = "baseDAO")
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean save(T o) {
		try {
			Session session = this.getCurrentSession();
			session.save(o);
			session.flush();
			session.clear();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(T o) {
		try {
			Session session = this.getCurrentSession();
			session.delete(o);
			session.flush();
			session.clear();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
		return true;
	}

	@Override
	public boolean update(T o) {
		try {
			Session session = this.getCurrentSession();
			session.update(o);
			session.flush();
			session.clear();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(T o) {
		try {
			Session session = this.getCurrentSession();
			session.saveOrUpdate(o);
			session.flush();
			session.clear();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
		return true;
	}

	@Override
	public List<T> find(String hql) {
		try {
			Session session = this.getCurrentSession();
			logger.info( "【" + hql.toString() + "】");
			List<T> Lts = session.createQuery(hql).list();
			session.flush();
			session.clear();
			return Lts;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public List<T> find(String hql, Object[] param) {
		try {
			Session session = this.getCurrentSession();
			Query q = session.createQuery(hql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
			}
			logger.info( "【" + q.toString() + "】");
			List<T> Lts = q.list();
			session.flush();
			session.clear();
			return Lts;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public List<T> find(String hql, Object[] param, Object[] paramType) {
		try {
			Session session = this.getCurrentSession();
			Query q = session.createQuery(hql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if (paramType[i].equals("date")) {
						q.setDate(i, (java.util.Date) param[i]);
					} else if (paramType[i].equals("time")) {
						q.setTime(i, (java.util.Date) param[i]);
					} else {
						q.setParameter(i, param[i]);
					}
				}
			}
			logger.info( "【" + q.toString() + "】");
			List<T> Lts = q.list();
			session.flush();
			session.clear();
			return Lts;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public List<T> find(String hql, List<Object> param) {
		try {
			Session session = this.getCurrentSession();
			Query q = session.createQuery(hql);
			if (param != null && param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					q.setParameter(i, param.get(i));
				}
			}
			logger.info( "【" + q.toString() + "】");
			List lts = q.list();
			session.flush();
			session.clear();
			return lts;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		session.flush();
		session.clear();
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		logger.info( "【" + q.toString() + "】");
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		session.flush();
		session.clear();
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		logger.info( "【" + q.toString() + "】");
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public T get(Class<T> c, Serializable id) {
		try {
			Session session = this.getCurrentSession();
			T t = (T) session.get(c, id);
			session.flush();
			session.clear();
			return t;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public T get(String hql, Object[] param) {
		try {
			List<T> l = this.find(hql, param);
			if (l != null && l.size() > 0) {
				return l.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public T get(String hql, List<Object> param) {
		try {
			List<T> l = this.find(hql, param);
			if (l != null && l.size() > 0) {
				return l.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public Long count(String hql) {
		try {
			Session session = this.getCurrentSession();
			logger.info( "【" + hql.toString() + "】");
			Long num = (Long) session.createQuery(hql).uniqueResult();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public Long count(String hql, Object[] param) {
		try {
			Session session = this.getCurrentSession();
			Query q = session.createQuery(hql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
			}
			logger.info( "【" + q.toString() + "】");
			Long num = (Long) q.uniqueResult();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public Long count(String hql, List<Object> param) {
		try {
			Session session = this.getCurrentSession();
			Query q = session.createQuery(hql);
			if (param != null && param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					q.setParameter(i, param.get(i));
				}
			}
			logger.info( "【" + q.toString() + "】");
			Long num = (Long) q.uniqueResult();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public Integer executeHql(String hql) {
		try {
			Session session = this.getCurrentSession();
			logger.info( "【" + hql.toString() + "】");
			Integer num = session.createQuery(hql).executeUpdate();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public void clear() {
		this.getCurrentSession().clear();
	}

	@Override
	public Integer executeHql(String hql, Object[] param) {
		try {
			Session session = this.getCurrentSession();
			logger.info( "【" + hql.toString() + "】");
			Query q = session.createQuery(hql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
			}
			Integer num = q.executeUpdate();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public Integer executeHql(String hql, List<Object> param) {
		try {
			Session session = this.getCurrentSession();
			Query q = session.createQuery(hql);
			if (param != null && param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					q.setParameter(i, param.get(i));
				}
			}
			logger.info( "【" + q.toString() + "】");
			Integer num = q.executeUpdate();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/*
	 * public boolean executeProcedure(final String hql, final Object[] param) {
	 * try { this.getCurrentSession().doWork(new Work() {
	 * 
	 * @Override public void execute(Connection connection) throws SQLException
	 * { CallableStatement call = connection.prepareCall("{call " + hql+"}"); if
	 * (param != null && param.length > 0) { for (int i = 0; i < param.length;
	 * i++) { call.setObject(i + 1, param[i]); } } call.executeQuery();
	 * call.close(); } }); } catch (Exception e) { Logger.error(this.getClass(),
	 * e); return false; } return true; }
	 */

	@Override
	public List<T> executeProcedureForList(String hql,
			Map<Object, String> param, Map<String, Type> output, Class<?> c) {
		Session session = this.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"{call " + hql + "}");
		for (Iterator<Entry<String, Type>> iterator = output.entrySet()
				.iterator(); iterator.hasNext();) {
			Entry<String, Type> entry = iterator.next();
			query = query.addScalar(entry.getKey(), entry.getValue());
		}
		Query q = query.setResultTransformer(Transformers.aliasToBean(c));
		int paramIndex = 0;
		for (Iterator<Entry<Object, String>> iterator = param.entrySet()
				.iterator(); iterator.hasNext();) {
			Entry<Object, String> entry = iterator
					.next();
			Object key = entry.getKey();
			String value = entry.getValue();
			if (value != null) {
				if (value.equals("date")) {
					q.setDate(paramIndex, (java.util.Date) key);
				} else if (value.equals("time")) {
					q.setTime(paramIndex, (java.util.Date) key);
				} else {
					q.setParameter(paramIndex, key);
				}
			} else {
				q.setParameter(paramIndex, key);
			}
			paramIndex++;
		}
		logger.info( "【" + q.toString() + "】");
		List<T> list = q.list();
		session.flush();
		session.clear();
		return list;
	}

	@Override
	public List<T> executeProcedureForList(String hql, Object[] param,
			Map<String, Type> output, Class<?> c) {
		Session session = this.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"{call " + hql + "}");
		for (Iterator<Entry<String, Type>> iterator = output.entrySet()
				.iterator(); iterator.hasNext();) {
			Entry<String, Type> entry = iterator.next();
			query = query.addScalar(entry.getKey(), entry.getValue());
		}
		Query q = query.setResultTransformer(Transformers.aliasToBean(c));
		for (int i = 0; i < param.length; i++) {
			q.setParameter(i, param[i]);
		}
		logger.info( "【" + q.toString() + "】");
		List<T> list = q.list();
		session.flush();
		session.clear();
		return list;
	}

	@Override
	public int executeProcedure(String hql, Object[] param) {
		try {
			Session session=this.getCurrentSession();
			Query q = session.createSQLQuery("{call " + hql + "}");
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					if (param[i] instanceof java.util.Date) {
						q.setDate(i, (java.util.Date) param[i]);
					} else {
						q.setParameter(i, param[i]);
					}
				}
			}
			logger.info( "【" + q.toString() + "】");
			Integer num=q.executeUpdate();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return -1;
		}
	}

	@Override
	public List<T> queryBySql(String sql, Class<?> cls) {
		try {
			Session session=this.getCurrentSession();
			logger.info( "【" + sql.toString() + "】");
			List<Map> list = session.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.flush();
			session.clear();
			List<T> result = new ArrayList<T>();
			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					result.add((T)map2Object(list.get(i),cls));
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public List<T> queryBySql(String sql, int page, int rows, Class<?> cls) {
		try {
			Session session = this.getCurrentSession();
			logger.info( "【" + sql.toString() + "】");
			SQLQuery q = session.createSQLQuery(sql);
			List<Map> list = q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			session.flush();
			session.clear();
			List<T> result = new ArrayList<T>();
			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					result.add((T)map2Object(list.get(i),cls));
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public Integer excuteBySql(String sql) {
		try {
			Session session = this.getCurrentSession();
			logger.info( "【" + sql.toString() + "】");
			Integer num = session.createSQLQuery(sql).executeUpdate();
			session.flush();
			session.clear();
			return num;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return 0;
		}
	}

	@Override
	public void evict(T o) {
		evict(o);
	}

	@Override
	public boolean merge(T o) {
		return merge(o);
	}
	
	/***
	 * map 转换为 对象
	 * @param map 
	 * @param cls 
	 * @return
	 * @throws Exception
	 */
	private Object map2Object(Map<String, Object> map, Class<?> cls)
			throws Exception {
		Field[] fields = cls.getDeclaredFields();
		Object obj = cls.newInstance();
		for (Field field : fields) {
			Class<?> clsType = field.getType();
			String name = field.getName();
			if(name.equals("serialVersionUID")) {
				continue;
			}
			String strSet = "set" + name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
			Method methodSet = cls.getDeclaredMethod(strSet, clsType);
			if (map.containsKey(name)) {
				methodSet.invoke(obj, map.get(name));
			}
		}
		return obj;
	}
}
