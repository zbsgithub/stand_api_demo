package com.gzdata.common.db.mybatis.service;

import java.io.Serializable;
import java.util.List;

import com.gzdata.common.db.mybatis.dao.BaseDAOInterface;
import com.gzdata.common.db.mybatis.page.Pagination;
import com.gzdata.common.db.mybatis.query.QueryInterface;





/**
 *
 * 基础Service
 *
 * @param <T>
 */
public abstract class AbstractBaseService<T> {

	// 子类定义自己的DAO
	protected abstract BaseDAOInterface<T> getDAO();

	// 添加对象
	public void insert(T entity) {

		getDAO().insert(entity);

	}

	// 添加指定字段
	public void insertSelective(T entity) {

		getDAO().insertSelective(entity);

	}

	// 更新对象
	public void update(T entity) {

		getDAO().update(entity);

	}

	// 更新指定字段
	public void updateSelective(T entity) {

		getDAO().updateSelective(entity);

	}

	// 删除对象
	public void delete(T entity) {

		getDAO().updateSelective(entity);

	}

	// 通过ID删除对象
	public void deleteByID(Serializable id) {

		getDAO().deleteByID(id);

	}

	// 通过ID数组批量删除对象
	public void batchDelete(Serializable... ids) {

		getDAO().batchDelete(ids);

	}

	// 查询所有
	public List<T> findAll() {

		return getDAO().findAll();
	}

	// 通过ID查询对象
	public T findById(Serializable id) {

		return getDAO().findById(id);

	}

	// 通过条件查询对象
	public List<T> findList(QueryInterface qo) {

		return getDAO().findList(qo);

	}

	// 查询总记录数
	public int findTotalCount() {

		return getDAO().findTotalCount();

	}

	// 通过查询条件查询总数
	public int findTotalCountByCondition(QueryInterface query) {

		return getDAO().findTotalCountByCondition(query);

	}

	// 通过查询条件查询分页数据
	public List<T> findPaginationDataByCondition(
			QueryInterface query) {

		List<T> dataList = getDAO().findPaginationDataByCondition(query);
//		Pagination<T> resultPage = new Pagination<T>();

		/*resultPage.setOrderList(query.getOrderList());
		resultPage.setPageNO(query.getPageNO());
		resultPage.setPageSize(query.getPageSize());
		resultPage.setTotal(findTotalCountByCondition(query));
		resultPage.setDataList(dataList);*/
//		resultPage.setData(dataList);

		return dataList;
	}

	// 通过拦截器查询分页数据
/*	public PaginationInterface queryByPagination(QueryInterface query) {

		@SuppressWarnings("unchecked")
		Pagination<T> pagination = (Pagination<T>) query;

		List<T> dataList = getDAO().queryByPagination(pagination);
		Pagination<T> resultPage = new Pagination<T>();

		resultPage.setOrderList(pagination.getOrderList());
		resultPage.setPageNO(pagination.getPageNO());
		resultPage.setPageSize(pagination.getPageSize());
		resultPage.setTotal(pagination.getTotal());
		resultPage.setDataList(dataList);

		return resultPage;

	}*/

}
