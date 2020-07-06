
                            

package com.gzdata.core.dao;

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
import com.gzdata.core.model.SysUsersRoles;

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
public interface SysUsersRolesDao extends BaseDAOInterface<SysUsersRoles> {

	 	
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
	@Insert({ "insert into sys_users_roles ( id,user_id,role_id)  values (#{id,jdbcType=BIGINT},#{userId,jdbcType=BIGINT},#{roleId,jdbcType=BIGINT})" })
	@Override
	 	@Options(useGeneratedKeys = true, keyProperty = "id")
		public void insert(SysUsersRoles entity);

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
			+"insert into sys_users_roles "
		    +"<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" > <if test=\"id != null\" > id, </if> <if test=\"userId != null\" > user_id, </if> <if test=\"roleId != null\" > role_id, </if>  </trim> "
		    +" <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >  <if test=\"id != null\" > #{id,jdbcType=BIGINT}, </if> <if test=\"userId != null\" > #{userId,jdbcType=BIGINT}, </if> <if test=\"roleId != null\" > #{roleId,jdbcType=BIGINT}, </if> </trim>"
		    +"</script>" 
			})
	@Override
	 	@Options(useGeneratedKeys = true, keyProperty = "id")
		public void insertSelective(SysUsersRoles entity);
	
	

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
	@Delete({ "delete from sys_users_roles where id = #{id,jdbcType=BIGINT}" })
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
			+"delete from sys_users_roles where id in "
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
	@Update({ "update sys_users_roles set id= #{id,jdbcType=BIGINT},user_id= #{userId,jdbcType=BIGINT},role_id= #{roleId,jdbcType=BIGINT} where id = #{id,jdbcType=BIGINT} " })
	@Override
	public void update(SysUsersRoles entity);
	
	

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
			+"update sys_users_roles "
			+"<set > <if test=\"id != null\" > id = #{id,jdbcType=BIGINT}, </if> <if test=\"userId != null\" > user_id = #{userId,jdbcType=BIGINT}, </if> <if test=\"roleId != null\" > role_id = #{roleId,jdbcType=BIGINT}, </if>  </set> "
			+"where id = #{id,jdbcType=BIGINT}"
			+"</script>" 
			})
	@Override
	public void updateSelective(SysUsersRoles entity);

	
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
	@Select({ "select * from sys_users_roles" })
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "user_id", property = "userId" , jdbcType = JdbcType.BIGINT ),@Result(column = "role_id", property = "roleId" , jdbcType = JdbcType.BIGINT ) })
	@Override
	public List<SysUsersRoles> findAll();

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
	@Select({ "select count(id) from sys_users_roles" })
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
	@Select({ "select * from sys_users_roles where id = #{id,jdbcType=BIGINT}" })
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "user_id", property = "userId" , jdbcType = JdbcType.BIGINT ),@Result(column = "role_id", property = "roleId" , jdbcType = JdbcType.BIGINT ) })
	@Override
	public SysUsersRoles findById(Serializable id);

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
			+"select * from sys_users_roles "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"userId != null\" > and user_id = #{userId,jdbcType=BIGINT} </if><if test=\"roleId != null\" > and role_id = #{roleId,jdbcType=BIGINT} </if> "
			+"</where> order by id </script>" 
			})
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "user_id", property = "userId" , jdbcType = JdbcType.BIGINT ),@Result(column = "role_id", property = "roleId" , jdbcType = JdbcType.BIGINT ) })
	@Override
	public List<SysUsersRoles> findList(QueryInterface query);

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
			+"select count(id) from sys_users_roles "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"userId != null\" > and user_id = #{userId,jdbcType=BIGINT} </if><if test=\"roleId != null\" > and role_id = #{roleId,jdbcType=BIGINT} </if> "
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
			+"select * from sys_users_roles "
			+"<where> 1 = 1 "
			+"<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"userId != null\" > and user_id = #{userId,jdbcType=BIGINT} </if><if test=\"roleId != null\" > and role_id = #{roleId,jdbcType=BIGINT} </if> "
			+"</where> order by id "
			+"<if test=\"pagination==1\" > limit #{first,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}</if>"
			+"</script>" 
			})
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.BIGINT ,id = true  ),@Result(column = "user_id", property = "userId" , jdbcType = JdbcType.BIGINT ),@Result(column = "role_id", property = "roleId" , jdbcType = JdbcType.BIGINT ) })
	@Override
	public List<SysUsersRoles> findPaginationDataByCondition(QueryInterface query);

}

