package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
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
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService service;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test", trelloLists);
        trelloBoardDtos.add(trelloBoardDto);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> result = service.fetchTrelloBoards();

        //Then
        assertEquals(result, trelloBoardDtos);
        result.forEach(trelloListDto -> {
            assertEquals(trelloListDto.getId(), "1");
            assertEquals(trelloListDto.getName(), "test");
            assertEquals(trelloListDto.getLists(), trelloLists);
        });
    }

//    @Test
//    public void testCreateTrelloCard() {
//        //Given
//        List<TrelloCardDto> trelloCardDtos = new ArrayList<>();
//        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test desc", "bot", "test");
//        trelloCardDtos.add(trelloCardDto);
//
//        //When
//        CreatedTrelloCardDto result = trelloClient.createNewCard(trelloCardDto);
//
//        //Then
//        assertEquals(result.getName(), "test");
//    }

}