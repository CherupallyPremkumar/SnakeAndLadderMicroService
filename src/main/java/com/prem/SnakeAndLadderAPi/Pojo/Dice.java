package com.prem.SnakeAndLadderAPi.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Dice implements Serializable {
    @Id
    String diceId;
    int size;


}
