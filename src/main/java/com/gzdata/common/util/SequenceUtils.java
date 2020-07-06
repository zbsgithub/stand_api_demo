package com.gzdata.common.util;


/**
 * 
 * 说明：处理对序列表的业务操作
 * 
 * @author 张小平
 * 
 * @version 1.0
 * 
 * @since 2017年05月03日
 */
public  class SequenceUtils {


//	private StringBuffer sb;// 字符串追加公用对象
	private final static int digit = 5;// 预留位数
	private static int seq = 1;

	/**
	 * 
	 * 功能描述：获取订单号
	 * 
	 * @return
	 * 
	 * @author 张小平
	 * 
	 * @since 2015年9月30日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getOrdernumNumber() {
		StringBuffer sb = new StringBuffer();
		String ordernum = sb.append("0000").append(seq++)
				.toString();
		sb = new StringBuffer();
		return sb
				.append(DateUtil.getYYYYMMDDHHMMSS())
				.append(ordernum.substring(ordernum.length() - digit,
						ordernum.length())).toString();
	}
}
