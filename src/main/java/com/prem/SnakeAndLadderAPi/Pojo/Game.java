package com.prem.SnakeAndLadderAPi.Pojo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Game implements Serializable {
    @Id
    String gameId;
    Boolean status;
    String winner;
    @OneToMany
    List<Player> players;
    @OneToOne
    @JoinColumn(name = "dice_dice_id")
    Dice dice;
    @OneToOne
    @JoinColumn(name = "board_board_id")
    Board board;


}
