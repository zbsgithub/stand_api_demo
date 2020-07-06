package com.gzdata.core.controller.online;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gzdata.common.compoent.Constants;
import com.gzdata.common.db.mybatis.result.Result;
import com.gzdata.common.util.DateUtil;
import com.gzdata.common.util.MD5Util;
/**
 * 上传文件控制器
 * 
 *
 * @author 张兵帅
 *
 * @version 
 *
 * @since 2020年5月17日
 */
@Api("上传文件控制器")
@RestController
@RequestMapping("/api/")
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	/**
	 * 
	 * 功能描述：上传文件
	 *
	 * @param file
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2020年5月17日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@ApiOperation("上传人群包文件")
	@PostMapping("submit/upload/crowd_info")
	public Result uploadShopImgInfo(@RequestParam(value = "file") MultipartFile file) {
		/**
		 * 上传逻辑：
		 * 1.只是上传文件即可，成功则返回文件地址
		 * 
		 */
		if (file.isEmpty()) {
            logger.info("----------------文件不能为空--------------");
            return Result.valueOf(Result.ERROR, "上传失败：文件不能为空");
        }
		
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePathPre = "";

        filePathPre =Constants.fileUploadPath; // 上传后的路径

        //md5(时间戳+文件名)
        fileName = MD5Util.md5(DateUtil.getYYYYMMDDHHMMSS()+fileName)+ suffixName; // 新文件绝对路径
        
        File dest = new File(filePathPre + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(String.format("------fileName:%s        filePath:%s", fileName,dest.getAbsolutePath()));
        
		return Result.valueOf(Result.SUCCESS, "操作成功",dest.getAbsolutePath());
	}
}
