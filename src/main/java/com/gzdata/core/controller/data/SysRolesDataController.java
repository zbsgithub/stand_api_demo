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
import com.gzdata.core.dto.SysRolesDto;
import com.gzdata.core.model.SysRoles;
import com.gzdata.core.qo.SysRolesQo;
import com.gzdata.core.service.SysRolesService;

/**
 * 
 * SysRoles信息控制器（管理员）
 *
 * @author 张兵帅
 *
 * @version
 *
 * @since 2020年05月11日
 */
@Controller
public class SysRolesDataController {

	@Autowired
	private  SysRolesService sysRolesService;
	
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
	@RequestMapping("data/sys/roles/pagelist")
	@ResponseBody
	public Result pagelist(@RequestBody SysRolesQo qo) {
		return Result.valueOf(Result.SUCCESS, "操作成功", sysRolesService.findPaginationDataByCondition(qo));
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
	@RequestMapping("data/sys/roles/list")
	@ResponseBody
	public Result list(@RequestBody SysRolesQo qo) {
		return Result.valueOf(Result.SUCCESS, "操作成功", sysRolesService.findList(qo));
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
	@RequestMapping("data/sys/roles/detail")
	@ResponseBody
	public Result detail(@RequestParam String id) {

		return Result.valueOf(Result.SUCCESS, "操作成功", sysRolesService.findById(id));
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
	@RequestMapping("data/sys/roles/batch_delete")
	@ResponseBody
	public Result batchDelete(@RequestParam Serializable... ids) {

	 	 sysRolesService.batchDelete(ids);
			

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
	@RequestMapping("data/sys/roles/delete")
	@ResponseBody
	public Result delete(@RequestParam String id) {

		
			 			 sysRolesService.deleteByID(id);
			
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
	@RequestMapping("data/sys/roles/add")
	@ResponseBody
	 	 public Result add(@RequestBody SysRolesDto dto) {
	
	
		 				SysRoles sysRoles = new SysRoles();
				BeanUtils.copyProperties(dto, sysRoles);
				sysRolesService.insert(sysRoles);
		
				

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
	@RequestMapping("data/sys/roles/update")
	@ResponseBody
	 	 public Result edit(@RequestBody SysRolesDto dto) {
		
	 				SysRoles sysRoles = new SysRoles();
				BeanUtils.copyProperties(dto, sysRoles);
				sysRolesService.updateSelective(sysRoles);
		
		
		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

}
