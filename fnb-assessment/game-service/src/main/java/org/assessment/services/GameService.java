package org.assessment.services;

import org.assessment.config.ApplicationConfig;
import org.assessment.exceptions.GameException;
import org.assessment.games.GameStrategy;
import org.assessment.model.api.GameResponseDTO;
import org.assessment.model.api.requests.MakeMoveReqDTO;
import org.assessment.model.api.requests.StartGameReqDTO;
import org.assessment.model.dto.GameDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private GameServiceStrategy gameServiceStrategy;

    ModelMapper mapper = new ModelMapper();

     GameDTO gameDTO =null;

    private GameStrategy game;

    @Autowired
    public GameService() {

    }

    public GameResponseDTO startGame(StartGameReqDTO startRequestDTO) {
          game = gameServiceStrategy.byGameName(startRequestDTO.getGameName());
        Optional<ApplicationConfig.GameConfigDTO> optional = applicationConfig.getGameByName(startRequestDTO.getGameName());
        if (optional.isPresent()) {
             gameDTO = game.startGame(startRequestDTO.getPlayers(), optional.get().getNumPits(), optional.get().getNumStones());
            return mapper.map(gameDTO, GameResponseDTO.class);
        } else {
            throw new GameException("Game " + startRequestDTO.getGameName() + " not enable");
        }

    }

    //Method does not work!!
    public GameResponseDTO makeMove(MakeMoveReqDTO moveReqDTO) {
        GameDTO gameDTO = this.game.makeMove(this.gameDTO,moveReqDTO.getCurrentPlayerName(), moveReqDTO.getIndex());
        return mapper.map(gameDTO, GameResponseDTO.class);

    }


}
