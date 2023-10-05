package com.leejihoon.boardback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.leejihoon.boardback.dto.request.board.PostBoardRequestDto;
import com.leejihoon.boardback.dto.response.ResponseDto;
import com.leejihoon.boardback.dto.response.board.GetBoardResponseDto;
import com.leejihoon.boardback.dto.response.board.GetCommentListResponseDto;
import com.leejihoon.boardback.dto.response.board.GetFavoriteListResponseDto;
import com.leejihoon.boardback.dto.response.board.GetLatestBoardListResponseDto;
import com.leejihoon.boardback.dto.response.board.PostBoardResponseDto;
import com.leejihoon.boardback.dto.response.board.PutFavoriteResponseDto;
import com.leejihoon.boardback.entity.BoardEntity;
import com.leejihoon.boardback.entity.BoardImageEntity;
import com.leejihoon.boardback.entity.BoardViewEntity;
import com.leejihoon.boardback.entity.FavoriteEntity;
import com.leejihoon.boardback.entity.UserEntity;
import com.leejihoon.boardback.repository.BoardImageRepository;
import com.leejihoon.boardback.repository.BoardRepository;
import com.leejihoon.boardback.repository.BoardViewRepository;
import com.leejihoon.boardback.repository.CommentRepository;
import com.leejihoon.boardback.repository.FavoriteRepository;
import com.leejihoon.boardback.repository.UserRepository;
import com.leejihoon.boardback.repository.resultSet.CommentListResultSet;
import com.leejihoon.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService{

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;
    private final BoardViewRepository boardViewRepository;
    private final BoardImageRepository boardImageRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            List<String> boardImageList = dto.getBoardImageList();
            Integer boardNumber = boardEntity.getBoardNumber();

            List<BoardImageEntity> boardImageEntities = new ArrayList<>();
            for (String boardImage: boardImageList){
                BoardImageEntity boardImageEntity = new BoardImageEntity(boardNumber, boardImage);
                boardImageEntities.add(boardImageEntity);
            }
            boardImageRepository.saveAll(boardImageEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        
        BoardViewEntity boardViewEntity = null;
        List<BoardImageEntity> boardImageEntities = new ArrayList<>();

        try {
            
            boardViewEntity = boardViewRepository.findByBoardNumber(boardNumber);
            if(boardViewEntity == null) return GetBoardResponseDto.NotExistBoard();
            
            boardImageEntities = boardImageRepository.findByBoardNumber(boardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(boardViewEntity, boardImageEntities);
    }
    
    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {
        
        List<UserEntity> userEntities = new ArrayList<>();
        
        try {
            boolean existedboard = boardRepository.existsByBoardNumber(boardNumber);
            if (!existedboard) return GetFavoriteListResponseDto.notExistBoard();

            userEntities = userRepository.findByBoardFavorite(boardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetFavoriteListResponseDto.success(userEntities);
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {

        List<BoardViewEntity> boardViewEntities = new ArrayList<>();

        try {

            boardViewEntities = boardViewRepository.findByOrderByWriteDatetimeDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetLatestBoardListResponseDto.success(boardViewEntities);
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {
       
        try {
            
            boolean existedboard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedboard) return PutFavoriteResponseDto.notExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PutFavoriteResponseDto.notExistUser();

            boolean isFavorite = favoriteRepository.existsByUserEmailAndBoardNumber(email, boardNumber);
            
            FavoriteEntity favoriteEntity = new FavoriteEntity(email, boardNumber);
            
            if(isFavorite) favoriteRepository.delete(favoriteEntity);
            else favoriteRepository.save(favoriteEntity);
            


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {
        
        List<CommentListResultSet> resultSets = new ArrayList<>();
        
        try {

            boolean existedboard = boardRepository.existsByBoardNumber(boardNumber);
            if (!existedboard) return GetCommentListResponseDto.notExistBoard();

            resultSets = commentRepository.findByCommentList(boardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(resultSets);
    }


    
}
