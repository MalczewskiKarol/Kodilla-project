package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.AttachmentByType;
import com.crud.tasks.mapper.Badges;
import com.crud.tasks.mapper.CreatedTrelloCardDto;
import com.crud.tasks.mapper.Trello;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTestSuite {

    @InjectMocks
    private TrelloController controller;

    @Mock
    private TrelloFacade facade;

    @Test
    public void shouldGetTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1", "test", trelloList));

        when(facade.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> result = controller.getTrelloBoards();

        //Then
        assertEquals(result, trelloBoardDtoList);
        trelloBoardDtoList.forEach(trelloBoardDto -> {
            assertEquals(trelloBoardDto.getId(), "1");
            assertEquals(trelloBoardDto.getName(), "test");
            assertEquals(trelloBoardDto.getLists(), trelloList);
        });
    }

    @Test
    public void createdTrelloCard() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "test", "www.o2.pl", new Badges(1, new AttachmentByType(new Trello(1, 1))));
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "test", "test");

        when(facade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto result = controller.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals(result, createdTrelloCardDto);
        assertEquals(result.getId(), "1");
        assertEquals(result.getName(), "test");
        assertEquals(result.getShortUrl(), "www.o2.pl");
        assertEquals(result.getBadges().getVotes(), 1);
        assertEquals(result.getBadges().getAttachments().getTrello().getBoard(), 1);
        assertEquals(result.getBadges().getAttachments().getTrello().getCard(), 1);
        }
}