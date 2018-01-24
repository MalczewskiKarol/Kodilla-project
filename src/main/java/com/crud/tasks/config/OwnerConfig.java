package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OwnerConfig {
    @Value("${info.app.owner.name}")
    private String ownerName;

    @Value("${info.app.owner.surname}")
    private String ownerSurname;
}
