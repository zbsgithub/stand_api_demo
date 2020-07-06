                                            
package com.gzdata.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzdata.common.db.mybatis.dao.BaseDAOInterface;
import com.gzdata.common.db.mybatis.service.AbstractBaseService;
import com.gzdata.core.dao.SysPermissionsDao;
import com.gzdata.core.model.SysPermissions;

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
public class SysPermissionsService extends AbstractBaseService<SysPermissions> {

	@Autowired
	private SysPermissionsDao sysPermissionsDao;
	
	 
	@Override
	protected BaseDAOInterface<SysPermissions> getDAO() {
		return sysPermissionsDao;
	}
	
	public List<Integer> getCurrentUserPermiId(String roleName){
		return sysPermissionsDao.getCurrentUserPermiId(roleName);
	}

	public int findPermisionIdByPermission(String permission){
		return sysPermissionsDao.findPermisionIdByPermission(permission);
	}
}