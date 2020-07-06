package com.gzdata.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.ConnectionInfo;
import com.trilead.ssh2.SCPClient;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

public class SshUtil {

	private static Logger logger = LoggerFactory.getLogger(SshUtil.class);

	/**
	 * 
	 * 功能描述：链接远程主机
	 *
	 * @param ip
	 * @param port
	 * @param userName
	 * @param password
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2019年7月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Connection connec(String ip, int port, String userName,
			String password) {

		Connection con = new Connection(ip, port);
		try {
			ConnectionInfo connect = con.connect();
			
			boolean isAuthed = con.authenticateWithPassword(userName, password);
			
		} catch (IOException e1) {
			logger.error("connec remote host arise exception:",e1);
		}
		
		
		return con;
	}

	/**
	 * 
	 * 功能描述：操作远程与本地文件
	 *
	 * @param con
	 * @param opertType
	 *            get/put
	 * @param localPath
	 *            absFilepath C:/test/b.txt
	 * @param remotePath
	 *            absFilePath:例如：/home/test/d.txt
	 * 
	 * @author 张兵帅
	 *
	 * @since 2019年7月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void opertFile(Connection con, String opertType, String localPath,
			String remotePath) {

		try {

			SCPClient scpClient = con.createSCPClient();

			if (opertType.equals("get")) {// 从服务器获取文件
				scpClient.get(remotePath, localPath);

			} else if (opertType.equals("put")) {// 将本地文件上传到服务器
				scpClient.put(localPath, remotePath);
			}
			
			con.close();// 关闭链接

		} catch (IOException e) {
			logger.error("opeartor reomte and local file arise exception::",e);
		}

	}

	/**
	 * 
	 * 功能描述：开启会话，执行原生linux命令
	 *
	 * @param con
	 *            会话链接
	 * @param command
	 *            命令
	 * 
	 * @author 张兵帅
	 *
	 * @since 2019年7月25日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Map<String, Object> excuteShell(Connection con, String command) {

		Session session;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			session = con.openSession();

			session.execCommand(command);// 显示执行命令后的信息
			InputStream stdout = new StreamGobbler(session.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			
			int i =0;
			String lineContent="";
			
			while (true) {
				String line;
				try {
					line = br.readLine();
					
					if (line == null) {
						logger.info("远程服务器返回信息:空");
						break;
					}
					logger.info("远程服务器返回信息:" + line);
					
					lineContent+=line+"\n";
					
					if(i!=0){
						resultMap.put("result", lineContent);
					}else{
						resultMap.put("result", line);
					}
					
					i+=1;

				} catch (IOException e) {
					logger.error("excute command arise exception:",e);
				}

			}
			// 获得退出状态：0或1
			logger.info("ExitCode:{}",session.getExitStatus());
			
			resultMap.put("code", session.getExitStatus());
			
			
			// 关闭会话
			session.close();
		} catch (IOException e1) {
			logger.error("excute command arise exception:",e1);
		}

		return resultMap;
	}
}
