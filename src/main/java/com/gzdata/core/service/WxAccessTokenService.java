                                                            
package com.gzdata.core.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzdata.common.compoent.Constants;
import com.gzdata.common.db.mybatis.dao.BaseDAOInterface;
import com.gzdata.common.db.mybatis.service.AbstractBaseService;
import com.gzdata.common.util.DateUtil;
import com.gzdata.common.util.MD5Util;
import com.gzdata.common.util.OkHttpUtil;
import com.gzdata.common.util.RedisUtil;
import com.gzdata.common.util.UUIDUtil;
import com.gzdata.core.dao.WxAccessTokenDao;
import com.gzdata.core.model.WxAccessToken;
import com.gzdata.core.qo.WxAccessTokenQo;

/**
 * 
 * 说明：处理对wx_access_token的业务操作
 * 
 * @author 张兵帅
 * 
 * @version 1.0
 * 
 * @since 2019年08月10日
 */
@Service
public class WxAccessTokenService extends AbstractBaseService<WxAccessToken> {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(WxAccessTokenService.class);
	
	@Autowired
	private WxAccessTokenDao wxAccessTokenDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	 
	@Override
	protected BaseDAOInterface<WxAccessToken> getDAO() {
		return wxAccessTokenDao;
	}

	/**
	 * 
	 * 功能描述：获取自定义登录态
	 *
	 * @param code
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2019年8月11日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@Transactional
	public String getCustomLoginStatus(String code){
		
		String url = String.format(Constants.REQ_OPENID_URL_STRING, Constants.APPID,Constants.APPSECRET,code);
		String result=OkHttpUtil.okHttpReqGet(url,null);
		logger.info("-------request return result ----:"+result);
		
		JSONObject object = JSON.parseObject(result);
		String sessionkey="";
		
		if(object.getIntValue("errcode")==0){//request success
			
			sessionkey=MD5Util.md5(DateUtil.getYYYYMMDDHHMMSS()+UUIDUtil.generatePrimaryKey());
			/*ShopOper soOper=new ShopOper();
			//用户信息
			ShopOperQo qo=new ShopOperQo();
			qo.setWxopenId(object.getString("openid"));
			List<ShopOper> soList = shopOperService.findList(qo);
			if(soList!=null && !soList.isEmpty()){//非空
				 soOper = soList.get(0);
				
			}else{//为空
				soOper = new ShopOper();
				soOper.setWxopenId(object.getString("openid"));
				soOper.setStatus(0);//用户状态：0.状态
				soOper.setCreateTime(DateUtil.getNow());//创建时间
				shopOperService.insertSelective(soOper);//新增
			}*/
			
//			redisUtil.hset(Constants.HASHNAME,sessionkey, object.getString("openid")+","+object.getString("session_key")+","+soOper.getUserId(),15*24*60*60);//存到hash
//			logger.info("===============================current user userId："+soOper.getUserId()+"===========================openid:"+object.getString("openid"));
//			
			
		}else{//requst failed
			logger.info("----------------request failed reason："+object.getString("errcode"));
		}

		
		return sessionkey;
	}
	
	/**
	 * 获取微信基础TOKEN
	 * @return
	 */
	@Transactional
	public String getWechatBaseAccessToken() {
		long currentTime = System.currentTimeMillis();
		long twoHour = currentTime +30*60*1000;//30分钟
		WxAccessTokenQo qo = new WxAccessTokenQo();
		qo.setStatus(2);
		qo.setBeginTime(DateUtil.getNow());// 当前日期
		qo.setExpTime(new Date(twoHour));// 刷新时间
		qo.setAppid(Constants.APPID);
		List<WxAccessToken> list = wxAccessTokenDao.findNotExpAccessToken(qo);
		if(list.size() > 0){
			return list.get(0).getToken();
		}else {
			String url = String.format(Constants.REQ_TOKEN_URL, Constants.APPID,Constants.APPSECRET);
			System.out.println("正在刷新token:"+url);
			String result = OkHttpUtil.okHttpReqGet(url,null);
			JSONObject object = JSON.parseObject(result);
			String tokenValue = object.getString("access_token");
			if(tokenValue != null && tokenValue.length()!=0){//存在token值
				WxAccessToken accessToken = null;
				qo = new WxAccessTokenQo();
				qo.setStatus(2);
				qo.setAppid(Constants.APPID);
				List<WxAccessToken> allList =  wxAccessTokenDao.findList(qo);
				if(allList.size() > 0){//存在token记录
					accessToken = allList.get(0);
					accessToken.setBeginTime(DateUtil.getNow());
					//获取加30min时间
					long onePointFive =  currentTime +30*60*1000;
					accessToken.setExpTime(new Date(onePointFive));
					accessToken.setStatus(2);
					accessToken.setAppid(Constants.APPID);
					accessToken.setToken(tokenValue);
					updateSelective(accessToken);
				}else{//不存在token记录
					accessToken = new WxAccessToken();
					accessToken.setBeginTime(DateUtil.getNow());
					// 获取加30min时间
					long onePointFive =  currentTime +30*60*1000;
					accessToken.setExpTime(new Date(onePointFive));
					accessToken.setStatus(2);
					accessToken.setAppid(Constants.APPID);
					accessToken.setToken(tokenValue);
					insertSelective(accessToken);
				}
				return tokenValue;
			}else{
				return null;
			}
		}
	}
}