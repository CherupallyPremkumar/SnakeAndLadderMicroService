package com.prem.SnakeAndLadderAPi;

import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.Pojo.Color;
import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import com.prem.SnakeAndLadderAPi.Repo.BoardRepo;
import com.prem.SnakeAndLadderAPi.Repo.DiceRepo;
import com.prem.SnakeAndLadderAPi.Repo.PlayersRepo;
import com.prem.SnakeAndLadderAPi.Service.BoadService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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

    public void setup() {
        MockitoAnnotations.openMocks(this);
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
        List<PlayerDto> playerDtos = new ArrayList<>();
        playerDtos.add(new PlayerDto("John", Color.BLUE));
        playerDtos.add(new PlayerDto("Prem", Color.BLUE));
        Dice dice = new Dice(UUID.randomUUID().toString(), 6);
        when(diceRepo.save(any(Dice.class))).thenReturn(dice);
        assertNotNull(boadService.createGame(playerDtos));
    }

}
