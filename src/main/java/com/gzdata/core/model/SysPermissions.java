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
public class SysPermissions  {


 							private Integer id; //编号


 							private String permission; //权限名称


 							private String description; //权限描述


 							private Integer rid; //此权限关联角色的id


 							private Integer available; //是否锁定




/** 以下为get,set方法 */
   		 						
        	
        	 public Integer getId() {
		        return this.id;
	        }
	        public void setId(Integer id) {
	        	this.id = id;
	        }
	

   		 						
        	
        	 public String getPermission() {
		        return this.permission;
	        }
	        public void setPermission(String permission) {
	        	this.permission = permission;
	        }
	

   		 						
        	
        	 public String getDescription() {
		        return this.description;
	        }
	        public void setDescription(String description) {
	        	this.description = description;
	        }
	

   		 						
        	
        	 public Integer getRid() {
		        return this.rid;
	        }
	        public void setRid(Integer rid) {
	        	this.rid = rid;
	        }
	

   		 						
        	
        	 public Integer getAvailable() {
		        return this.available;
	        }
	        public void setAvailable(Integer available) {
	        	this.available = available;
	        }
	





}
