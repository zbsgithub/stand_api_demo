package com.gzdata.common.db.mybatis.dao;

import java.io.Serializable;
import java.util.List;

import com.gzdata.common.db.mybatis.query.QueryInterface;





/**
 * 基础DAO ，提供通用增删改查方法
 *
 *
 */
public interface BaseDAOInterface<T> {

	// 添加对象
	public void insert(T entity);

	// 添加指定字段
	public void insertSelective(T entity);

	// 修改对象
	public void update(T entity);

	// 修改指定字段
	public void updateSelective(T entity);

	// 删除对象
	public void delete(T entity);

	// 通过ID 删除对象
	public void deleteByID(Serializable id);

	// 通过ID批量 删除对象
	public void batchDelete(Serializable... ids);

	// 查询所有
	public List<T> findAll();

	// 根据id 查询
	public T findById(Serializable id);

	// 查询列表
	public List<T> findList(QueryInterface query);

	// 查询记录总数
	public int findTotalCount();

	// 查询分页记录总数
	public int findTotalCountByCondition(QueryInterface query);

	// 有条件的分页数据
	public List<T> findPaginationDataByCondition(QueryInterface query);

	// 通过拦截器查询分页数据
	public abstract List<T> queryByPagination(QueryInterface query);

}
