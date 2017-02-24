 package com.jlu.edu.email;

public class SendMail {  
      
    // 163邮箱  
    public static boolean send_163(String email,String password) {  
        // 这个类主要是设置邮件  
        MailSenderInfo mailInfo = new MailSenderInfo();  
        mailInfo.setMailServerHost("smtp.163.com");  
        mailInfo.setMailServerPort("25");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName("18844188674@163.com"); // 实际发送者  
        mailInfo.setPassword("zhenghe11649");// 您的邮箱密码  
        mailInfo.setFromAddress("18844188674@163.com"); // 设置发送人邮箱地址  
        mailInfo.setToAddress(email); // 设置接受者邮箱地址  
        mailInfo.setSubject("大学生多功能学习助手提供原密码");  
        mailInfo.setContent("此原密码为"+password+"请牢记此密码，或者到客户端更换密码，谢谢！");  
        // 这个类主要来发送邮件  
        SimpleMailSender sms = new SimpleMailSender();  
        return sms.sendTextMail(mailInfo); // 发送文体格式  
        
    }  
}  