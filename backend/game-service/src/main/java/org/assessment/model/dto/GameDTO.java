package org.assessment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private List<PitDTO> board;
    private List<PlayerDTO> players;
    private PlayerDTO currentPlayer;
    private GameStatus status;
    private UUID gameId;
}
