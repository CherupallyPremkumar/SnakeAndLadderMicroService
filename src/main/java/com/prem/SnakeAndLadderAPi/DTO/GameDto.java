package com.prem.SnakeAndLadderAPi.DTO;

import com.prem.SnakeAndLadderAPi.Pojo.Board;
import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class GameDto {
    String gameId;
    Player players;
    int number;
}
