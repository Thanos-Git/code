package com.i.learn.advanced.email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailDemo {
    public static void main(String[] args){
        // 收件人电子邮箱
        String to = "963541095@qq.com";

        // 发件人电子邮箱
        String from = "962230880@qq.com";

        // 指定发送邮件的主机为 localhost
        String host = "smtp.qq.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("962230880@qq.com", "prtqftsoqgzebbaj"); //发件人邮件用户名、授权码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));

            // 收件人
            InternetAddress[] recipients = new InternetAddress[1];
            recipients[0] = new InternetAddress(to);

            // 抄送人
            InternetAddress[] cc = new InternetAddress[1];
            cc[0] = new InternetAddress("chenlin_hust@163.com");
            message.addRecipients(Message.RecipientType.TO,recipients);
            message.addRecipients(Message.RecipientType.CC,cc);

            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 消息
            messageBodyPart.setText("This is message body");

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 附件部分
            messageBodyPart = new MimeBodyPart();
            String filename = "local/person.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart );

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
