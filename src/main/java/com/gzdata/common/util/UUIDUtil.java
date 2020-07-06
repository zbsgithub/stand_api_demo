package com.gzdata.common.util;

import java.util.UUID;

public class UUIDUtil {
	/**
	 * 生成随机uuid
	 * 
	 * @return
	 */
	public static String generatePrimaryKey() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
