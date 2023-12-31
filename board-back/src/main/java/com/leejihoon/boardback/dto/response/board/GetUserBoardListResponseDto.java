package com.leejihoon.boardback.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.leejihoon.boardback.common.object.BoardListItem;
import com.leejihoon.boardback.dto.response.ResponseCode;
import com.leejihoon.boardback.dto.response.ResponseDto;
import com.leejihoon.boardback.dto.response.ResponseMessage;
import com.leejihoon.boardback.entity.BoardViewEntity;

import lombok.Getter;

@Getter
public class GetUserBoardListResponseDto extends ResponseDto {

    private List<BoardListItem> userBoardList;

    private GetUserBoardListResponseDto(String code, String message, List<BoardViewEntity> boardViewEntities) {
        super(code, message);
        this.userBoardList = BoardListItem.getList(boardViewEntities);
    }

    public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardViewEntity> boardViewEntities) {
        GetUserBoardListResponseDto result = new GetUserBoardListResponseDto(ResponseCode.SUCCESS,
                ResponseMessage.SUCCESS, boardViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
