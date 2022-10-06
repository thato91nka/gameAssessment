package org.assessment.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.assessment.model.dto.PlayerDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameResponseDTO {
    private PlayerDTO currentPlayer;
    private List<PitResponseDTO> board;

}
