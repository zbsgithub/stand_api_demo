package com.gzdata.core.service;

//import io.netty.util.concurrent.Future;

import java.util.List;
import java.util.concurrent.Future;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzdata.common.db.mybatis.dao.BaseDAOInterface;
import com.gzdata.common.db.mybatis.service.AbstractBaseService;
import com.gzdata.common.util.DateUtil;
import com.gzdata.common.util.MD5Util;
import com.gzdata.core.dao.SysRolesDao;
import com.gzdata.core.dao.SysUsersDao;
import com.gzdata.core.dao.SysUsersRolesDao;
import com.gzdata.core.dto.SysUsersDto;
import com.gzdata.core.model.SysUsers;
import com.gzdata.core.model.SysUsersRoles;

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
public class SysUsersService extends AbstractBaseService<SysUsers> {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUsersService.class);

	@Autowired
	private SysUsersDao sysUsersDao;
	@Autowired
	private SysRolesDao roleDao;
	@Autowired
	private SysUsersRolesDao userRoleDao;

	@Override
	protected BaseDAOInterface<SysUsers> getDAO() {
		return sysUsersDao;
	}
	
	/**
	 * 
	 * 功能描述：按照名称查找用户信息
	 *
	 * @param userName
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年5月11日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public SysUsers findUserByUserName(String email){
		
		return sysUsersDao.findUserByUserName(email);
	}

	/**
	 * 
	 * 功能描述：查询该钉钉用户是否存在
	 *
	 * @param openId
	 * @param unionId
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年6月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public List<SysUsers> findUserByOpenIdAndUnionid(String openId,String unionId){
		
		return sysUsersDao.findUserByOpenIdAndUnionid(openId,unionId);
	}

	@Transactional
	public void createUserInfo(SysUsersDto dto,Integer userId) {
		SysUsers sysUsers = new SysUsers();
		BeanUtils.copyProperties(dto, sysUsers);

		sysUsers.setSalt(MD5Util.encodeBase64(dto.getPassword()));// pwd base64
		sysUsers.setCreateTime(DateUtil.getNow());
		sysUsers.setPassword(new SimpleHash("MD5", dto.getPassword(),
				ByteSource.Util.bytes(dto.getEmail() + "salt"), 2).toHex());
		sysUsers.setLocked(0);//0：未锁定 1.已锁定
		sysUsers.setBingState("未绑定");
		sysUsersDao.insert(sysUsers);

		// 查询角色id
		int roleId = roleDao.findIdByRole(dto.getRoleType());
        logger.info("createUserInfo roleId {}:",roleId);
        
		// 用户角色
		SysUsersRoles usersRoles = new SysUsersRoles();
		usersRoles.setUserId(sysUsers.getId());
		usersRoles.setRoleId(roleId);
		userRoleDao.insert(usersRoles);

		
	}
	
	// 角色权限
//	if (dto.getRoleType().equals("admin")) {
//
//		for (Entry<String, Object> entry : Constants.ADMIN_MAP.entrySet()) {
//			logger.info("Key = " + entry.getKey() + ", Value = "
//					+ entry.getValue());
//
//			SysRolesPermissions rolesPermissions = new SysRolesPermissions();
//			int permissionId = permissionService
//					.findPermisionIdByPermission(entry.getKey());
//			rolesPermissions.setRoleId(roleId);
//			rolesPermissions.setPermissionId(permissionId);
//			rolesPermissionService.insertSelective(rolesPermissions);
//		}
//	} else {// 普通用户
//		for (Entry<String, Object> entry : Constants.NORMAL_MAP.entrySet()) {
//			logger.info("Key = " + entry.getKey() + ", Value = "
//					+ entry.getValue());
//
//			int permissionId = permissionService
//					.findPermisionIdByPermission(entry.getKey());
//			SysRolesPermissions rolesPermissions = new SysRolesPermissions();
//			rolesPermissions.setRoleId(roleId);
//			rolesPermissions.setPermissionId(permissionId);
//			rolesPermissionService.insertSelective(rolesPermissions);
//		}
//	}

	@Async
	public Future<String> serviceThread5(String str){
		
		Future<String> future;
		try {
			Thread.sleep(10000);
			future = new AsyncResult<String>("success");
		} catch (InterruptedException e) {
			logger.error("serviceThread5 arise exception:",e);
			future = new AsyncResult<String>("error");
		}
		
		return future;
	}
	
	/*public static void main(String[] args) {
		System.out.println(new SimpleHash("MD5", "1q2w3e4r!T",ByteSource.Util.bytes("super_admin" + "salt"), 2).toHex().toString());
		System.out.println(MD5Util.encodeBase64("1q2w3e4r!T"));
	}*/
	
}