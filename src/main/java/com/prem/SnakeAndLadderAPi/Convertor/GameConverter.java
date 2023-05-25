package com.prem.SnakeAndLadderAPi.Convertor;

import com.prem.SnakeAndLadderAPi.DTO.GameExitDto;
import com.prem.SnakeAndLadderAPi.Pojo.Game;
import org.springframework.stereotype.Component;

@Component
public class GameConverter {
    public static GameExitDto EntityToDto(Game game){
       return GameExitDto.builder()
                .gameId(game.getGameId())
               .status(game.getStatus())
               .winner(game.getWinner())
                .board(game.getBoard())
                .dice(game.getDice())
                .players(game.getPlayers())
                .gameId(game.getGameId())
                .build();
    }
}
