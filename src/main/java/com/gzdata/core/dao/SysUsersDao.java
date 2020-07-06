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
import com.gzdata.core.model.SysUsers;

/**
 *
 * 说明：对象的数据访问类
 *
 * @author 张兵帅
 *
 * @version 1.0
 *
 * @since 2020年05月11日
 */
public interface SysUsersDao extends BaseDAOInterface<SysUsers> {

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
	@Insert({ "insert into sys_users ( id,username,password,salt,locked,email,phone,department,group_type,role_type,nick,openid,unionid,bing_state,last_login_time,create_time)  values (#{id,jdbcType=BIGINT},#{username,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},#{salt,jdbcType=VARCHAR},#{locked,jdbcType=TINYINT},#{email,jdbcType=VARBINARY},#{phone,jdbcType=VARCHAR},#{department,jdbcType=VARCHAR},#{groupType,jdbcType=VARCHAR},#{roleType,jdbcType=VARCHAR},#{nick,jdbcType=VARCHAR},#{openid,jdbcType=VARCHAR},#{unionid,jdbcType=VARCHAR},#{bingState,jdbcType=VARCHAR},#{lastLoginTime,jdbcType=TIMESTAMP},#{createTime,jdbcType=TIMESTAMP})" })
	@Override
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(SysUsers entity);

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
	@Insert({ "<script>"
			+ "insert into sys_users "
			+ "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" > <if test=\"id != null\" > id, </if> <if test=\"username != null\" > username, </if> <if test=\"password != null\" > password, </if> <if test=\"salt != null\" > salt, </if> <if test=\"locked != null\" > locked, </if> <if test=\"email != null\" > email, </if> <if test=\"phone != null\" > phone, </if> <if test=\"department != null\" > department, </if> <if test=\"groupType != null\" > group_type, </if> <if test=\"roleType != null\" > role_type, </if> <if test=\"nick != null\" > nick, </if> <if test=\"openid != null\" > openid, </if> <if test=\"unionid != null\" > unionid, </if> <if test=\"bingState != null\" > bing_state, </if> <if test=\"lastLoginTime != null\" > last_login_time, </if> <if test=\"createTime != null\" > create_time, </if> </trim> "
			+ " <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >  <if test=\"id != null\" > #{id,jdbcType=BIGINT}, </if> <if test=\"username != null\" > #{username,jdbcType=VARCHAR}, </if> <if test=\"password != null\" > #{password,jdbcType=VARCHAR}, </if> <if test=\"salt != null\" > #{salt,jdbcType=VARCHAR}, </if> <if test=\"locked != null\" > #{locked,jdbcType=TINYINT}, </if> <if test=\"email != null\" > #{email,jdbcType=VARBINARY}, </if> <if test=\"phone != null\" > #{phone,jdbcType=VARCHAR}, </if> <if test=\"department != null\" > #{department,jdbcType=VARCHAR}, </if> <if test=\"groupType != null\" > #{groupType,jdbcType=VARCHAR}, </if> <if test=\"roleType != null\" > #{roleType,jdbcType=VARCHAR}, </if> <if test=\"nick != null\" > #{nick,jdbcType=VARCHAR}, </if> <if test=\"openid != null\" > #{openid,jdbcType=VARCHAR}, </if> <if test=\"unionid != null\" > #{unionid,jdbcType=VARCHAR}, </if> <if test=\"bingState != null\" > #{bingState,jdbcType=VARCHAR}, </if> <if test=\"lastLoginTime != null\" > #{lastLoginTime,jdbcType=TIMESTAMP}, </if> <if test=\"createTime != null\" > #{createTime,jdbcType=TIMESTAMP}, </if> </trim>"
			+ "</script>" })
	@Override
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertSelective(SysUsers entity);

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
	@Delete({ "delete from sys_users where id = #{id,jdbcType=BIGINT}" })
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
	@Delete({ "<script>"
			+ "delete from sys_users where id in "
			+ "<foreach  item=\"id\"  collection=\"array\" open=\"(\" separator=\",\" close=\")\" > #{id} </foreach>"
			+ "</script>" })
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
	@Update({ "update sys_users set id= #{id,jdbcType=BIGINT},username= #{username,jdbcType=VARCHAR},password= #{password,jdbcType=VARCHAR},salt= #{salt,jdbcType=VARCHAR},locked= #{locked,jdbcType=TINYINT},email= #{email,jdbcType=VARBINARY},phone= #{phone,jdbcType=VARCHAR},department= #{department,jdbcType=VARCHAR},group_type= #{groupType,jdbcType=VARCHAR},role_type= #{roleType,jdbcType=VARCHAR},nick= #{nick,jdbcType=VARCHAR},openid= #{openid,jdbcType=VARCHAR},unionid= #{unionid,jdbcType=VARCHAR},bing_state= #{bingState,jdbcType=VARCHAR},last_login_time=#{lastLoginTime,jdbcType=TIMESTAMP},create_time=#{createTime,jdbcType=TIMESTAMP} where id = #{id,jdbcType=BIGINT} " })
	@Override
	public void update(SysUsers entity);

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
	@Update({ "<script>"
			+ "update sys_users "
			+ "<set > <if test=\"id != null\" > id = #{id,jdbcType=BIGINT}, </if> <if test=\"username != null\" > username = #{username,jdbcType=VARCHAR}, </if> <if test=\"password != null\" > password = #{password,jdbcType=VARCHAR}, </if> <if test=\"salt != null\" > salt = #{salt,jdbcType=VARCHAR}, </if> <if test=\"locked != null\" > locked = #{locked,jdbcType=TINYINT}, </if> <if test=\"email != null\" > email = #{email,jdbcType=VARBINARY}, </if> <if test=\"phone != null\" > phone = #{phone,jdbcType=VARCHAR}, </if> <if test=\"department != null\" > department = #{department,jdbcType=VARCHAR}, </if> <if test=\"groupType != null\" > group_type = #{groupType,jdbcType=VARCHAR}, </if> <if test=\"roleType != null\" > role_type = #{roleType,jdbcType=VARCHAR}, </if> <if test=\"nick != null\" > nick = #{nick,jdbcType=VARCHAR}, </if> <if test=\"openid != null\" > openid = #{openid,jdbcType=VARCHAR}, </if> <if test=\"unionid != null\" > unionid = #{unionid,jdbcType=VARCHAR}, </if> <if test=\"bingState != null\" > bing_state = #{bingState,jdbcType=VARCHAR}, </if>  <if test=\"lastLoginTime != null\" > last_login_time = #{lastLoginTime,jdbcType=TIMESTAMP}, </if>  <if test=\"createTime != null\" > create_time = #{createTime,jdbcType=TIMESTAMP}, </if>  </set> "
			+ "where id = #{id,jdbcType=BIGINT}" + "</script>" })
	@Override
	public void updateSelective(SysUsers entity);

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
	@Select({ "select * from sys_users" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
			@Result(column = "locked", property = "locked", jdbcType = JdbcType.TINYINT),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARBINARY),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_type", property = "groupType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_type", property = "roleType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nick", property = "nick", jdbcType = JdbcType.VARCHAR),
			@Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "unionid", property = "unionid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bing_state", property = "bingState", jdbcType = JdbcType.VARCHAR) })
	@Override
	public List<SysUsers> findAll();

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
	@Select({ "select count(id) from sys_users" })
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
	@Select({ "select * from sys_users where id = #{id,jdbcType=BIGINT}" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
			@Result(column = "locked", property = "locked", jdbcType = JdbcType.TINYINT),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARBINARY),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_type", property = "groupType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_type", property = "roleType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nick", property = "nick", jdbcType = JdbcType.VARCHAR),
			@Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "unionid", property = "unionid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bing_state", property = "bingState", jdbcType = JdbcType.VARCHAR) })
	@Override
	public SysUsers findById(Serializable id);

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
	@Select({ "<script>"
			+ "select * from sys_users "
			+ "<where> 1 = 1 "
			+ "<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"username != null\" > and username = #{username,jdbcType=VARCHAR} </if><if test=\"password != null\" > and password = #{password,jdbcType=VARCHAR} </if><if test=\"salt != null\" > and salt = #{salt,jdbcType=VARCHAR} </if><if test=\"locked != null\" > and locked = #{locked,jdbcType=TINYINT} </if><if test=\"email != null\" > and email = #{email,jdbcType=VARBINARY} </if><if test=\"phone != null\" > and phone = #{phone,jdbcType=VARCHAR} </if><if test=\"department != null\" > and department = #{department,jdbcType=VARCHAR} </if><if test=\"groupType != null\" > and group_type = #{groupType,jdbcType=VARCHAR} </if><if test=\"roleType != null\" > and role_type = #{roleType,jdbcType=VARCHAR} </if><if test=\"nick != null\" > and nick = #{nick,jdbcType=VARCHAR} </if><if test=\"openid != null\" > and openid = #{openid,jdbcType=VARCHAR} </if><if test=\"unionid != null\" > and unionid = #{unionid,jdbcType=VARCHAR} </if><if test=\"bingState != null\" > and bing_state = #{bingState,jdbcType=VARCHAR} </if> "
			+ "</where> order by id </script>" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
			@Result(column = "locked", property = "locked", jdbcType = JdbcType.TINYINT),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARBINARY),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_type", property = "groupType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_type", property = "roleType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nick", property = "nick", jdbcType = JdbcType.VARCHAR),
			@Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "unionid", property = "unionid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bing_state", property = "bingState", jdbcType = JdbcType.VARCHAR) })
	@Override
	public List<SysUsers> findList(QueryInterface query);

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
	@Select({ "<script>"
			+ "select count(id) from sys_users "
			+ "<where> 1 = 1 "
			+ "<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"username != null\" > and username = #{username,jdbcType=VARCHAR} </if><if test=\"password != null\" > and password = #{password,jdbcType=VARCHAR} </if><if test=\"salt != null\" > and salt = #{salt,jdbcType=VARCHAR} </if><if test=\"locked != null\" > and locked = #{locked,jdbcType=TINYINT} </if><if test=\"email != null\" > and email = #{email,jdbcType=VARBINARY} </if><if test=\"phone != null\" > and phone = #{phone,jdbcType=VARCHAR} </if><if test=\"department != null\" > and department = #{department,jdbcType=VARCHAR} </if><if test=\"groupType != null\" > and group_type = #{groupType,jdbcType=VARCHAR} </if><if test=\"roleType != null\" > and role_type = #{roleType,jdbcType=VARCHAR} </if><if test=\"nick != null\" > and nick = #{nick,jdbcType=VARCHAR} </if><if test=\"openid != null\" > and openid = #{openid,jdbcType=VARCHAR} </if><if test=\"unionid != null\" > and unionid = #{unionid,jdbcType=VARCHAR} </if><if test=\"bingState != null\" > and bing_state = #{bingState,jdbcType=VARCHAR} </if> "
			+ "</where></script>" })
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
	@Select({ "<script>"
			+ "select * from sys_users "
			+ "<where> 1 = 1 "
			+ "<if test=\"id != null\" > and id = #{id,jdbcType=BIGINT} </if><if test=\"username != null\" > and username = #{username,jdbcType=VARCHAR} </if><if test=\"password != null\" > and password = #{password,jdbcType=VARCHAR} </if><if test=\"salt != null\" > and salt = #{salt,jdbcType=VARCHAR} </if><if test=\"locked != null\" > and locked = #{locked,jdbcType=TINYINT} </if><if test=\"email != null\" > and email = #{email,jdbcType=VARBINARY} </if><if test=\"phone != null\" > and phone = #{phone,jdbcType=VARCHAR} </if><if test=\"department != null\" > and department = #{department,jdbcType=VARCHAR} </if><if test=\"groupType != null\" > and group_type = #{groupType,jdbcType=VARCHAR} </if><if test=\"roleType != null\" > and role_type = #{roleType,jdbcType=VARCHAR} </if><if test=\"nick != null\" > and nick = #{nick,jdbcType=VARCHAR} </if><if test=\"openid != null\" > and openid = #{openid,jdbcType=VARCHAR} </if><if test=\"unionid != null\" > and unionid = #{unionid,jdbcType=VARCHAR} </if><if test=\"bingState != null\" > and bing_state = #{bingState,jdbcType=VARCHAR} </if> "
			+ "</where> order by id "
			+ "<if test=\"pagination==1\" > limit #{first,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}</if>"
			+ "</script>" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
			@Result(column = "locked", property = "locked", jdbcType = JdbcType.TINYINT),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARBINARY),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_type", property = "groupType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_type", property = "roleType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nick", property = "nick", jdbcType = JdbcType.VARCHAR),
			@Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "unionid", property = "unionid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bing_state", property = "bingState", jdbcType = JdbcType.VARCHAR) })
	@Override
	public List<SysUsers> findPaginationDataByCondition(QueryInterface query);

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
	@Select("SELECT * FROM sys_users s WHERE s.`locked` = 0 AND s.`email` = #{email};")
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
			@Result(column = "locked", property = "locked", jdbcType = JdbcType.TINYINT),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARBINARY),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_type", property = "groupType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_type", property = "roleType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nick", property = "nick", jdbcType = JdbcType.VARCHAR),
			@Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "unionid", property = "unionid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bing_state", property = "bingState", jdbcType = JdbcType.VARCHAR),
			@Result(column = "last_login_time", property = "lastLoginTime", jdbcType = JdbcType.TIMESTAMP)
			})
	public SysUsers findUserByUserName(@Param("email") String email);

	
	/**
	 * 
	 * 功能描述：按openid和unionid查询用户是否存在
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
	@Select("SELECT * FROM sys_users s WHERE s.`locked` = 0 AND s.openid = #{openId} and s.unionid = #{unionId};")
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
			@Result(column = "locked", property = "locked", jdbcType = JdbcType.TINYINT),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARBINARY),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "department", property = "department", jdbcType = JdbcType.VARCHAR),
			@Result(column = "group_type", property = "groupType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_type", property = "roleType", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nick", property = "nick", jdbcType = JdbcType.VARCHAR),
			@Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "unionid", property = "unionid", jdbcType = JdbcType.VARCHAR),
			@Result(column = "bing_state", property = "bingState", jdbcType = JdbcType.VARCHAR),
			@Result(column = "last_login_time", property = "lastLoginTime", jdbcType = JdbcType.TIMESTAMP)
			})
	public List<SysUsers> findUserByOpenIdAndUnionid(String openId,String unionId);

}
