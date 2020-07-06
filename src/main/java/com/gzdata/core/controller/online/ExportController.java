package com.gzdata.core.controller.online;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.gzdata.common.db.mybatis.result.Result;
import com.gzdata.common.task.AsyncTask;
import com.gzdata.core.dao.SysUsersDao;
import com.gzdata.core.service.SysUsersService;

@RestController
@RequestMapping("/api/")
public class ExportController {

	@Autowired
	private SysUsersDao sysUsersDao;
	
	@Autowired
	private SysUsersService sysUsersService;
	
	@Autowired  
    private AsyncTask asyncTask; 
	
	/**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象 参照{@link DownloadData}
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
//    @GetMapping("download")
    /*public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserInfo.class).sheet("模板").doWrite(data());
        
        
    }*/

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    @PostMapping("upload")
    @ResponseBody
    public Result upload(MultipartFile file) throws IOException, InterruptedException, ExecutionException {
//        EasyExcel.read(file.getInputStream(), UserInfo.class, new ExcelListener()).sheet().doRead();
        EasyExcel.read(file.getInputStream(),new ExcelListener()).headRowNumber(1).sheet().doRead();
        
        long currentTimeMillis = System.currentTimeMillis();  
        long currentTimeMillis1 = System.currentTimeMillis();  
        
        return Result.valueOf(Result.SUCCESS,"操作成功","task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }
    
/*    private List<UserInfo> data() {
        List<UserInfo> list = new ArrayList<UserInfo>();
        for (int i = 0; i < 10; i++) {
        	UserInfo data = new UserInfo();
        	data.setNick("张兵帅");
        	data.setOpenid("293733");
        	data.setUnionid("929373333333333");
            list.add(data);
        }
        return list;
    }
*/    
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public void getExcel(@RequestParam("file_name")String fileName, HttpServletRequest request, HttpServletResponse response){
        try (  ServletOutputStream out = response.getOutputStream()){
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            //设置返回头为附件形式，并提供下载文件名
            response.setHeader("Content-disposition","attachment;filename="+URLEncoder.encode(fileName,"utf-8")+".xlsx");
            @SuppressWarnings("deprecation")
			ExcelWriter writer = new ExcelWriter(out,ExcelTypeEnum.XLSX,true);
            //提供excel表头模板
//            Sheet sheet = new Sheet(1,0,TotalAmont.class);
//            sheet.setSheetName("第一个");
            //写入数据，数据格式为List<List<String>>
//            writer.write0(null,sheet);
//            writer.write1(null,new  Sheet(1,1,Model.class));
            writer.finish();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
