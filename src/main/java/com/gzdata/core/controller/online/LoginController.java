package com.gzdata.core.controller.online;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gzdata.common.compoent.SystemUtil;
import com.gzdata.common.db.mybatis.result.Result;
import com.gzdata.common.util.AESUtil;
import com.gzdata.common.util.DateUtil;
import com.gzdata.core.model.SysRoles;
import com.gzdata.core.model.SysUsers;
import com.gzdata.core.model.SysUsersRoles;
import com.gzdata.core.qo.SysUsersRolesQo;
import com.gzdata.core.service.SysPermissionsService;
import com.gzdata.core.service.SysRolesService;
import com.gzdata.core.service.SysUsersRolesService;
import com.gzdata.core.service.SysUsersService;

@RestController
@Api("登录控制器")
@RequestMapping("api")
public class LoginController {


	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SystemUtil systemUtil;
	@Autowired
	private SysUsersService userService;
	@Autowired
	private SysRolesService rolesService;
	@Autowired
	private SysUsersRolesService sysUsersRolesService;
	@Autowired
	private SysPermissionsService permissionsService;
	
	@Autowired
    private Environment env;
	

	/**
	 *
	 * 功能描述：默认登录
	 *
	 * @param username
	 * @param password
	 * @return
	 *
	 * @author 张兵帅
	 *
	 * @since 2020年5月11日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@ApiOperation("账号密码登录")
	@PostMapping(value = "/login")
	@ResponseBody
	public Result login(@RequestParam(name="account",value="account",required=true) String account,@RequestParam(name="password",value="password",required=true) String password) {
		
		logger.info("account decrypt: {}	password decrypt: {}",AESUtil.decrypt(account),AESUtil.decrypt(password));
		
		// 从SecurityUtils里边创建一个 subject
		Subject subject = SecurityUtils.getSubject();
		// 在认证提交前准备 token（令牌）
		UsernamePasswordToken token = new UsernamePasswordToken(AESUtil.decrypt(account),
				AESUtil.decrypt(password));
		// 执行认证登陆
		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			return Result.valueOf(Result.ERROR,"账号不存在，请检查后重试");
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			return Result.valueOf(Result.ERROR,"密码不正确");
		} catch (LockedAccountException lae) {
			return Result.valueOf(Result.ERROR,"账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			return Result.valueOf(Result.ERROR,"用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			return Result.valueOf(Result.ERROR,"用户名或密码不正确！");
		}
		Map<String, Object> resultMap = new HashMap<>();

		if (subject.isAuthenticated()) {
			SysUsers users = userService.findUserByUserName(AESUtil.decrypt(account));//account == email
			if(users.getLocked()==1){//账号已被锁定==已删除
				return Result.valueOf(Result.ERROR,"登录失败:账号不存在，请检查后重试");
			}
			
			subject.getSession().setTimeout(1800000);//失效时间：30分钟
			
			resultMap.put("x-auth-token", subject.getSession().getId());
			resultMap.put("userName", users.getUsername());//姓名
			
			resultMap.put("role",getCurrentRoles(users.getId()));
        	resultMap.put("permissions",getCurrentPermission(users.getId()));

			resultMap.put("bingState", users.getBingState());
			//判断是否是首次登录
			if(users.getLastLoginTime()!=null){
				resultMap.put("firstLogin", false);
			}else{
				resultMap.put("firstLogin", true);
				users.setLastLoginTime(DateUtil.getNow());//登录时间
				userService.updateSelective(users);
			}
			
			return Result.valueOf(Result.SUCCESS,"登录成功", resultMap);

		} else {
			token.clear();
			resultMap.put("x-auth-token", null);
			return Result.valueOf(Result.ERROR,"登录失败");
		}
	}

	


	private List<String> getCurrentRoles(int userId){
		List<String> roleList = new ArrayList<String>();
		
		SysUsersRolesQo qo =new SysUsersRolesQo();
		qo.setUserId(userId);
		List<SysUsersRoles> userRoleList = sysUsersRolesService.findList(qo);
		
		//角色权限列表
        if(userRoleList!=null && !userRoleList.isEmpty()){
        	
        	for (SysUsersRoles sysUsersRoles : userRoleList) {
				
        		SysRoles roles = rolesService.findById(sysUsersRoles.getRoleId());
        		roleList.add(roles.getRole());
        		
			}
        }
			
		return roleList;	
	}

	
	private List<String> getCurrentPermission(int userId){
		
		List<String> permissionList = new ArrayList<String>();
		
		SysUsersRolesQo qo =new SysUsersRolesQo();
		qo.setUserId(userId);
		List<SysUsersRoles> userRoleList = sysUsersRolesService.findList(qo);
		
		//角色权限列表
        if(userRoleList!=null && !userRoleList.isEmpty()){
        	
        	for (SysUsersRoles sysUsersRoles : userRoleList) {
				
        		SysRoles roles = rolesService.findById(sysUsersRoles.getRoleId());
        		
        		List<Integer> permissionIds = permissionsService.getCurrentUserPermiId(roles.getRole());
        		if(permissionIds!=null && !permissionIds.isEmpty()){//存在权限列表数据
        			for (Integer id : permissionIds) {
        				permissionList.add(permissionsService.findById(id).getPermission());
        			}
        		}
        		
			}
        }
        return permissionList;
	}
}
