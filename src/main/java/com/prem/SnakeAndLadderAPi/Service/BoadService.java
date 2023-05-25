package com.prem.SnakeAndLadderAPi.Service;

import com.prem.SnakeAndLadderAPi.Convertor.BoardConvertor;
import com.prem.SnakeAndLadderAPi.Convertor.PlayerConvertor;
import com.prem.SnakeAndLadderAPi.DTO.BoardDtoExit;
import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.DTO.PlayerExitDto;
import com.prem.SnakeAndLadderAPi.Exception.InvalidGameException;
import com.prem.SnakeAndLadderAPi.Pojo.Board;
import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import com.prem.SnakeAndLadderAPi.Repo.BoardRepo;
import com.prem.SnakeAndLadderAPi.Repo.DiceRepo;
import com.prem.SnakeAndLadderAPi.Repo.PlayersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BoadService  {
    @Autowired
    BoardRepo boardRepo;
    @Autowired
    DiceRepo diceRepo;
    @Autowired
    PlayersRepo playersRepo;

    public int diceRoll() {
        return 1;
    }

    public BoardDtoExit createGame(List<PlayerDto> playersDto) throws InvalidGameException {
        validatePlayers(playersDto);
       Dice dice=saveDice();
       Board board=createBoard(dice);
       List<Player> playerList=createPlayers(playersDto);
       savePlayers(playerList);
       saveBoard(board);
       return convertToDto(board,playerList);

    }

    private void validatePlayers(List<PlayerDto> playersDto) throws InvalidGameException {
        if(playersDto.isEmpty()) {
            throw new InvalidGameException("players list is empty");
        }
        else if(playersDto.size()<2)
        {
            throw new InvalidGameException("At least two players are required");
        }
    }
    private Dice saveDice() {
        Dice dice = new Dice(UUID.randomUUID().toString(), 6);
        return diceRepo.save(dice);
    }

    private Board createBoard(Dice dice) {
        Board board = new Board();
        board.setBoardId(UUID.randomUUID().toString());
        board.setSize_of_board(100);
        board.setDice(dice);
        return board;
    }

    private List<Player> createPlayers(List<PlayerDto> playersDto) {
        List<Player> playersList = new ArrayList<>();
        for (PlayerDto p : playersDto) {
            Player player = PlayerConvertor.DtoToEntity(p);
            player.setPlayerId(UUID.randomUUID().toString());
            player.setPosition(1);
            playersList.add(player);
        }
        return playersList;
    }

    private void savePlayers(List<Player> playersList) {
        playersRepo.saveAll(playersList);
    }

    private void saveBoard(Board board) {
        boardRepo.save(board);
    }

    private BoardDtoExit convertToDto(Board board, List<Player> playersList) {
        return BoardConvertor.EntityToDto(board, playersList.stream().map(PlayerConvertor::EntityToDto).collect(Collectors.toList()));

    }
}
