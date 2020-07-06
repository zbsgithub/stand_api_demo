package com.gzdata.core.model;


                                        



/**
 * 
 * 说明：  值对象类
 * 
 * @author 张兵帅
 * 
 * @version 1.0
 * 
 * @since 2020年05月11日
 */
public class SysRolesPermissions  {


 							private Integer id; //编号


 							private Integer roleId; //角色编号


 							private Integer permissionId; //权限编号




/** 以下为get,set方法 */
   		 						
        	
        	 public Integer getId() {
		        return this.id;
	        }
	        public void setId(Integer id) {
	        	this.id = id;
	        }
	

   		 						
        	
        	 public Integer getRoleId() {
		        return this.roleId;
	        }
	        public void setRoleId(Integer roleId) {
	        	this.roleId = roleId;
	        }
	

   		 						
        	
        	 public Integer getPermissionId() {
		        return this.permissionId;
	        }
	        public void setPermissionId(Integer permissionId) {
	        	this.permissionId = permissionId;
	        }
	





}
