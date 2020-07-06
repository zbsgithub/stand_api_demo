
                                            

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
import com.gzdata.core.model.SysRoles;

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
public interface SysRolesDao extends BaseDAOInterface<SysRoles> {

	 	
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
	@Insert({ "insert into sys_roles ( id,role,description,pid,available)  values (#{id,jdbcType=BIGINT},#{role,jdbcType=VARCHAR},#{description,jdbcType=VARCHAR},#{pid,jdbcType=BIGINT},#{available,jdbcType=TINYINT})" })
	@Override
	 	@Options(useGeneratedKeys = true, keyProperty = "id")
		public void insert(SysRoles entity);

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
			+"insert into sys_roles "
		    +"<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" > <if test=\"id != null\" > id, </if> <if test=\"role != null\" > role, </if> <if test=\"description != null\" > description, </if> <if test=\"pid != null\" > pid, </if> <if test=\"available != null\" > available, </if>  </trim> "
		    +" <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >  <if test=\"id != null\" > #{id,jdbcType=BIGINT}, </if> <if test=\"role != null\" > #{role,jdbcType=VARCHAR}, </if> <if test=\"description != null\" > #{description,jdbcType=VARCHAR}, </if> <if test=\"pid != null\" > #{pid,jdbcType=BIGINT}, </if> <if test=\"available != null\" > #{available,jdbcType=TINYINT}, </if> </trim>"
		    +"</script>" 
			})
	@Override
	 	@Options(useGeneratedKeys = true, keyProperty = "id")
		public void insertSelective(SysRoles entity);
	
	

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
	@Delete({ "delete from sys_roles where id = #{id,jdbcType=BIGINT}" })
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
			+"delete from sys_roles where id in "
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
	@Update({ "update sys_roles set id= #{id,jdbcType=BIGINT},role= #{role,jdbcType=VARCHAR},description= #{description,jdbcType=VARCHAR},pid= #{pid,jdbcType=BIGINT},available= #{available,jdbcType=TINYINT} where id = #{id,jdbcType=BIGINT} " })
	@Override
	public void update(SysRoles entity);
	
	

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
			+"update sys_roles "
			+"<set > <if test=\"id != null\" > id = #{id,jdbcType=BIGINT}, </if> <if test=\"role != null\" > role = #{role,jdbcType=VARCHAR}, </if> <if test=\"description != null\" > description = #{description,jdbcType=VARCHAR}, </if> <if test=\"pid != null\" > pid = #{pid,jdbcType=BIGINT}, </if> <if test=\"available != null\" > available = #{available,jdbcType=TINYINT}, </if>  </set> "
			+"where id = #{id,jdbcType=BIGINT}"
			+"</script>" 
			})
	@Override
	public void updateSelective(SysRoles entity);

	
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
	@Select({ "select * from sys_roles" })
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "role", property = "role" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "pid", property = "pid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public List<SysRoles> findAll();

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
	@Select({ "select count(id) from sys_roles" })
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
	@Select({ "select * from sys_roles where id = #{id,jdbcType=BIGINT}" })
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "role", property = "role" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "pid", property = "pid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public SysRoles findById(Serializable id);

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
			+"select * from sys_roles "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"role != null\" > and role = #{role,jdbcType=VARCHAR} </if><if test=\"description != null\" > and description = #{description,jdbcType=VARCHAR} </if><if test=\"pid != null\" > and pid = #{pid,jdbcType=BIGINT} </if><if test=\"available != null\" > and available = #{available,jdbcType=TINYINT} </if> "
			+"</where> order by id </script>" 
			})
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "role", property = "role" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "pid", property = "pid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public List<SysRoles> findList(QueryInterface query);

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
			+"select count(id) from sys_roles "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"role != null\" > and role = #{role,jdbcType=VARCHAR} </if><if test=\"description != null\" > and description = #{description,jdbcType=VARCHAR} </if><if test=\"pid != null\" > and pid = #{pid,jdbcType=BIGINT} </if><if test=\"available != null\" > and available = #{available,jdbcType=TINYINT} </if> "
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
			+"select * from sys_roles "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"role != null\" > and role = #{role,jdbcType=VARCHAR} </if><if test=\"description != null\" > and description = #{description,jdbcType=VARCHAR} </if><if test=\"pid != null\" > and pid = #{pid,jdbcType=BIGINT} </if><if test=\"available != null\" > and available = #{available,jdbcType=TINYINT} </if> "
			+"</where> order by id "
			+"<if test=\"pagination==1\" > limit #{first,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}</if>"
			+"</script>" 
			})
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "role", property = "role" , jdbcType = JdbcType.VARCHAR ),@Result(column = "description", property = "description" , jdbcType = JdbcType.VARCHAR ),@Result(column = "pid", property = "pid" , jdbcType = JdbcType.BIGINT ),@Result(column = "available", property = "available" , jdbcType = JdbcType.TINYINT ) })
	@Override
	public List<SysRoles> findPaginationDataByCondition(QueryInterface query);

	@Select(" SELECT r.`id` FROM sys_roles r WHERE r.`role` = #{role}; ")
	public int findIdByRole(@Param("role") String role);
}

