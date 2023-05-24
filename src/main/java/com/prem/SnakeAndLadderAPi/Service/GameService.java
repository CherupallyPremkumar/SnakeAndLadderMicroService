package com.prem.SnakeAndLadderAPi.Service;

import com.prem.SnakeAndLadderAPi.Convertor.BoardConvertor;
import com.prem.SnakeAndLadderAPi.Convertor.GameConverter;
import com.prem.SnakeAndLadderAPi.Convertor.PlayerConvertor;
import com.prem.SnakeAndLadderAPi.DTO.BoardDtoExit;
import com.prem.SnakeAndLadderAPi.DTO.GameDto;
import com.prem.SnakeAndLadderAPi.DTO.GameExitDto;
import com.prem.SnakeAndLadderAPi.DTO.PlayerDto;
import com.prem.SnakeAndLadderAPi.Exception.InvalidGameException;
import com.prem.SnakeAndLadderAPi.Pojo.Board;
import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import com.prem.SnakeAndLadderAPi.Pojo.Game;
import com.prem.SnakeAndLadderAPi.Pojo.Player;
import com.prem.SnakeAndLadderAPi.Repo.BoardRepo;
import com.prem.SnakeAndLadderAPi.Repo.DiceRepo;
import com.prem.SnakeAndLadderAPi.Repo.GameRepo;
import com.prem.SnakeAndLadderAPi.Repo.PlayersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class GameService implements GameCreationStrategy {

    HashMap<String, Game> gameHashMap = new HashMap<>();
    HashMap<String, Game> playersHashMap = new HashMap<>();
    @Autowired
    DiceRepo diceRepo;
    @Autowired
    PlayersRepo playersRepo;
    @Autowired
    BoardRepo boardRepo;
    @Autowired
    GameRepo gameRepo;

    public GameExitDto createGame(List<PlayerDto> playerDtos) throws InvalidGameException {
        validatePlayers(playerDtos);
        List<Player> playerList = createPlayers(playerDtos);
        Dice dice = saveDice();
        Board board = createBoard(dice);
        saveBoard(board);
        Game game = createGame(dice, board, playerList);
        gameHashMap.put(game.getGameId(), game);
        savePlayers(playerList);
        saveGame(game);
        return GameConverter.EntityToDto(game);
    }

    @Override
    public GameExitDto moveGame(GameDto gameDto) throws InvalidGameException {
        if (isGameCompleted(gameDto)) {
            UpdateGame(gameDto);
        }
        return null;
    }

    private GameExitDto UpdateGame(GameDto gameDto) {
        Game game = gameHashMap.get(gameDto.getGameId());
        int number = gameDto.getNumber();

        Player updatedPlayer = game.getPlayers()
                .stream()
                .filter(player -> Objects.equals(player.getPlayerId(), gameDto.getPlayers().getPlayerId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        Player playerAfterMove = addPosition(updatedPlayer, number, game);

        if (playerAfterMove.getPosition() == game.getBoard().getSize_of_board()) {
            gameOver(game, playerAfterMove);
            return GameConverter.EntityToDto(game);
        }else {
            Player player=game.getPlayers().stream().filter(new Predicate<Player>() {
                @Override
                public boolean test(Player player) {
                    return player.getPlayerId().equals(playerAfterMove.getPlayerId());
                }
            }).findFirst().get();
        }

return null;
    }

    private void gameOver(Game game,Player player) {
        game.setWinner(player.getPlayerName());
        gameHashMap.remove(game.getGameId());
    }

    private Player addPosition(Player player, int number, Game game) {
        int afterAdd = player.getPosition() + number;
        if (afterAdd <= game.getBoard().getSize_of_board()) {
            player.setPosition(afterAdd);
        }
        return player;
    }

    private void responseThisPlayerISWinner(Player player) {
    }


    private boolean isGameCompleted(GameDto gameDto) throws InvalidGameException {
        Game b = gameHashMap.get(gameDto.getGameId());
        if (b.getStatus() == null) throw new InvalidGameException("Game hasn't Created");
        else return b.getStatus();
    }

    private void validatePlayers(List<PlayerDto> playersDto) throws InvalidGameException {
        if (playersDto.isEmpty()) {
            throw new InvalidGameException("players list is empty");
        } else if (playersDto.size() < 2) {
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

    private void saveGame(Game game) {
        gameRepo.save(game);
    }

    private BoardDtoExit convertToDto(Board board, List<Player> playersList) {
        return BoardConvertor.EntityToDto(board, playersList.stream().map(PlayerConvertor::EntityToDto).collect(Collectors.toList()));

    }

    private Game createGame(Dice dice, Board board, List<Player> playerList) {
        return new Game(UUID.randomUUID().toString(), true,"", playerList, dice, board);
    }
}
