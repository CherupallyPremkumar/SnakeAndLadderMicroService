package com.prem.SnakeAndLadderAPi.DTO;

import com.prem.SnakeAndLadderAPi.Pojo.Board;
import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Getter
@Setter
public class GameExitDto {

    String gameId;
    List<Player> players;
    Dice dice;
    Board board;
}