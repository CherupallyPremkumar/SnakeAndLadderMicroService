package com.prem.SnakeAndLadderAPi.Convertor;

import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.DTO.PlayerExitDto;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerConvertor {
    static public Player DtoToEntity(PlayerDto playerDto)
    {
        return Player.builder().playerColor(playerDto.getColor()).playerName(playerDto.getName()).build();
    }
    static public PlayerExitDto EntityToDto(Player player)
    {
        return PlayerExitDto
                .builder()
                .playerId(player.getPlayerId())
                .color(player.getPlayerColor())
                .name(player.getPlayerName())
                .position(player.getPosition())
                .build();
    }

}
