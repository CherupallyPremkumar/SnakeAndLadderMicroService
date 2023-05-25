package com.prem.SnakeAndLadderAPi.Pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player {
    @Id
    String playerId;
    String playerName;
    Color playerColor;
    int position;
}
