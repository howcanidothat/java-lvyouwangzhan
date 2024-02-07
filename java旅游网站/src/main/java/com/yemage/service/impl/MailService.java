package com.yemage.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailService {
    @Value("${spring.mail.username}")
    private  String mailUsername;
    @Resource
    private  JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    public  void sendMailForActivationAccount(String activationUrl, String email){
        //创建邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            // 设置邮件主题
            message.setSubject("管理员账号激活");
            // 设置邮件发送者，可以多个
            message.setFrom(mailUsername);
            // 设置邮件接收者，可以多个
            message.setTo(email);
            // 设置邮件抄送人，可以多个
            //message.setCc();
            // 设置隐秘抄送人，可以多个
            //message.setBcc();
            // 设置邮件发送日期
            message.setSentDate(new Date());
            // 创建上下文环境
            Context context = new Context();
            context.setVariable("activationUrl",activationUrl);
            String text = templateEngine.process("activation-account.html",context);
            // 设置邮件正文
            message.setText(text,true);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // 邮件发送
        javaMailSender.send(mimeMessage);

    }
}
