package com.prem.SnakeAndLadderAPi.Pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    String boardId;
    int size_of_board;
    @JoinColumn(name = "board_id")
    @OneToMany
    List<Player> player;
    @OneToOne
    Dice dice;
}
