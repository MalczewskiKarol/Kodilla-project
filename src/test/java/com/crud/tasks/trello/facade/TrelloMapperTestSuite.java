package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTestSuite {

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        trelloBoardDtos.add(new TrelloBoardDto("1", "name", new ArrayList<>()));
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(trelloBoards.size(), 1);
        assertEquals(trelloBoards.get(0).getId(), "1");
        assertEquals(trelloBoards.get(0).getName(), "name");
        assertEquals(trelloBoards.get(0).getLists(), new ArrayList<>());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        trelloBoards.add(new TrelloBoard("1", "name", new ArrayList<>()));
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(trelloBoardDtos.size(), 1);
        assertEquals(trelloBoardDtos.get(0).getId(), "1");
        assertEquals(trelloBoardDtos.get(0).getName(), "name");
        assertEquals(trelloBoardDtos.get(0).getLists(), new ArrayList<>());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        trelloListDtos.add(new TrelloListDto("1", "name", true));
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(trelloLists.size(), 1);
        assertEquals(trelloLists.get(0).getName(), "name");
        assertEquals(trelloLists.get(0).getId(), "1");
        assertEquals(trelloLists.get(0).isClosed(), true);
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        trelloLists.add(new TrelloList("1", "name", true));
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(trelloListDtos.size(), 1);
        assertEquals(trelloListDtos.get(0).getId(), "1");
        assertEquals(trelloListDtos.get(0).getName(), "name");
        assertEquals(trelloListDtos.get(0).isClosed(), true);
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "desc", "bot", "1");
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(), "name");
        assertEquals(trelloCardDto.getDescription(), "desc");
        assertEquals(trelloCardDto.getPos(), "bot");
        assertEquals(trelloCardDto.getListId(), "1");
    }

    @Test
    public void shoudMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "bot", "1");
        TrelloMapper trelloMapper = new TrelloMapper();

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCard.getName(), "name");
        assertEquals(trelloCard.getDescription(), "desc");
        assertEquals(trelloCard.getPos(), "bot");
        assertEquals(trelloCard.getListId(), "1");
    }
}