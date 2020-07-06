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
import com.gzdata.core.model.WxAccessToken;

/**
 * 
 * 说明：wx_access_token对象的数据访问类
 * 
 * @author 张兵帅
 * 
 * @version 1.0
 * 
 * @since 2019年08月10日
 */
public interface WxAccessTokenDao extends BaseDAOInterface<WxAccessToken> {

	/**
	 * 
	 * 功能描述：保存
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Insert({ "insert into wx_access_token ( id,token,begin_time,exp_time,status,appid)  values (#{id,jdbcType=INTEGER},#{token,jdbcType=VARCHAR},#{beginTime,jdbcType=TIMESTAMP},#{expTime,jdbcType=TIMESTAMP},#{status,jdbcType=TINYINT},#{appid,jdbcType=VARCHAR})" })
	@Override
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(WxAccessToken entity);

	/**
	 * 
	 * 功能描述：选择字段保存
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Insert({ "<script>"
			+ "insert into wx_access_token "
			+ "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" > <if test=\"id != null\" > id, </if> <if test=\"token != null\" > token, </if> <if test=\"beginTime != null\" > begin_time, </if> <if test=\"expTime != null\" > exp_time, </if> <if test=\"status != null\" > status, </if> <if test=\"appid != null\" > appid, </if>  </trim> "
			+ " <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >  <if test=\"id != null\" > #{id,jdbcType=INTEGER}, </if> <if test=\"token != null\" > #{token,jdbcType=VARCHAR}, </if> <if test=\"beginTime != null\" > #{beginTime,jdbcType=TIMESTAMP}, </if> <if test=\"expTime != null\" > #{expTime,jdbcType=TIMESTAMP}, </if> <if test=\"status != null\" > #{status,jdbcType=TINYINT}, </if> <if test=\"appid != null\" > #{appid,jdbcType=VARCHAR}, </if> </trim>"
			+ "</script>" })
	@Override
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertSelective(WxAccessToken entity);

	/**
	 * 
	 * 功能描述：根据ID删除
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Delete({ "delete from wx_access_token where id = #{id,jdbcType=INTEGER}" })
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
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Delete({ "<script>"
			+ "delete from wx_access_token where id in "
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
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Update({ "update wx_access_token set id= #{id,jdbcType=INTEGER},token= #{token,jdbcType=VARCHAR},begin_time= #{beginTime,jdbcType=TIMESTAMP},exp_time= #{expTime,jdbcType=TIMESTAMP},status= #{status,jdbcType=TINYINT},appid= #{appid,jdbcType=VARCHAR} where id = #{id,jdbcType=INTEGER} " })
	@Override
	public void update(WxAccessToken entity);

	/**
	 * 
	 * 功能描述：选择字段更新
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Update({ "<script>"
			+ "update wx_access_token "
			+ "<set > <if test=\"id != null\" > id = #{id,jdbcType=INTEGER}, </if> <if test=\"token != null\" > token = #{token,jdbcType=VARCHAR}, </if> <if test=\"beginTime != null\" > begin_time = #{beginTime,jdbcType=TIMESTAMP}, </if> <if test=\"expTime != null\" > exp_time = #{expTime,jdbcType=TIMESTAMP}, </if> <if test=\"status != null\" > status = #{status,jdbcType=TINYINT}, </if> <if test=\"appid != null\" > appid = #{appid,jdbcType=VARCHAR}, </if>  </set> "
			+ "where id = #{id,jdbcType=INTEGER}" + "</script>" })
	@Override
	public void updateSelective(WxAccessToken entity);

	/**
	 * 
	 * 功能描述：查询所有
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "select * from wx_access_token" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
			@Result(column = "begin_time", property = "beginTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "exp_time", property = "expTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
			@Result(column = "appid", property = "appid", jdbcType = JdbcType.VARCHAR) })
	@Override
	public List<WxAccessToken> findAll();

	/**
	 * 
	 * 功能描述：查询总数
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "select count(id) from wx_access_token" })
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
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "select * from wx_access_token where id = #{id,jdbcType=INTEGER}" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
			@Result(column = "begin_time", property = "beginTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "exp_time", property = "expTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
			@Result(column = "appid", property = "appid", jdbcType = JdbcType.VARCHAR) })
	@Override
	public WxAccessToken findById(Serializable id);

	/**
	 * 
	 * 功能描述：根据查询对象查询
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "<script>"
			+ "select * from wx_access_token "
			+ "<where> 1 = 1 "
			+ "<if test=\"id != null\" > and id = #{id,jdbcType=INTEGER} </if><if test=\"token != null\" > and token = #{token,jdbcType=VARCHAR} </if><if test=\"beginTime != null\" > and begin_time = #{beginTime,jdbcType=TIMESTAMP} </if><if test=\"expTime != null\" > and exp_time = #{expTime,jdbcType=TIMESTAMP} </if><if test=\"status != null\" > and status = #{status,jdbcType=TINYINT} </if><if test=\"appid != null\" > and appid = #{appid,jdbcType=VARCHAR} </if> "
			+ "</where> order by id </script>" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
			@Result(column = "begin_time", property = "beginTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "exp_time", property = "expTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
			@Result(column = "appid", property = "appid", jdbcType = JdbcType.VARCHAR) })
	@Override
	public List<WxAccessToken> findList(QueryInterface query);

	/**
	 * 
	 * 功能描述：根据查询对象查询记录数
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "<script>"
			+ "select count(id) from wx_access_token "
			+ "<where> 1 = 1 "
			+ "<if test=\"id != null\" > and id = #{id,jdbcType=INTEGER} </if><if test=\"token != null\" > and token = #{token,jdbcType=VARCHAR} </if><if test=\"beginTime != null\" > and begin_time = #{beginTime,jdbcType=TIMESTAMP} </if><if test=\"expTime != null\" > and exp_time = #{expTime,jdbcType=TIMESTAMP} </if><if test=\"status != null\" > and status = #{status,jdbcType=TINYINT} </if><if test=\"appid != null\" > and appid = #{appid,jdbcType=VARCHAR} </if> "
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
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({ "<script>"
			+ "select * from wx_access_token "
			+ "<where> 1 = 1 "
			+ "<if test=\"id != null\" > and id = #{id,jdbcType=INTEGER} </if><if test=\"token != null\" > and token = #{token,jdbcType=VARCHAR} </if><if test=\"beginTime != null\" > and begin_time = #{beginTime,jdbcType=TIMESTAMP} </if><if test=\"expTime != null\" > and exp_time = #{expTime,jdbcType=TIMESTAMP} </if><if test=\"status != null\" > and status = #{status,jdbcType=TINYINT} </if><if test=\"appid != null\" > and appid = #{appid,jdbcType=VARCHAR} </if> "
			+ "</where> order by id "
			+ "<if test=\"pagination==1\" > limit #{first,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}</if>"
			+ "</script>" })
	@Results({
			@Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
			@Result(column = "begin_time", property = "beginTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "exp_time", property = "expTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
			@Result(column = "appid", property = "appid", jdbcType = JdbcType.VARCHAR) })
	@Override
	public List<WxAccessToken> findPaginationDataByCondition(
			QueryInterface query);
	
	
	/**
	 * 
	 * 功能描述：获取微信token值
	 *
	 * @param query
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2019年8月17日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Select({
		"<script>"
		+"select * from wx_access_token "
		+"<where> 1 = 1 "
		+ "<if test=\"expTime != null\" > and exp_time BETWEEN #{beginTime,jdbcType=TIMESTAMP} AND #{expTime,jdbcType=TIMESTAMP} </if>"
		+ "<if test=\"status != null\" > and status = #{status,jdbcType=TINYINT} </if> "
		+"<if test=\"appid != null\" > and appid = #{appid,jdbcType=VARCHAR} </if>"
		+"</where> ORDER BY exp_time DESC LIMIT 1"
		+"</script>" 
		})
	@Results({@Result(column = "id", property = "id" , jdbcType = JdbcType.VARCHAR ,id = true  ),
		@Result(column = "token", property = "token" , jdbcType = JdbcType.VARCHAR ),
		@Result(column = "begin_time", property = "beginTime" , jdbcType = JdbcType.TIMESTAMP ),
		@Result(column = "exp_time", property = "expTime" , jdbcType = JdbcType.TIMESTAMP ),
		@Result(column = "status", property = "status" , jdbcType = JdbcType.TINYINT ),
		@Result(column = "appid", property = "appid" , jdbcType = JdbcType.VARCHAR) })
	public List<WxAccessToken> findNotExpAccessToken(QueryInterface query);

}
