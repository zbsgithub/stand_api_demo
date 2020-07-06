package com.gzdata.common.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.gzdata.common.compoent.Constants;
import com.gzdata.common.util.AESUtil;
import com.gzdata.common.util.DateUtil;
import com.gzdata.common.util.MD5Util;



/**
 * 定时任务
 * 
 *
 * @author 张兵帅
 *
 * @version 
 *
 * @since 2019年7月24日
 */
@Component
//@EnableScheduling
public class SaticScheduleTask {

	
	private static Logger logger = LoggerFactory.getLogger(SaticScheduleTask.class);
	
	@Autowired
    private Environment env;
	
	
	
	/**
	 * 
	 * 功能描述：创建文件夹
	 *
	 * @param path
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年6月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
    public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}
			else{
				return false;
			}
		} catch (Exception e) {
		} finally {
			file = null;
		}
		return false;
	}
    
	/**
	 * 
	 * 功能描述：异步下载文件到本地
	 *
	 * @param reqUrl 请求的绝对路径带参数
	 * @param savePath 文件保存地址到类别
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年6月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public String downloadFileEnqueue(String reqUrl,String savePath) {
		final Map<String, Object> map=new HashMap<String, Object>();//返回文件路径
		
        final long startTime = System.currentTimeMillis();
        logger.info("startTime="+startTime);
        OkHttpClient  okHttpClient = new OkHttpClient.Builder()
        .connectTimeout(50*60L, TimeUnit.SECONDS)
        .readTimeout(50*60L, TimeUnit.SECONDS)
        .build();
        
        Request request = new Request.Builder().header("token",Constants.REQ_TOKEN)
                .url(reqUrl)
                .addHeader("Connection", "close")
                .build();
        
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                logger.info("download failed");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                /**
                 * 储存下载文件的目录  example："E:/mapdata/download";
                 */
                mkDirectory(savePath);
                
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    logger.info("文件总字节大小：{}",total);
                    
                    String fileName=MD5Util.md5(DateUtil.getYYYYMMDDHHMMSS());
                    File file = new File(savePath, File.separator+fileName);
                    map.put("saveFilePath", file.getAbsolutePath());
                    
                    fos = new FileOutputStream(file);
//                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
//                        sum += len;
//                        int progress = (int) (sum * 1.0f / total * 100);
//                        logger.info("download progress : " + progress);
//                        .onDownloading("",progress);
                    }
                    fos.flush();
                    logger.info("download success");
                    logger.info("totalTime="+ ((System.currentTimeMillis() - startTime) /1000) +"s");
                } catch (Exception e) {
                    logger.error("download failed : ",e);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
        
        
        return String.valueOf(map.get("saveFilePath"));
    }
	
	/**
	 * 
	 * 功能描述：同步下载文件
	 *
	 * @param reqUrl
	 * @param savePath
	 * @return
	 * @throws Exception
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年6月13日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public String downloadFileExcute(String reqUrl,String savePath) throws Exception {
		final Map<String, Object> map=new HashMap<String, Object>();//返回文件路径
		
        final long startTime = System.currentTimeMillis();
        logger.info("startTime="+startTime);
        OkHttpClient  okHttpClient = new OkHttpClient.Builder()
        .connectTimeout(50*60L, TimeUnit.SECONDS)
        .readTimeout(50*60L, TimeUnit.SECONDS)
        .build();
        
        Request request = new Request.Builder().header("token",Constants.REQ_TOKEN)
                .url(reqUrl)
                .addHeader("Connection", "close")
                .build();
        
       Response response= okHttpClient.newCall(request).execute();
       
       InputStream is = null;
       byte[] buf = new byte[2048];
       int len = 0;
       FileOutputStream fos = null;
       /**
        * 储存下载文件的目录  example："E:/mapdata/download";
        */
       mkDirectory(savePath);
       
       try {
           is = response.body().byteStream();
           long total = response.body().contentLength();
           logger.info("文件总字节大小：{}",total);
           
           String fileName=MD5Util.md5(DateUtil.getYYYYMMDDHHMMSS());
           File file = new File(savePath, File.separator+fileName);
           map.put("saveFilePath", file.getAbsolutePath());
           
           fos = new FileOutputStream(file);
           while ((len = is.read(buf)) != -1) {
               fos.write(buf, 0, len);
           }
           fos.flush();
           logger.info("download success");
           logger.info("totalTime="+ ((System.currentTimeMillis() - startTime) /1000) +"s");
       } catch (Exception e) {
           logger.error("download failed : ",e);
       } finally {
           try {
               if (is != null)
                   is.close();
           } catch (IOException e) {
           }
           try {
               if (fos != null)
                   fos.close();
           } catch (IOException e) {
           }
       }
       
        return String.valueOf(map.get("saveFilePath"));
    }
				
	public void excuteCommand(String dataFilePath,String tableName){
		logger.info("----------begin wirte data to customer ck db -------------");
		//TODO 测试上不指定端口如何
//		 String excuteCommand="cat %s | /usr/bin/clickhouse-client --host %s --port %s --user %s --password %s --query \"INSERT INTO %s.%s FORMAT CSV\"";
//    	 String joinPath = String.format(excuteCommand, dataFilePath,env.getProperty("ck.db.ip"),env.getProperty("ck.db.port"),env.getProperty("ck.db.username"),env.getProperty("ck.db.password"),env.getProperty("ck.db.dbname"),tableName);
    	 String excuteCommand="cat %s | /usr/bin/clickhouse-client --host %s --user %s --password %s --query \"INSERT INTO %s.%s FORMAT CSV\"";
    	 String joinPath = String.format(excuteCommand, dataFilePath,env.getProperty("ck.db.ip"),env.getProperty("ck.db.username"),AESUtil.decrypt(env.getProperty("ck.db.password")),env.getProperty("ck.db.dbname"),tableName);
	     logger.info("cat data to ck command: {}",joinPath);
	     
	     Process proce=null;
	     InputStream stderr=null;
    	 try{
	            Runtime rt = Runtime.getRuntime();
	            //执行命令, 最后一个参数，可以使用new File("path")指定运行的命令的位置
	            List<String> commandArray=new ArrayList<String>();
	            commandArray.add("/bin/sh");
	            commandArray.add("-c");
	            commandArray.add(joinPath);
	            
	            proce = rt.exec(commandArray.toArray(new String[commandArray.size()]));//(子进程执行)
	            
	            //方法阻塞, 等待命令执行完成（成功会返回0）
	            proce.waitFor();

	            logger.info("process exit value:{}",proce.exitValue());
	            
	            stderr =  proce.getInputStream();
	            InputStreamReader isr = new InputStreamReader(stderr);
	            BufferedReader br = new BufferedReader(isr);
	            
	            logger.info("return result:{}",br.readLine());
	            String line="";
	            while ((line = br.readLine()) != null) { // 打印出命令执行的结果
	            	logger.info("command excute result：{}",line);
	            }
	        }catch (Exception e){
				logger.error("command excute error",e);
	        }finally{
	        	try {
					stderr.close();
					proce.destroy();
				} catch (Exception e) {
					logger.error("command close exist exception:",e);
				}
	        }

		logger.info("----------end wirte data to customer ck db -------------");
	}
	
}
