package org.assessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assessment.model.api.requests.StartGameReqDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    void startGame() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/game/start")
                .content("{ \"players\": [ { \"name\": \"p1\" }, { \"name\": \"p2\" } ], \"gameName\": \"Mancala\" }")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void startGameNoGameFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/game/start")
                        .content("{ \"players\": [ { \"name\": \"p1\" }, { \"name\": \"p2\" } ], \"gameName\": \"Mandala\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

}