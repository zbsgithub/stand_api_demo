package com.gzdata.common.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 随机数
 * 
 *
 * @author 张兵帅
 *
 * @version
 *
 * @since 2018年12月20日
 */
public class RandomUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(RandomUtil.class);

	/**
	 * 
	 * 功能描述：生成唯一uuid(截取13位)+三位随机数
	 *
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2018年12月20日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String generImgRandomName() {
		String uuidStr = UUID.randomUUID().toString();
		String resultNum = "";
		try {

			SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
			/**
			 * gener 0,999 random number
			 */
			int randNum = number.nextInt(1000);
			if (randNum < 100) {
				resultNum = "0" + randNum;
			} else {
				resultNum = String.valueOf(randNum);
			}
			logger.info(resultNum);
		} catch (NoSuchAlgorithmException nsae) {
			logger.error("generator random num exist error:",nsae);
		}

		return uuidStr.substring(0, 13) + "-" + resultNum;

	}
	
}
