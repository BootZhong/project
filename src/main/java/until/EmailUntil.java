package until;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;

import pojo.Customer;

/*发邮件
 * 
 * 
 * */
public class EmailUntil {
	
	//用于记录第几位顾客
	int customerNum = 0;
	
	//获取邮箱验证码
	public void emailPost(String receiveEmail, int checkNum) throws MessagingException {
		// 创建Properties 类用于记录邮箱的一些属性
	    Properties props = new Properties();
	    // 表示SMTP发送邮件，必须进行身份验证
	    props.put("mail.smtp.auth", "true");
	    //此处填写SMTP服务器
	    props.put("mail.smtp.host", "smtp.qq.com");
	    //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
	    props.put("mail.smtp.port", "587");
	    // 此处填写你的账号
	    props.put("mail.user", "2284420486@qq.com");
	    // 此处的密码就是前面说的16位STMP口令
	    props.put("mail.password", "jlipcinvkkxyebej");
	    // 构建授权信息，用于进行SMTP进行身份验证
	    Authenticator authenticator = new Authenticator() {
	
	        protected PasswordAuthentication getPasswordAuthentication() {
	            // 用户名、密码
	            String userName = props.getProperty("mail.user");
	            String password = props.getProperty("mail.password");
	            return new PasswordAuthentication(userName, password);
	        }
	    };
	    // 使用环境属性和授权信息，创建邮件会话
	    Session mailSession = Session.getInstance(props, authenticator);
	    // 创建邮件消息
	    MimeMessage message = new MimeMessage(mailSession);
	    // 设置发件人
	    InternetAddress form = new InternetAddress(
	            props.getProperty("mail.user"));
	    message.setFrom(form);
	
	    // 设置收件人的邮箱
	    InternetAddress to = new InternetAddress(receiveEmail);
	    message.setRecipient(RecipientType.TO, to);
	
	    // 设置邮件标题
	    message.setSubject("你的验证码");
	
	    // 设置邮件的内容体
	    message.setContent("你找回账号的验证码为: "+checkNum, "text/html;charset=UTF-8");
	
	    // 最后当然就是发送邮件啦
	    Transport.send(message);	
		}
	
	//把顾客信息通过邮件发送到管理员邮箱
	public void sendCustomerInfo(Customer customer) throws MessagingException {
		// 创建Properties 类用于记录邮箱的一些属性
	    Properties props = new Properties();
	    // 表示SMTP发送邮件，必须进行身份验证
	    props.put("mail.smtp.auth", "true");
	    //此处填写SMTP服务器
	    props.put("mail.smtp.host", "smtp.qq.com");
	    //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
	    props.put("mail.smtp.port", "587");
	    // 此处填写你的账号
	    props.put("mail.user", "2284420486@qq.com");
	    // 此处的密码就是前面说的16位STMP口令
	    props.put("mail.password", "jlipcinvkkxyebej");
	    // 构建授权信息，用于进行SMTP进行身份验证
	    Authenticator authenticator = new Authenticator() {
	
	        protected PasswordAuthentication getPasswordAuthentication() {
	            // 用户名、密码
	            String userName = props.getProperty("mail.user");
	            String password = props.getProperty("mail.password");
	            return new PasswordAuthentication(userName, password);
	        }
	    };
	    // 使用环境属性和授权信息，创建邮件会话
	    Session mailSession = Session.getInstance(props, authenticator);
	    // 创建邮件消息
	    MimeMessage message = new MimeMessage(mailSession);
	    // 设置发件人
	    InternetAddress form = new InternetAddress(
	            props.getProperty("mail.user"));
	    message.setFrom(form);
	
	    // 设置收件人的邮箱
	    InternetAddress to = new InternetAddress("lang@forleaves.com");
	    message.setRecipient(RecipientType.TO, to);
	
	    // 设置邮件标题
	    customerNum++;
	    message.setSubject("You have a new inquiry from "+customer.getName());
	    
	    // 设置邮件的内容体
	    String emailContent = "To: For Leaves Ltd,<br>" + 
	    					  "Dear Mr.Lang,<br>"+
	    					  "Message Content:"+customer.getMessage()+"<br><br><br><br><br>"+
	    					  "Name:"+customer.getName()+"<br>"+
	    					  "Company:"+customer.getEmail()+"<br>"+
	    					  "Email:"+customer.getCompany()+"<br>";
	    					  
	    message.setContent(emailContent, "text/html;charset=UTF-8");
	
	    // 最后当然就是发送邮件啦
	    Transport.send(message);	
		}
}

