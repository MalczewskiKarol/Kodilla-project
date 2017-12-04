package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.CreatedTrelloCard;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
public class TrelloController {
    public TrelloBoardDto trelloBoardDto;
    @Autowired
    private TrelloClient trelloClient;


    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.privateUrl();

//        List<TrelloBoardDto> trelloBoards = trelloClient.privateUrl();
//        trelloBoards.stream()
//                .filter(n -> n.getName(trelloBoardDto))
//                .filter(n -> n.getId(trelloBoardDto))
//                .filter(n -> n.getName().contains("Kodilla"))
//                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));

//        List<TrelloBoardDto> trelloBoards = trelloClient.privateUrl();
//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getName() + " " + trelloBoardDto.getId());
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList -> System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//        });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}