

package com.gzdata.common.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 异步任务
 *
 *
 * @author 张兵帅
 *
 * @version
 *
 * @since 2020年5月25日
 */
@Component
public class AsyncTask {

	private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    
    
    @Autowired
    private Environment env;

/*    @Async
    public void task2() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task2任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }
    @Async
    public void task3() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task3任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }*/

    /**
     *
     * 功能描述：执行异步碰撞任务
     *
     *
     * @author 张兵帅
     *
     * @since 2020年5月26日
     *
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
   /* @Async
    public void asyncReq(IdCycleGroupDto dto){

    	String idType = "";// MOBILE_IMEI / OTT_MAC / MOBILE_MAC / OTT_WIFI
  		if(dto.getEquipmentType().equals("OTT")){//设备类型

  		   // ID类型：有线无线：wired-有线,wireless-无线
  	  		if(dto.getUploadIdType().equals("wired")){//有线
  	  			idType = "OTT_MAC";
  	  		}else if(dto.getUploadIdType().equals("wireless")){//无线
  	  			idType = "OTT_WIFI";
  	  		}
  		}else if(dto.getEquipmentType().equals("MOBILE")){
  	  		if(dto.getUploadIdType().equals("wired")){//有线
  	  			idType = "MOBILE_MAC";
  	  		}else if(dto.getUploadIdType().equals("wireless")){//无线
  	  			idType = "MOBILE_IMEI";
  	  		}
  		}


  		long currentTimeMillis = System.currentTimeMillis();

  		BufferedReader br = null;
        List<String> strList=new ArrayList<String>();

        try {
            br = new BufferedReader(new FileReader(new File(dto.getFilePathId())));
            String line = null;
            int sumReadNum=0;
            int readNum=0;
            String[] organId=null;

            while((line = br.readLine())!=null){//循环读取文件内容
                strList.add(line);
                sumReadNum+=1;

                readNum+=1;
                if (readNum==10000) {
            		organId = strList.toArray(new String[strList.size()]);

            		JSONObject obj = new JSONObject();
            		obj.put("idType", idType);//ID上传类型
            		obj.put("date", DateUtil.getDate(DateUtil.getNow(), DateUtil.DEFAULT_DATE_FORMAT));
            		obj.put("taskId", dto.getRemark());//任务id
            		obj.put("encryptType", dto.getEncryptionMethod());//加密方式 [ md5 / no ]
            		obj.put("delimiter", dto.getMacFormate());//ID 分隔符 [ - / : / no ]
            		obj.put("isUpper", dto.getMacCase());//是否大写 [ true / false ]
            		obj.put("terminalId", organId);//设备id array
            		logger.info("organId数组大小："+organId.length);
            		logger.info("organId数组："+organId.toString());
            		String jsonStr = JSONObject.toJSONString(obj);

            		logger.info("obj tostring:{}",jsonStr);

            		String result=OkHttpUtil.okHttpReqPost(Constants.REQ_SYNC_UPLOAD_INFO, jsonStr, Constants.REQ_TOKEN);
                    logger.info("-------request return result ----:"+result);

                    JSONObject object = JSON.parseObject(result);
            		if(object.getIntValue("code")==200){//request success
            			logger.info("respone msg:{}",object.getString("message"));
            		}

            		strList.clear();//置空
            		readNum=0;

            		continue;
				}

            }
            logger.info("------------------batch insert begin---------------");
            
            organId = strList.toArray(new String[strList.size()]);

    		JSONObject obj = new JSONObject();
    		obj.put("idType", idType);//ID上传类型
    		obj.put("date", DateUtil.getDate(DateUtil.getNow(), DateUtil.DEFAULT_DATE_FORMAT));
    		obj.put("taskId", dto.getRemark());//任务id
    		obj.put("encryptType", dto.getEncryptionMethod());//加密方式 [ md5 / no ]
    		obj.put("delimiter", dto.getMacFormate());//ID 分隔符 [ - / : / no ]
    		obj.put("isUpper", dto.getMacCase());//是否大写 [ true / false ]
    		obj.put("terminalId", organId);//设备id array
    		logger.info("organId数组大小："+organId.length);
    		logger.info("organId数组："+organId.toString());
    		String jsonStr = JSONObject.toJSONString(obj);

    		logger.info("obj tostring:{}",jsonStr);

    		String result=OkHttpUtil.okHttpReqPost(Constants.REQ_SYNC_UPLOAD_INFO, jsonStr, Constants.REQ_TOKEN);
            logger.info("-------request return result ----:"+result);

            JSONObject object = JSON.parseObject(result);
    		if(object.getIntValue("code")==200){//request success
    			logger.info("respone msg:{}",object.getString("message"));
    		}

    		strList.clear();//置空
    		readNum=0;
    		
    		logger.info("------------------batch insert over---------------");
    		
            //正常读取完
            logger.info("总行数：{}",sumReadNum);
            File file = new File(dto.getFilePathId());
    		if (file.exists()) {
    			file.delete();
    			logger.info("file is delete success");
    		}else {
    			logger.info("file is not exist");
			}

        } catch (Exception e) {
            logger.error("asyncReq program arise exception:",e);
        }finally{
            try {
                if(br!=null){
                    br.close();
                }
            } catch (Exception e2) {
                logger.error("asyncReq program arise exception:",e2);
            }
        }


        long currentTimeMillis1 = System.currentTimeMillis();
        logger.info("task任务总耗时:{} ms",(currentTimeMillis1-currentTimeMillis));

    }*/
    
    /**
     * 
     * 功能描述：异步计算标签全选
     *
     * @param crowdId
     * @param productId
     * @throws InterruptedException
     * 
     * @author 张兵帅
     *
     * @since 2020年6月4日
     *
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
    /*@Async
    public void labelAllCompute(String crowdId,Integer productId) throws InterruptedException{
    	
    	long currentTimeMillis = System.currentTimeMillis();

    	//异步调用计算的逻辑
		 try {
//			DruidPooledConnection mysqlConnection = mysqlDataSource.getConnection();
//			DruidPooledConnection ckConnection = ckDataSource.getConnection();
			
			SelectTag selectTag=new SelectTag();
			selectTag.action(crowdId, "1",env);
			
		} catch (Exception e) {
			logger.error("labelAllCompute exist exception",e);
		}
		 CrowdInfo ciCrowdInfo = ciService.findById(Integer.parseInt(crowdId));
		 if(ciCrowdInfo!=null){
			 logger.info("人群包id：{}	人群包名称：{}	async compute over",crowdId,ciCrowdInfo.getCrowdName());
		 }else{
			 logger.info("暂未找到该相关人群包信息：人群包id{}",crowdId);
		 }
		
        long currentTimeMillis1 = System.currentTimeMillis();
        logger.info("标签全选-计算-任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }*/
}


