package com.gzdata.config.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gzdata.core.model.SysRoles;
import com.gzdata.core.model.SysUsers;
import com.gzdata.core.model.SysUsersRoles;
import com.gzdata.core.qo.SysUsersRolesQo;
import com.gzdata.core.service.SysPermissionsService;
import com.gzdata.core.service.SysRolesService;
import com.gzdata.core.service.SysUsersRolesService;
import com.gzdata.core.service.SysUsersService;

/**
 * 描述：
 *
 * @author caojing
 * @create 2019-01-27-13:57
 */
public class CustomRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(CustomRealm.class);

	@Autowired
	private SysUsersService userService;
	@Autowired
	private SysUsersRolesService userRoleService;
	@Autowired
	private SysRolesService roleService;
	@Autowired
	private SysPermissionsService permissionsService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String account = (String) SecurityUtils.getSubject().getPrincipal();

		SysUsers user = userService.findUserByUserName(account);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> stringSet = getUserPermission(user.getId());
		
		info.setStringPermissions(stringSet);
		info.setRoles(getUserRoles(user.getId()));

		return info;
	}

	/**
	 * 身份认证方法
	 * <p>
	 * 获取即将需要认证的信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String userName = (String) authenticationToken.getPrincipal();
		String userPwd = new String((char[]) authenticationToken.getCredentials());
		
	    logger.info("shiro solve userPwd:{} realm userName: {}",userPwd,userName);
		
		SysUsers users = userService.findUserByUserName(userName);
		if(null==users){
			throw new UnknownAccountException();
		}
		String password = users.getPassword();
		logger.info("realm db password: {}",password);

		// 设置盐值
		ByteSource salt = ByteSource.Util.bytes(userName + "salt");
		String pwdHash = new SimpleHash("MD5", userPwd, salt, 2).toHex();

		if (null == userName) {
			throw new UnknownAccountException("用户名不正确");
		} else if (!(pwdHash).equals(password)) {
			throw new IncorrectCredentialsException("密码不正确");
		}

		// 通过关于盐值的构造器，将前端传入的密码在加密时再加入盐值
		return new SimpleAuthenticationInfo(userName, password,
				ByteSource.Util.bytes(userName + "salt"), getName());

	}

	/**
	 *
	 * 功能描述：得到用戶角色集合
	 *
	 * @param userId
	 * @return
	 *
	 * @author 张兵帅
	 *
	 * @since 2020年5月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	private Set<String> getUserRoles(int userId) {
		Set<String> rolesSet=new HashSet<String>();

		SysUsersRolesQo qo = new SysUsersRolesQo();
		qo.setUserId(userId);
		List<SysUsersRoles> userRoleList = userRoleService.findList(qo);

		if (userRoleList != null && !userRoleList.isEmpty()) {
			for (SysUsersRoles sysUsersRoles : userRoleList) {
				SysRoles roles = roleService.findById(sysUsersRoles.getRoleId());
				rolesSet.add(roles.getRole());
			}
		}

		return rolesSet;
	}

	/**
	 *
	 * 功能描述：获取当前用户权限列表
	 *
	 * @param userId
	 * @return
	 *
	 * @author 张兵帅
	 *
	 * @since 2020年5月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	private Set<String> getUserPermission(int userId) {
		// 权限列表
		Set<String>  permiList = new HashSet<String>();

		SysUsersRolesQo qo = new SysUsersRolesQo();
		qo.setUserId(userId);
		List<SysUsersRoles> userRoleList = userRoleService.findList(qo);

		if (userRoleList != null && !userRoleList.isEmpty()) {
			for (SysUsersRoles sysUsersRoles : userRoleList) {
				SysRoles roles = roleService.findById(sysUsersRoles.getRoleId());

				List<Integer> permissionIds = permissionsService.getCurrentUserPermiId(roles.getRole());
				if (permissionIds != null && !permissionIds.isEmpty()) {// 存在权限列表数据
					for (Integer id : permissionIds) {
						logger.info("permission: "+permissionsService.findById(id).getPermission());
						permiList.add(permissionsService.findById(id).getPermission());
					}
				}

			}

		}
		return permiList;
	}
}
