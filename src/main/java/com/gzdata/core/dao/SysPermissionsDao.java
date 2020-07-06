
                                            

package com.gzdata.core.dao;

import io.lettuce.core.dynamic.annotation.Param;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.gzdata.common.db.mybatis.dao.BaseDAOInterface;
import com.gzdata.common.db.mybatis.query.QueryInterface;
import com.gzdata.core.model.SysPermissions;

/**
 * 
 *  说明：对象的数据访问类
 * 
 * @author 张兵帅
 * 
 * @version 1.0
 * 
 * @since 2020年05月11日
 */
public interface SysPermissionsDao extends BaseDAOInterface<SysPermissions> {

	 	
	/**
	 * 
	 * 功能描述：保存
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Insert({ "insert into sys_permissions ( id,permission,description,rid,available)  values (#{id,jdbcType=BIGINT},#{permission,jdbcType=VARCHAR},#{description,jdbcType=VARCHAR},#{rid,jdbcType=BIGINT},#{available,jdbcType=TINYINT})" })
	@Override
	 	@Options(useGeneratedKeys = true, keyProperty = "id")
		public void insert(SysPermissions entity);

	/**
	 * 
	 * 功能描述：选择字段保存
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Insert({
			"<script>"
			+"insert into sys_permissions "
		    +"<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" > <if test=\"id != null\" > id, </if> <if test=\"permission != null\" > permission, </if> <if test=\"description != null\" > description, </if> <if test=\"rid != null\" > rid, </if> <if test=\"available != null\" > available, </if>  </trim> "
		    +" <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >  <if test=\"id != null\" > #{id,jdbcType=BIGINT}, </if> <if test=\"permission != null\" > #{permission,jdbcType=VARCHAR}, </if> <if test=\"description != null\" > #{description,jdbcType=VARCHAR}, </if> <if test=\"rid != null\" > #{rid,jdbcType=BIGINT}, </if> <if test=\"available != null\" > #{available,jdbcType=TINYINT}, </if> </trim>"
		    +"</script>" 
			})
	@Override
	 	@Options(useGeneratedKeys = true, keyProperty = "id")
		public void insertSelective(SysPermissions entity);
	
	

	/**
	 * 
	 * 功能描述：根据ID删除
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Delete({ "delete from sys_permissions where id = #{id,jdbcType=BIGINT}" })
	@Override
	public void deleteByID(Serializable id);

	/**
	 * 
	 * 功能描述：根据ID数组批量删除
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Delete({
			"<script>"
			+"delete from sys_permissions where id in "
			+"<foreach  item=\"id\"  collection=\"array\" open=\"(\" separator=\",\" close=\")\" > #{id} </foreach>"
			+"</script>" 
			})
	@Override
	public void batchDelete(Serializable... ids);

	/**
	 * 
	 * 功能描述：更新
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Update({ "update sys_permissions set id= #{id,jdbcType=BIGINT},permission= #{permission,jdbcType=VARCHAR},description= #{description,jdbcType=VARCHAR},rid= #{rid,jdbcType=BIGINT},available= #{available,jdbcType=TINYINT} where id = #{id,jdbcType=BIGINT} " })
	@Override
	public void update(SysPermissions entity);
	
	

	/**
	 * 
	 * 功能描述：选择字段更新
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Update({
			"<script>"
			+"update sys_permissions "
			+"<set > <if test=\"id != null\" > id = #{id,jdbcType=BIGINT}, </if> <if test=\"permission != null\" > permission = #{permission,jdbcType=VARCHAR}, </if> <if test=\"description != null\" > description = #{description,jdbcType=VARCHAR}, </if> <if test=\"rid != null\" > rid = #{rid,jdbcType=BIGINT}, </if> <if test=\"available != null\" > available = #{available,jdbcType=TINYINT}, </if>  </set> "
			+"where id = #{id,jdbcType=BIGINT}"
			+"</script>" 
			})
	@Override
	public void updateSelective(SysPermissions entity);

	
	/**
	 * 
	 * 功能描述：查询所有
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "select * from sys_permissions" })
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "permission", property = "permission" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "rid", property = "rid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public List<SysPermissions> findAll();

	/**
	 * 
	 * 功能描述：查询总数
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "select count(id) from sys_permissions" })
	@Override
	public int findTotalCount();

	/**
	 * 
	 * 功能描述：根据ID查询
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "select * from sys_permissions where id = #{id,jdbcType=BIGINT}" })
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "permission", property = "permission" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "rid", property = "rid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public SysPermissions findById(Serializable id);

	/**
	 * 
	 * 功能描述：根据查询对象查询
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({
			"<script>"
			+"select * from sys_permissions "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"permission != null\" > and permission = #{permission,jdbcType=VARCHAR} </if><if test=\"description != null\" > and description = #{description,jdbcType=VARCHAR} </if><if test=\"rid != null\" > and rid = #{rid,jdbcType=BIGINT} </if><if test=\"available != null\" > and available = #{available,jdbcType=TINYINT} </if> "
			+"</where> order by id </script>" 
			})
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "permission", property = "permission" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "rid", property = "rid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public List<SysPermissions> findList(QueryInterface query);

	/**
	 * 
	 * 功能描述：根据查询对象查询记录数
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({
			"<script>"
			+"select count(id) from sys_permissions "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"permission != null\" > and permission = #{permission,jdbcType=VARCHAR} </if><if test=\"description != null\" > and description = #{description,jdbcType=VARCHAR} </if><if test=\"rid != null\" > and rid = #{rid,jdbcType=BIGINT} </if><if test=\"available != null\" > and available = #{available,jdbcType=TINYINT} </if> "
			+"</where></script>" 
			})
	@Override
	public int findTotalCountByCondition(QueryInterface query);

	/**
	 * 
	 * 功能描述：根据查询对象查询分页记录
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({
			"<script>"
			+"select * from sys_permissions "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"permission != null\" > and permission = #{permission,jdbcType=VARCHAR} </if><if test=\"description != null\" > and description = #{description,jdbcType=VARCHAR} </if><if test=\"rid != null\" > and rid = #{rid,jdbcType=BIGINT} </if><if test=\"available != null\" > and available = #{available,jdbcType=TINYINT} </if> "
			+"</where> order by id "
			+"<if test=\"pagination==1\" > limit #{first,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}</if>"
			+"</script>" 
			})
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "permission", property = "permission" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "rid", property = "rid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public List<SysPermissions> findPaginationDataByCondition(QueryInterface query);
	
	/**
	 * 
	 * 功能描述：获取当前登录用户权限id集合
	 *
	 * @param roleName
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年5月12日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({"<script>"," SELECT rp.`permission_id` FROM sys_roles_permissions rp WHERE rp.`role_id` IN (SELECT role.`id` FROM sys_roles role WHERE role.`role` = #{roleName}) ","</script>"})
	public List<Integer> getCurrentUserPermiId(@Param("roleName") String roleName);
	
	@Select(" SELECT s.`id` FROM sys_permissions s WHERE s.`permission` = #{permission}; ")
	public int findPermisionIdByPermission(@Param("permission") String permission);

}

