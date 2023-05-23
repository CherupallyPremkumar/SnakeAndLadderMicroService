package com.prem.SnakeAndLadderAPi.DTO;

import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BoardDtoExit {
    String boardId;
    int size_of_board;
    Dice dice;
    List<PlayerExitDto> playerExitDtos;
}
