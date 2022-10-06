package org.assessment.services;

import org.assessment.config.ApplicationConfig;

import org.assessment.exceptions.GameException;
import org.assessment.games.GameStrategy;
import org.assessment.model.api.GameResponseDTO;
import org.assessment.model.api.requests.StartGameReqDTO;
import org.assessment.model.dto.GameDTO;
import org.assessment.model.dto.GameStatus;
import org.assessment.model.dto.PlayerDTO;
import org.junit.Ignore;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameServiceTest {

    @Mock
    private ApplicationConfig applicationConfig;

    @Autowired
    private GameService gameService;

    private StartGameReqDTO startGameReqDTO;


    @BeforeEach
    void setUp(){

        PlayerDTO player1 = new PlayerDTO("Thato");
        PlayerDTO player2 = new PlayerDTO("Lebo");
        List<PlayerDTO> players= new ArrayList<>();
        players.add(player1);
        players.add(player2);
        startGameReqDTO = new StartGameReqDTO(players,"Mancala");
    }

    @Test
    public void startGamePresentGame(){
        ApplicationConfig.GameConfigDTO config = new ApplicationConfig.GameConfigDTO("Mancala",6,6);

        Optional<ApplicationConfig.GameConfigDTO> opt = Optional.of(config);
        when(applicationConfig.getGameByName("Mancala")).thenReturn(opt);
        GameResponseDTO gameDTO = gameService.startGame(startGameReqDTO);
        assertEquals(config.getNumPits()*2+2,gameDTO.getBoard().size());


    }

    @Ignore()
    public void startGameNoGame(){
        when(applicationConfig.getGameByName("Mancala")).thenReturn(Optional.empty());
        Exception exception = assertThrows(GameException.class, () -> {
            gameService.startGame(startGameReqDTO);
        });


    }

}