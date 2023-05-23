package com.prem.SnakeAndLadderAPi.Service;

import com.prem.SnakeAndLadderAPi.DTO.BoardDtoExit;
import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.DTO.PlayerExitDto;
import com.prem.SnakeAndLadderAPi.Exception.InvalidGameException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GameCreationStrategy {
     BoardDtoExit createGame(List<PlayerDto> playerDtos) throws InvalidGameException;
}
