package com.prem.SnakeAndLadderAPi.Controller;

import com.prem.SnakeAndLadderAPi.DTO.BoardDtoExit;
import com.prem.SnakeAndLadderAPi.DTO.GameDto;
import com.prem.SnakeAndLadderAPi.DTO.GameExitDto;
import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.Exception.InvalidGameException;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import com.prem.SnakeAndLadderAPi.Service.BoadService;
import com.prem.SnakeAndLadderAPi.Service.GameCreationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableCaching
public class Contorller {
    @Autowired
    GameCreationStrategy boadService;


     @PostMapping("/createGame")
    public GameExitDto CreateGame(@RequestBody List<PlayerDto> playersDto) throws Exception {
        return boadService.createGame(playersDto);
    }
    @PutMapping("/moveGame/")
    @CacheEvict(key="#gameDto.gameId",value ="GAME")
    public  GameExitDto moveGame(@RequestBody GameDto gameDto) throws InvalidGameException {
        return boadService.moveGame(gameDto);
    }


}
