package com.prem.SnakeAndLadderAPi.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Dice {
    @Id
    String diceId;
    int size;


}
