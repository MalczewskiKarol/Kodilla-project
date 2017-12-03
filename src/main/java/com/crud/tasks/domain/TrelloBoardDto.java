package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("lists")
    private List<TrelloListDto> lists;

    public boolean getName(TrelloBoardDto trelloBoardDto) {
        if(name == null) {
            return false;
        }
        return true;
    }

    public boolean getId(TrelloBoardDto trelloBoardDto) {
        if(id == null) {
            return false;
        }
        return true;
    }
}
