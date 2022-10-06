package org.assessment.games;


import org.assessment.model.dto.GameDTO;
import org.assessment.model.dto.MancalaPitDTO;
import org.assessment.model.dto.PitDTO;
import org.assessment.model.dto.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

public interface GameStrategy {

    GameDTO startGame(List<PlayerDTO>players, int numPits, int numStones);

    GameDTO makeMove(GameDTO gameDTO,String currentPlayerName, int index);

    GameDTO endGame(PlayerDTO player);



    default List<PitDTO> createBoard(List<PlayerDTO> players, int numPits, int numStones) {
        List<PitDTO> board = new ArrayList<>();
        int index = 0;
        for (PlayerDTO player : players) {
            for (int i = 0; i < numPits; i++) {
                board.add(new PitDTO(index, player, numStones));
                index++;
            }
            board.add(new MancalaPitDTO(index, player));
            index++;
        }
        return board;
    }
}
