package org.assessment.model.api.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.assessment.model.dto.PlayerDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StartGameReqDTO {
  private  List<PlayerDTO> players;
  private String gameName;

}
