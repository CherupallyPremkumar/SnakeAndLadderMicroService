package com.prem.SnakeAndLadderAPi;

import com.prem.SnakeAndLadderAPi.DTO.GameDto;
import com.prem.SnakeAndLadderAPi.DTO.GameExitDto;
import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.Exception.InvalidGameException;
import com.prem.SnakeAndLadderAPi.Pojo.Color;
import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import com.prem.SnakeAndLadderAPi.Repo.BoardRepo;
import com.prem.SnakeAndLadderAPi.Repo.DiceRepo;
import com.prem.SnakeAndLadderAPi.Repo.GameRepo;
import com.prem.SnakeAndLadderAPi.Repo.PlayersRepo;
import com.prem.SnakeAndLadderAPi.Service.BoadService;
import com.prem.SnakeAndLadderAPi.Service.GameService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


@SpringBootTest
class SnakeAndLadderAPiApplicationTests {
    @InjectMocks
    BoadService boadService;
    @Mock
    DiceRepo diceRepo;
    @Mock
    PlayersRepo playersRepo;
    @Mock
    BoardRepo boardRepo;
    @InjectMocks
    GameService gameService;
    @Mock
    GameRepo gameRepo;
     @Autowired
    GameExitDto gameExitDto;
    List<PlayerDto> playerDtos = new ArrayList<>();
    Player prem;
    Player vaishnavi;


    public void setup() {
        MockitoAnnotations.openMocks(this);

    }
    @BeforeEach
public void befor()
{
    playerDtos.add(new PlayerDto("vaishnavi", Color.BLUE));
    playerDtos.add(new PlayerDto("Prem", Color.RED));
    playerDtos.add(new PlayerDto("Shivani", Color.GREEN));
    playerDtos.add(new PlayerDto("Bhanu", Color.ORANGE));
}

    @Test
    public void testCreateGameWithEmtyPlayersDto() throws Exception {
        List<PlayerDto> playerDtoList = new ArrayList<>();
        Exception exception = assertThrows(Exception.class, () -> boadService.createGame(playerDtoList));
        assertEquals("players list is empty", exception.getMessage());
    }

    @Test
    public void testCreateGamemethodWithOnePlayer() throws Exception {
        List<PlayerDto> playerDtos = new ArrayList<>();
        playerDtos.add(new PlayerDto());
        Exception exception = assertThrows(Exception.class, () -> boadService.createGame(playerDtos));
        assertEquals("At least two players are required", exception.getMessage());
    }

    @Test
    public void TestCreateGamewithValidPlayersDto() throws Exception {

        Dice dice = new Dice(UUID.randomUUID().toString(), 6);
        when(diceRepo.save(any(Dice.class))).thenReturn(dice);
        assertNotNull(boadService.createGame(playerDtos));
    }
    @Test
    public void testCreateGamePlayersDto() throws Exception {
        List<PlayerDto> playerDtoList = new ArrayList<>();
        Exception exception = assertThrows(Exception.class, () -> gameService.createGame(playerDtoList));
        assertEquals("players list is empty", exception.getMessage());
    }

    @Test
    public void testCreateGameOnePlayer() throws Exception {
        List<PlayerDto> playerDtos = new ArrayList<>();
        playerDtos.add(new PlayerDto());
        Exception exception = assertThrows(Exception.class, () -> gameService.createGame(playerDtos));
        assertEquals("At least two players are required", exception.getMessage());
    }

    @Test

    public void TestCreateGameValidPlayersDto() throws Exception {
        ArrayList<PlayerDto> playerDt=new ArrayList<>();
        playerDt.add(new PlayerDto("Joh", Color.BLUE));
        playerDt.add(new PlayerDto("s", Color.RED));
        Dice dice = new Dice(UUID.randomUUID().toString(), 6);
        when(diceRepo.save(any(Dice.class))).thenReturn(dice);
       assertNotNull(gameService.createGame(playerDt));
    }
    @Test
    public void MoveGame() throws InvalidGameException {
       this.gameExitDto=gameService.createGame(playerDtos);
       GameExitDto gg=null;
        while (true)
        {
                GameExitDto g=null;
           for (Player player:this.gameExitDto.getPlayers())
           {
               System.out.println(player.toString());
               Random random=new Random();
               GameDto gameDto=new GameDto();
               gameDto.setGameId(this.gameExitDto.getGameId());
               gameDto.setPlayers(player);
               gameDto.setNumber(random.nextInt(6));
              g=gameService.moveGame(gameDto);
               System.out.println(g.toString());
               if (!g.getStatus()) break;
           }

            if(!g.getStatus()) {
                gg = g;
                break;
            }
        }
        assertNotNull(gg);

    }

}
