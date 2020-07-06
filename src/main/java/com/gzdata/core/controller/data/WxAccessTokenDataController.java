package com.gzdata.core.controller.data;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzdata.common.db.mybatis.result.Result;
import com.gzdata.core.dto.WxAccessTokenDto;
import com.gzdata.core.model.WxAccessToken;
import com.gzdata.core.qo.WxAccessTokenQo;
import com.gzdata.core.service.WxAccessTokenService;

/**
 * 
 * WxAccessToken信息控制器（管理员）
 *
 * @author 张兵帅
 *
 * @version
 *
 * @since 2019年08月10日
 */
@Controller
public class WxAccessTokenDataController {

	@Autowired
	private  WxAccessTokenService wxAccessTokenService;
	
	/**
	 * 
	 * 功能描述：wx_access_token列表--普通分页
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/wx/access/token/pagelist")
	@ResponseBody
	public Result pagelist(@ModelAttribute WxAccessTokenQo qo) {
		return Result.valueOf(Result.SUCCESS, "操作成功", wxAccessTokenService.findTotalCountByCondition(qo), wxAccessTokenService.findPaginationDataByCondition(qo));
	}
	
	/**
	 * 
	 * 功能描述：wx_access_token列表--通过条件查询
	 * 
	 * @return
	 * 
	 * @author 张兵帅
	 * 
	 * @since 2019年08月10日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("data/wx/access/token/list")
	@ResponseBody
	public Result list(@RequestBody WxAccessTokenQo qo) {
		return Result.valueOf(Result.SUCCESS, "操作成功", wxAccessTokenService.findList(qo));
	}

	

	/**
	 * 
	 * 功能描述：wx_access_token详情
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
	@RequestMapping("data/wx/access/token/detail")
	@ResponseBody
	public Result detail(@RequestParam String id) {

		return Result.valueOf(Result.SUCCESS, "操作成功", wxAccessTokenService.findById(id));
	}
	
	/**
	 * 
	 * 功能描述：wx_access_token批量删除
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
	@RequestMapping("data/wx/access/token/batch_delete")
	@ResponseBody
	public Result batchDelete(@RequestParam Serializable... ids) {

	 	 wxAccessTokenService.batchDelete(ids);
			

		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

	/**
	 * 
	 * 功能描述：wx_access_token删除
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
	@RequestMapping("data/wx/access/token/delete")
	@ResponseBody
	public Result delete(@RequestParam String id) {

		
			 			 wxAccessTokenService.deleteByID(id);
			
		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

	/**
	 * 
	 * 功能描述：wx_access_token添加
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
	@RequestMapping("data/wx/access/token/add")
	@ResponseBody
	 	 public Result add(@RequestBody WxAccessTokenDto dto) {
	
	
		 				WxAccessToken wxAccessToken = new WxAccessToken();
				BeanUtils.copyProperties(dto, wxAccessToken);
				wxAccessTokenService.insert(wxAccessToken);
		
				

		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

	/**
	 * 
	 * 功能描述：wx_access_token修改
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
	@RequestMapping("data/wx/access/token/update")
	@ResponseBody
	 	 public Result edit(@RequestBody WxAccessTokenDto dto) {
		
	 				WxAccessToken wxAccessToken = new WxAccessToken();
				BeanUtils.copyProperties(dto, wxAccessToken);
				wxAccessTokenService.updateSelective(wxAccessToken);
		
		
		return Result.valueOf(Result.SUCCESS, "操作成功");
	}

}
