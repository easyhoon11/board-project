package com.leejihoon.boardback.service;

import org.springframework.http.ResponseEntity;

import com.leejihoon.boardback.dto.request.board.PostBoardRequestDto;
import com.leejihoon.boardback.dto.response.board.GetBoardResponseDto;
import com.leejihoon.boardback.dto.response.board.GetCommentListResponseDto;
import com.leejihoon.boardback.dto.response.board.GetFavoriteListResponseDto;
import com.leejihoon.boardback.dto.response.board.GetLatestBoardListResponseDto;
import com.leejihoon.boardback.dto.response.board.PostBoardResponseDto;
import com.leejihoon.boardback.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
    
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
    
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);
}
