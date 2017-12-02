package com.crud.tasks.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    @JsonProperty("badges")
    private Badges badges;

    @AllArgsConstructor
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Badges {

        @JsonProperty("votes")
        private int votes;

        @JsonProperty("attachmentsByType")
        private AttachmentByType attachments;

        @AllArgsConstructor
        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class AttachmentByType {

            @JsonProperty("trello")
            private Trello trello;

            @AllArgsConstructor
            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            static class Trello {

                @JsonProperty("board")
                private int board;

                @JsonProperty("card")
                private int card;
            }
        }
    }
}
