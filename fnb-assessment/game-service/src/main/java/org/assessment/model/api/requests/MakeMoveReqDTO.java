package org.assessment.model.api.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class MakeMoveReqDTO {
  private  String currentPlayerName;
  private int index;

}
