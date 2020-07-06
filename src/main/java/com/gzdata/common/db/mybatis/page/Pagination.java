package com.gzdata.common.db.mybatis.page;

import com.gzdata.common.db.mybatis.query.QueryInterface;

/**
 * 封装分页基础类
 * 
 *
 * @author 张兵帅
 *
 * @version
 *
 * @since 2018年4月4日
 */
public class Pagination<T> implements QueryInterface {

	/**
	 * 分页信息
	 */
	private int page;// 第几页

	@SuppressWarnings("unused")
	private int first;//数据起点

	private int pageSize;// 每页多少条
	// 临时加的
	private int pagination = 1; // 是否分页

	private Object data;// 结果集

	public int getPagination() {
		return pagination;
	}

	public void setPagination(int pagination) {
		this.pagination = pagination;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getFirst() {
		if (page == 0) {
			return 0;
		} else {
			return (page - 1) * pageSize;
		}

	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
