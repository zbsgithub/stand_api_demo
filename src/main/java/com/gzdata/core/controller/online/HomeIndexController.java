package com.gzdata.core.controller.online;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gzdata.common.db.mybatis.result.Result;
import com.gzdata.common.exception.GenericException;


@Api("主页控制器")
@RestController
public class HomeIndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeIndexController.class);
	
	/**
	 * 
	 * 功能描述：未登录
	 *
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年5月9日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@RequestMapping("/un_login")
	@ResponseBody
	public void unauthInfo(){
		
		throw new GenericException("token is expired");
		
	}
	
	
	
	/**
	 * 
	 * 功能描述：get userid
	 *
	 * @param unionid
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年5月6日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	/*public String getUserId(String unionid){
		
		*//**
		 * 根据unionid获取userid
		 *//*
		DingTalkClient clientUser = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getUseridByUnionid");
		OapiUserGetUseridByUnionidRequest request = new OapiUserGetUseridByUnionidRequest();
		request.setUnionid(unionid);
		request.setHttpMethod("GET");

		String userId = "";
		
		try {
			OapiUserGetUseridByUnionidResponse response = clientUser.execute(request, getToken());
			logger.info("userId:" + response.getUserid());
			userId = response.getUserid();
			logger.info("contactType:"+response.getContactType());//0.企业内部 1.企业外部
			
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}*/
	/**
	 * 
	 * 功能描述：获取token
	 *
	 * @return
	 * @throws Exception
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年5月6日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	/*public String getToken() throws Exception{
		*//**
		 * 存在替换的appKey值
		 *//*
		String appKey = "dingoa1cnxm2psp7si2pda";
		
		String appSecret = "osIHsTEDEJnyWibDfar_YoY4e1OpFErjvFYTKz507T-A_Jez2hHSWxqbus296zLJ";
		
		DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
		OapiGettokenRequest request = new OapiGettokenRequest();
		request.setAppkey(appKey);
		request.setAppsecret(appSecret);
		request.setHttpMethod("GET");
		String token="";
		
		try {
			OapiGettokenResponse response = client.execute(request);
			token=response.getAccessToken();
		} catch (ApiException e) {
			e.printStackTrace();
		}
		
		return token;
	}*/
	
	/**
	 * 
	 * 功能描述：获取扫码-用户信息
	 *
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年5月6日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	/*public String getUserInfo(String userId){
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
		OapiUserGetRequest request = new OapiUserGetRequest();
		request.setUserid(userId);
		request.setHttpMethod("GET");
		OapiUserGetResponse response=null;
		try {
			response = client.execute(request, getToken());
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response.toString();
	}*/
	
	

	@GetMapping(value = "/notRole")
	@ResponseBody
	public Result UnAuthen() {
		logger.info("----------------not role ---------------");
		return Result.valueOf(Result.ERROR,"暂无权限");
	}
}
