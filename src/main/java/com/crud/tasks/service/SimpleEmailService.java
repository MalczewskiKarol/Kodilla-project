package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private MailCreatorService mailCreatorService;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createDailyMessage(mail));
            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sendings", e.getMessage(), e);
        }
    }
    private boolean taskOrCardMessage() {
        return false; // <-- Set True if You want daily Task email
    }

    private MimeMessagePreparator createDailyMessage(final Mail mail) {
        return dailyMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(dailyMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if(taskOrCardMessage()) {
                messageHelper.setText(mailCreatorService.buildTrelloDailyEmail(mail.getMessage()), true);
            } else {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            }
        };
    }
//
//    private MimeMessagePreparator createMimeMessage(final Mail mail) {
//        return mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setTo(mail.getMailTo());
//            messageHelper.setSubject(mail.getSubject());
//            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
//        };
//    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        try {
            if (mail.getToCc() == null) {
                LOGGER.info("Field getToCc is optional.");
            } else {
                mailMessage.setCc(mail.getToCc());
            }
        } finally {
            LOGGER.info("Additional Carbon Copy won't be setted, if field getToCc is empty.");
        }
        return mailMessage;
    }
}