package org.assessment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private String name;

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
