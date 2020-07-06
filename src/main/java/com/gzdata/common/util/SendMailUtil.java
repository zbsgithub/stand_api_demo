package com.gzdata.common.util;
//package com.gzdata.common.util;
//
//import java.util.Date; 
//import java.util.Properties; 
//  
//
//import javax.mail.Address; 
//import javax.mail.Message; 
//import javax.mail.Session; 
//import javax.mail.Transport; 
//import javax.mail.internet.InternetAddress; 
//import javax.mail.internet.MimeMessage; 
//  
//
//import org.springframework.stereotype.Component;
//
//import com.sun.mail.util.MailSSLSocketFactory;
//
//@Component
//public class SendMailUtil {
//	
//	private String hostName="smtp.163.com";//主机名
//
//	private String sendMailCount="zbs1406368731@163.com";//发送邮箱账号
//
//	private String mailPwd="zbs5015...!";//密码
//	
//	/**
//	 * 
//	 * 功能描述：发送邮件
//	 *
//	 * @param toEmailAddress
//	 * @param emailTitle
//	 * @param emailContent
//	 * @throws Exception
//	 * 
//	 * @author 张兵帅
//	 *
//	 * @since 2018年3月29日
//	 *
//	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
//	 */
//	  public  void sendEmail(String toEmailAddress,String emailTitle,String emailContent)throws Exception{ 
//	    Properties props = new Properties(); 
//	  
//	    // 开启debug调试 
//	    props.setProperty("mail.debug", "false"); 
//	    // 发送服务器需要身份验证 
//	    props.setProperty("mail.smtp.auth", "true"); 
//	    // 设置邮件服务器主机名 
//	    props.setProperty("mail.host", hostName); 
//	    // 发送邮件协议名称 
//	    props.setProperty("mail.transport.protocol", "smtp"); 
//	  
//	    /**SSL认证，注意腾讯邮箱是基于SSL加密的，所有需要开启才可以使用**/
//	    MailSSLSocketFactory sf = new MailSSLSocketFactory(); 
//	    sf.setTrustAllHosts(true); 
//	    props.put("mail.smtp.ssl.enable", "true"); 
//	    props.put("mail.smtp.ssl.socketFactory", sf); 
//	  
//	    //创建会话 
//	    Session session = Session.getInstance(props); 
//	  
//	    //发送的消息，基于观察者模式进行设计的 
//	    Message msg = new MimeMessage(session); 
//	    msg.setSubject(emailTitle); 
//	    //使用StringBuilder，因为StringBuilder加载速度会比String快，而且线程安全性也不错 
//	    StringBuilder builder = new StringBuilder(); 
//	    builder.append("\n"+emailContent); 
//	    builder.append("\n时间 " + new Date()); 
//	    msg.setText(builder.toString()); 
//	    msg.setFrom(new InternetAddress(sendMailCount)); 
//	  
//	    Transport transport = session.getTransport(); 
//	    transport.connect(hostName, sendMailCount, mailPwd); 
//	    //发送消息 
//	    transport.sendMessage(msg, new Address[] { new InternetAddress(toEmailAddress) }); 
//	    transport.close(); 
//	  } 
//	}
//
