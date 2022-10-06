package org.assessment.services;


import org.assessment.games.GameStrategy;
import org.assessment.games.Mancala;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameServiceStrategy {

    private Map<String, GameStrategy> games;

    public GameServiceStrategy() {

        games = new HashMap<>();

        Mancala mancala = new Mancala();
        games.put("Mancala",mancala);

    }

    public GameStrategy byGameName(String gameName){
        return games.get(gameName);
    }

    public Map<String, GameStrategy> getGames() {
        return games;
    }



}
