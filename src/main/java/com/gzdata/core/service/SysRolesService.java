package com.gzdata.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzdata.common.db.mybatis.dao.BaseDAOInterface;
import com.gzdata.common.db.mybatis.service.AbstractBaseService;
import com.gzdata.core.dao.SysRolesDao;
import com.gzdata.core.model.SysRoles;

/**
 * 
 * 说明：处理对的业务操作
 * 
 * @author 张兵帅
 * 
 * @version 1.0
 * 
 * @since 2020年05月11日
 */
@Service
public class SysRolesService extends AbstractBaseService<SysRoles> {

	@Autowired
	private SysRolesDao sysRolesDao;

	@Override
	protected BaseDAOInterface<SysRoles> getDAO() {
		return sysRolesDao;
	}

	public int findIdByRole(String role){
		return sysRolesDao.findIdByRole(role);
	}
}