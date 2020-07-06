package com.gzdata.common.compoent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 常量类
 * 
 *
 * @author 张兵帅
 *
 * @version 
 *
 * @since 2018年4月7日
 */
public class Constants {
	/**
	 * linux下文件保存路径
	 * /tmp/
	 */	
	public static final String fileUploadPath=File.separator+"tmp"+File.separator;//文件上传地址
	/**
	 * window下保存路径
	 */
//	public static final String fileUploadPath="E:"+File.separator+"test_dirs"+File.separator;//文件上传地址
	
	/**
	 * reqest openid url
	 */
	public static final String REQ_OPENID_URL_STRING="https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
	
	/**
	 * request token url
	 */
	public static final String REQ_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	
//	public static final String APPID = "dingoaunw9silkfjd9jdp3";
//	
//	public static final String APPSECRET = "WRW9CvM6wwMCg93jSTDFUVSH5Z-e-geQ8H5sQ6QQ24yOgh688gZV1LSgsjmAFdle";
	public static final String APPID = "dingoa1cnxm2psp7si2pda";
	
	public static final String APPSECRET = "osIHsTEDEJnyWibDfar_YoY4e1OpFErjvFYTKz507T-A_Jez2hHSWxqbus296zLJ";
	
	public static final String DDURL = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
	
	
	
	
	/**
	 *  role:admin-权限列表
	 */
	@SuppressWarnings("serial")
	public  final  static Map<String,Object> ADMIN_MAP = new HashMap<String, Object>(){
		{
			put("crowd_portrait","人群画像");
			put("crowd_word_cloud","人群画像-词云");
			put("crowd_social","人群画像-社会属性");
			put("crowd_consum","人群画像-消费水平");
			put("crowd_behavior","人群画像-行为特征");
			put("crowd_content","人群画像-内容偏好");
			
			put("industry_report","行业报告");
			put("platform_set","平台设置");

			put("crowd_manager","人群管理");
			put("crowd_create_manager","人群管理-创建人群");
			put("crowd_manager_list","人群管理-人群列表");
			put("crowd_manager_circle","人群管理-创建人群-标签圈群");
			put("crowd_manager_extend","人群管理-创建人群-人群扩展");
			put("crowd_manager_collision","人群管理-创建人群-ID碰撞");
			
		}};

	/**
	 * role:nomal-权限列表
	 */
	@SuppressWarnings("serial")
	public  final  static Map<String,Object> NORMAL_MAP = new HashMap<String, Object>(){
		{
			put("crowd_portrait","人群画像");
			put("crowd_word_cloud","人群画像-词云");
			put("crowd_social","人群画像-社会属性");
			put("crowd_consum","人群画像-消费水平");
			put("crowd_behavior","人群画像-行为特征");
			put("crowd_content","人群画像-内容偏好");
			
			put("industry_report","行业报告");
//			put("platform_set","平台设置");

			put("crowd_manager","人群管理");
			put("crowd_create_manager","人群管理-创建人群");
			put("crowd_manager_list","人群管理-人群列表");
			put("crowd_manager_circle","人群管理-创建人群-标签圈群");
//			put("crowd_manager_extend","人群管理-创建人群-人群扩展");
//			put("crowd_manager_collision","人群管理-创建人群-ID碰撞");
		}};
		
		
	/**
	 * CCTV-SFTP:
			IP: 62.234.188.56
			端口: 22
			账号: cctv_family_portrait
			密码: n4FKc6+3x9WPZkr8
	 */
	public static final String CCTV_SFTP_IP="62.234.188.56";
	
	public static final String CCTV_SFTP_ACCOUNT="cctv_family_portrait";
	
	public static final String CCTV_SFTP_PWD="n4FKc6+3x9WPZkr8";
	
	/**
	 * 请求接口-公共token
	 */
	public static final String REQ_TOKEN="9cb8825599c193a0795e40aa34af835f";
	
	/**
	 * 人群扩展接口
	 */
	public static final String REQ_EXPANSION_INFO="http://182.92.130.11:9090/cctv-bridge-api/v1/sync/expansion-info";
	/**
	 * ID上传接口
	 */
	public static final String REQ_SYNC_UPLOAD_INFO="http://182.92.130.11:9090/cctv-bridge-api/v1/sync/upload-info";
	
	
	@SuppressWarnings("serial")
	public  final  static Map<String,ArrayList<String>> MAP_ORDER = new LinkedHashMap<String, ArrayList<String>>(){
		{
			put("社会属性",new ArrayList<>(Arrays.asList("家庭结构","家庭关系","孩子年龄","省","城市","城市级别","电视设备品牌","电视设备尺寸","手机品牌","手机设备价格","社群标签","性别","年龄","教育情况","个人收入")));
			put("消费水平",new ArrayList<>(Arrays.asList("家庭消费水平",	"汽车价格","房产价格","物业类型","电视设备价格")));
			put("行为特征",new ArrayList<>(Arrays.asList("月收视天数","开机时长","收视时段","launcher","是否是视频网站付费会员","消费偏好","电视广告品牌认知","应用偏好")));
			put("内容偏好",new ArrayList<>(Arrays.asList("直播频道偏好","直播节目类型偏好","直播情感偏好","点播APP偏好","点播节目类型偏好","点播情感偏好","应用偏好")));
			
		}};

}
