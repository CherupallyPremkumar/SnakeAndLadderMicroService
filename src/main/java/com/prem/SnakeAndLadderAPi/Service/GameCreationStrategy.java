package com.prem.SnakeAndLadderAPi.Service;

import com.prem.SnakeAndLadderAPi.DTO.*;
import com.prem.SnakeAndLadderAPi.Exception.InvalidGameException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GameCreationStrategy {
     GameExitDto createGame(List<PlayerDto> playerDtos) throws InvalidGameException;

     GameExitDto moveGame(GameDto gameDto) throws InvalidGameException;
}
