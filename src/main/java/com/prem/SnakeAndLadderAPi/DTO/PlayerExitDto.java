package com.prem.SnakeAndLadderAPi.DTO;

import com.prem.SnakeAndLadderAPi.Pojo.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PlayerExitDto {
    String playerId;
    String name;
    Color color;
    int position;
}
