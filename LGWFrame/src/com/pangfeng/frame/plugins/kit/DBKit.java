package com.pangfeng.frame.plugins.kit;

import java.util.List;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

public class DBKit  {
	public static DbManager mDBClient;



	/**
	 * 插入单个对象
	 * 
	 * @param entity
	 *            实体类的对象
	 * @return true:插入成功 false:插入失败
	 */
	public final static synchronized boolean save(Object entity) {
		try {
			mDBClient.save(entity);
		} catch (DbException e) {
			if (e != null)
				e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 插入全部对象
	 * 
	 * @param entity
	 *            实体类的对象
	 * @return true:插入成功 false:插入失败
	 */
	public final static synchronized boolean saveAll(List<?> entity) {
		try {
			mDBClient.save(entity);
		} catch (DbException e) {
			if (e != null)
				e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除这个表中的所有数据
	 * 
	 * @param entity
	 *            实体类的对象
	 * @return true:成功 false:失败
	 */
	public final static synchronized boolean delete(Object entity) {
		try {
			mDBClient.delete(entity);
		} catch (Exception e) {
			if (e != null)
				e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据条件删除表
	 * 
	 * @param entity
	 *            表名称
	 * @param colun
	 *            列名
	 * @param value
	 *            值
	 * @return true:成功 false:失败
	 */
	public final static synchronized boolean deleteCriteria(Class<?> entity,
			String colun, String value) {
		try {
			mDBClient.delete(entity, WhereBuilder.b(colun, "=", value));
		} catch (Exception e) {
			if (e != null)
				e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 更新这张表中的所有数据
	 * 
	 * @param entity
	 *            实体类的对象
	 * @return true:更新成功 false:更新失败
	 */
	public final static synchronized boolean update(Object entity) {
		try {
			mDBClient.saveOrUpdate(entity);// 先去查这个条数据 根据id来判断是存储还是更新 如果存在更新
		} catch (Exception e) {
			if (e != null)
				e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据参数更新表中的数据
	 * 
	 * @param entity
	 *            实体类的对象
	 * @param value
	 *            要更新的字段
	 * @return true:更新成功 false:更新失败
	 */
	public final static synchronized boolean update(Object entity, String... value) {
		try {
			mDBClient.update(entity, value);
		} catch (Exception e) {
			if (e != null)
				e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 查找 根据id
	 * 
	 * @param cla
	 *            要查询的类
	 * @param id
	 *            要查询的id
	 * @return 查询到的数据
	 */
	public final static synchronized <T> Object searchOne(Class<T> cla, int id) {
		try {
			// return
			// mDBClient.findFirst(mDBClient.selector(cla).where(WhereBuilder.b("id",
			// "=", id)));
			return mDBClient.selector(cla).where(WhereBuilder.b("id", "=", id))
					.findFirst();
		} catch (Exception e) {
			if (e != null)
				e.printStackTrace();
		}
		return null;
	}

	/**
	 * 正序查找 没有条件的
	 * 
	 * @param entity
	 *            要查询的类
	 * @param <T>
	 *            要查询的类
	 * @return 查询到的数据
	 */
	public final static synchronized <T> List<T> search(Class<T> entity) {
		try {
			// return mDBClient.findAll(Selector.from(entity));
			return mDBClient.selector(entity).findAll();
		} catch (Exception e) {
			if (e != null)
				e.printStackTrace();
		}
		return null;
	}

	/**
	 * 倒序查找所有数据 没有条件的
	 * 
	 * @param entityClass
	 * @return 返回数据库中所有的表数据
	 */
	public final static synchronized <T> List<T> searchDesc(Class<T> entityClass) {
		try {
			// return
			// mDBClient.findAll(Selector.from(entityClass).orderBy("id",true));
			return mDBClient.selector(entityClass).orderBy("id", true)
					.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 倒序查找所有数据 没有条件的
	 * 
	 * @param entityClass
	 *            实体类
	 * @param column
	 *            定义的查询条件
	 * @param value
	 *            定义的查询值
	 * @return 返回数据库中所有的表数据
	 */
	public final static synchronized <T> List<T> searchCriteria(Class<T> entityClass,
			String column, String value) {
		try {
			return mDBClient.selector(entityClass)
					.where(WhereBuilder.b(column, "=", value)).findAll();

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param entityClass
	 *            实体类
	 * @param column
	 *            定义的查询条件
	 * @param value
	 *            定义的查询值
	 * @return 返回数据库中所有的表数据
	 */
	public final static synchronized <T> List<T> searchCriteriaForSlw(
			Class<T> entityClass, String column1, String value1,
			String column2, String value2) {
		try {
			return mDBClient.selector(entityClass)
					.where(WhereBuilder.b(column1, "=", value1))
					.and(column2, "=", value2).findAll();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 删除表格
	 * 
	 * @param entityClass
	 *            实体类
	 * @return 返回数据库中所有的表数据
	 */
	public final static synchronized <T> boolean drop(Class<T> entityClass) {
		try {
			mDBClient.dropTable(entityClass);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
