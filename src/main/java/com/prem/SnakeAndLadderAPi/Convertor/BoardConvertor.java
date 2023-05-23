package com.prem.SnakeAndLadderAPi.Convertor;

import com.prem.SnakeAndLadderAPi.DTO.BoardDtoExit;
import com.prem.SnakeAndLadderAPi.DTO.PlayerExitDto;
import com.prem.SnakeAndLadderAPi.Pojo.Board;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BoardConvertor {

    public static  BoardDtoExit EntityToDto(Board board, List<PlayerExitDto> collect) {
       return BoardDtoExit
                .builder()
                .boardId(board.getBoardId())
                .size_of_board(board.getSize_of_board())
                .dice(board.getDice())
                .playerExitDtos(collect)
                .build();
    }
}
