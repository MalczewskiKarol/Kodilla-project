package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.config.OwnerConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private OwnerConfig ownerConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview", "CRUD app message");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://malczewskikarol.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("byebye", "Best regards, \n" + ownerConfig.getOwnerSurname());
        context.setVariable("company", ownerConfig.getOwnerName() + " " + ownerConfig.getOwnerSurname() + "     " + companyConfig.getCompanyEmail());
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTrelloDailyEmail(String message) {
        List<String> possibilities = new ArrayList<>();
        possibilities.add("You can create new Task");
        possibilities.add("You can read your Tasks");
        possibilities.add("You can update all of your Tasks");
        possibilities.add("You can delete your Tasks");

        Context context = new Context();
        context.setVariable("preview", "Daily task manager");
        if(taskRepository.count() == 1) {
            context.setVariable("message", "Currently in database you got: " + taskRepository.count() + " task");
        } else {
            context.setVariable("message", "Currently in database you got: " + taskRepository.count() + " tasks");
        }
        context.setVariable("tasks_url", "https://malczewskikarol.github.io");
        context.setVariable("button", "Check Tasks");
        context.setVariable("byebye", "Best regards" + ownerConfig.getOwnerName());
        context.setVariable("company", ownerConfig.getOwnerName() + " " + ownerConfig.getOwnerSurname() + "     " + companyConfig.getCompanyEmail());
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", possibilities);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
