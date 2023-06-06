package com.prem.SnakeAndLadderAPi.Pojo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player implements Serializable {
    @Id
    String playerId;
    String playerName;
    Color playerColor;
    int position;
}
