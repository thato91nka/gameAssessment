package org.assessment.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "config")
public class ApplicationConfig {
    private List<GameConfigDTO> games;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GameConfigDTO {
        private String name;
        private int numPits;
        private int numStones;
    }

    public Optional<GameConfigDTO> getGameByName(String name){
       return games.stream().filter(game->game.getName().equals(name)).findFirst();
    }

}
