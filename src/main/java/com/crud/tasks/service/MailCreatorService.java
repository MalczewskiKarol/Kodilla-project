package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.config.OwnerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private OwnerConfig ownerConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("preview", "CRUD app message");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://malczewskikarol.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("byebye", "Best regards, \n" + ownerConfig.getOwnerSurname());
        context.setVariable("company", ownerConfig.getOwnerName() + " " + ownerConfig.getOwnerSurname() + "     " + companyConfig.getCompanyEmail());
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
