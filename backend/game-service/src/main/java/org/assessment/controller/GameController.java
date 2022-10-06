package org.assessment.controller;

import org.assessment.exceptions.GameException;
import org.assessment.model.api.GameResponseDTO;
import org.assessment.model.api.requests.MakeMoveReqDTO;
import org.assessment.model.api.requests.StartGameReqDTO;
import org.assessment.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }


    @PostMapping(path = "/start", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDTO> startGame(@RequestBody StartGameReqDTO startRequestDTO) {
        try {
            return ResponseEntity.ok(gameService.startGame(startRequestDTO));
        }
        catch (GameException e){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e);
        }

    }


    @PostMapping(path = "/move", consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponseDTO> makeMove(@RequestBody MakeMoveReqDTO moveReqDTO) {
        return ResponseEntity.ok(gameService.makeMove(moveReqDTO));
    }




}
