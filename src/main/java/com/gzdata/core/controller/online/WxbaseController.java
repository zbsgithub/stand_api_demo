package com.gzdata.core.controller.online;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzdata.common.db.mybatis.result.Result;
import com.gzdata.core.service.WxAccessTokenService;

@Api(description="微信-token-控制器")
@RestController
public class WxbaseController {

	private static final Logger logger = LoggerFactory.getLogger(WxbaseController.class);
	
	@Autowired
	private WxAccessTokenService wxAccessTokenService;
	
	@ApiOperation("获取微信token")
	@GetMapping("/auth/get/wx/access_token")
	public Result getWxAccessTokenInfo(){
		
		String token = wxAccessTokenService.getWechatBaseAccessToken();
		logger.info("token值："+token);
		return Result.valueOf(Result.SUCCESS,"操作成功",token);
	}
}

