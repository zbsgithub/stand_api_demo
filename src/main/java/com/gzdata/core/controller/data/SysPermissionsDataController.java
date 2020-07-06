package com.gzdata.core.controller.data;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzdata.common.db.mybatis.result.Result;
import com.gzdata.core.dto.SysPermissionsDto;
import com.gzdata.core.model.SysPermissions;
import com.gzdata.core.qo.SysPermissionsQo;
import com.gzdata.core.service.SysPermissionsService;

/**
 * 
 * SysPermissions信息控制器（管理员）
 *
 * @author 张兵帅
 *
 * @version
 *
 * @since 2020年05月11日
 */
@Controller
public class SysPermissionsDataController {

	@Autowired
	private  SysPermissionsService sysPermissionsService;
	
	/**
	 * 
	 * 功能描述：列表--普通分页
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/sys/permissions/pagelist")
	@ResponseBody
	public Result pagelist(@RequestBody SysPermissionsQo qo) {
		return Result.valueOf(Result.SUCCESS, "操作成功", sysPermissionsService.findPaginationDataByCondition(qo));
	}
	
	/**
	 * 
	 * 功能描述：列表--通过条件查询
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2020年05月11日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/sys/permissions/list")
	@ResponseBody
	public Result list(@RequestBody SysPermissionsQo qo) {
		return Result.valueOf(Result.SUCCESS, "操作成功", sysPermissionsService.findList(qo));
	}

	

	/**
	 * 
	 * 功能描述：详情
	 *
	 * @param id
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2015年10月30日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/sys/permissions/detail")
	@ResponseBody
	public Result detail(@RequestParam String id) {

		return Result.valueOf(Result.SUCCESS, "操作成功", sysPermissionsService.findById(id));
	}
	
	/**
	 * 
	 * 功能描述：批量删除
	 *
	 * @param id
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2015年10月30日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/sys/permissions/batch_delete")
	@ResponseBody
	public Result batchDelete(@RequestParam Serializable... ids) {

	 	 sysPermissionsService.batchDelete(ids);
			

		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

	/**
	 * 
	 * 功能描述：删除
	 *
	 * @param id
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2015年10月30日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/sys/permissions/delete")
	@ResponseBody
	public Result delete(@RequestParam String id) {

		
			 			 sysPermissionsService.deleteByID(id);
			
		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

	/**
	 * 
	 * 功能描述：添加
	 *
	 * @param id
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2015年10月30日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/sys/permissions/add")
	@ResponseBody
	 	 public Result add(@RequestBody SysPermissionsDto dto) {
	
	
		 				SysPermissions sysPermissions = new SysPermissions();
				BeanUtils.copyProperties(dto, sysPermissions);
				sysPermissionsService.insert(sysPermissions);
		
				

		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

	/**
	 * 
	 * 功能描述：修改
	 *
	 * @param id
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2015年10月30日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/sys/permissions/update")
	@ResponseBody
	 	 public Result edit(@RequestBody SysPermissionsDto dto) {
		
	 				SysPermissions sysPermissions = new SysPermissions();
				BeanUtils.copyProperties(dto, sysPermissions);
				sysPermissionsService.updateSelective(sysPermissions);
		
		
		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

}
