package com.gzdata.common.compoent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 操作日志工具类
 *
 *
 * @author 张兵帅
 *
 * @version
 *O
 * @since 2020年5月12日
 */
@Component
public class OperUtil {

	private static final Logger logger = LoggerFactory.getLogger(OperUtil.class);


	/**
	 *
	 * 功能描述：插入日志记录
	 *
	 * @param userId
	 * @param content
	 *
	 * @author 张兵帅
	 *
	 * @since 2020年5月12日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	
	/*@Async
	 * public void insertLog(String userId,String content){
		SysUsers users=userService.findById(userId);
		OperLog ol = new OperLog();
		ol.setOperPerson(users.getUsername());
		ol.setOperTime(DateUtil.getNow());
		ol.setOperContent(content);
		operLogService.insertSelective(ol);

	}*/

}
