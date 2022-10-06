package org.assessment.games;


import org.assessment.exceptions.GameException;
import org.assessment.model.api.GameResponseDTO;
import org.assessment.model.dto.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class Mancala implements GameStrategy {


    @Override
    public GameDTO startGame(List<PlayerDTO> players, int numPits, int numStones) {
        List<PitDTO> board = createBoard(players, numPits, numStones);
        int randomOfTwoInts = new Random().nextBoolean() ? 0 : 1;
        return new GameDTO(board, players, players.get(randomOfTwoInts), GameStatus.ACTIVE, UUID.randomUUID());

    }

    @Override
    public GameDTO makeMove(GameDTO gameDTO, String playerName, int pitIndex) {
        PlayerDTO playerDTO = gameDTO.getPlayers().stream().filter(player -> player.getName().equals(playerName)).findFirst().get();
        if (!isValidMove(gameDTO,playerDTO, pitIndex)) {
            throw new GameException("Invalid move for player " + playerDTO.getName());
        }
        sowPits(gameDTO,pitIndex, playerDTO);
        //no logic implemented
        gameDTO = new GameDTO(gameDTO.getBoard(), gameDTO.getPlayers(), playerDTO, GameStatus.ACTIVE, UUID.randomUUID());
        return gameDTO;

    }

    @Override
    public GameDTO endGame(PlayerDTO player) {
        return null;
    }

    private boolean isValidMove(GameDTO gameDTO,PlayerDTO player, int pitIndex) {
        PitDTO pit = gameDTO.getBoard().get(pitIndex);
        return pit.getPlayerDTO().getName().equals(player.getName()) && !pit.isEmpty();
    }

    private boolean sowPits(GameDTO gameDTO,int pitIndex, PlayerDTO player) {
        boolean updateTurn = true;
        String playerId = player.getName();
        PitDTO selectedPit = gameDTO.getBoard().get(pitIndex);
        int currentIndex = pitIndex + 1;

        for (int i = 0; i < selectedPit.getNumStones(); i++) {
            currentIndex = currentIndex % gameDTO.getBoard().size();
            PitDTO currentPit = gameDTO.getBoard().get(currentIndex);
            boolean isMancalaPit = currentPit instanceof MancalaPitDTO;
            if (!(isMancalaPit && !(playerId.equals(currentPit.getPlayerDTO().getName())))) {
                currentPit.sow();
            }
            currentIndex = currentIndex + 1;
        }

        currentIndex = (currentIndex - 1) % gameDTO.getBoard().size();
        PitDTO oppositePit = gameDTO.getBoard().get((gameDTO.getBoard().size() - 2 - currentIndex));
        if (gameDTO.getBoard().get(currentIndex).isEmpty() && !oppositePit.isEmpty()) {
            int oppositePitStones = oppositePit.getNumStones();
            oppositePit.clear();
            int id = findPlayersMancalaPit(gameDTO,playerId);
            gameDTO.getBoard().get(id).addStones(oppositePitStones + 1);
        } else if (gameDTO.getBoard().get(currentIndex) instanceof MancalaPitDTO) {
            updateTurn = false;
        } else {
            gameDTO.getBoard().get(currentIndex + 1).sow();
        }
        selectedPit.clear();
        return updateTurn;
    }

    private int findPlayersMancalaPit(GameDTO gameDTO,String playerName) {
        Optional<PitDTO> optional = gameDTO.getBoard().stream().filter(pit -> pit.getPlayerDTO().getName().equals(playerName) && pit instanceof MancalaPitDTO).findFirst();
        if (optional.isPresent()) {
            return optional.get().getId();
        } else {
            throw new GameException("No Mancala pit found");
        }
    }


}
