package com.prem.SnakeAndLadderAPi.Controller;

import com.prem.SnakeAndLadderAPi.DTO.BoardDtoExit;
import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import com.prem.SnakeAndLadderAPi.Service.BoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Contorller {
    @Autowired
    BoadService boadService;

    public Integer rollDice() {
        return boadService.diceRoll();
    }
@PostMapping("/createGame")
    public BoardDtoExit CreateGame(@RequestBody List<PlayerDto> playersDto) throws Exception {
        return boadService.createGame(playersDto);
    }


}
