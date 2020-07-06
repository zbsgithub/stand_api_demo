package com.gzdata.core.dto;


                                                                



/**
 * 
 * 
 * 
 * @author 张兵帅
 * 
 * @version 1.0
 * 
 * @since 2020年05月11日
 */
public class SysRolesDto{


						private Integer id; //角色编号

						private String role; //角色名称

						private String description; //角色描述

						private Integer pid; //父节点

						private Integer available; //是否锁定


/** 以下为get,set方法 */
   		 						
        	
        	 public Integer getId() {
		        return this.id;
	        }
	        public void setId(Integer id) {
	        	this.id = id;
	        }
	

   		 						
        	
        	 public String getRole() {
		        return this.role;
	        }
	        public void setRole(String role) {
	        	this.role = role;
	        }
	

   		 						
        	
        	 public String getDescription() {
		        return this.description;
	        }
	        public void setDescription(String description) {
	        	this.description = description;
	        }
	

   		 						
        	
        	 public Integer getPid() {
		        return this.pid;
	        }
	        public void setPid(Integer pid) {
	        	this.pid = pid;
	        }
	

   		 						
        	
        	 public Integer getAvailable() {
		        return this.available;
	        }
	        public void setAvailable(Integer available) {
	        	this.available = available;
	        }
	





}
