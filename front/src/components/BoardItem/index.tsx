import React from "react";
import "./style.css";
import DefaultProfileImage from "assets/default-profile-image.png";
import { BoardListItem } from "types";
import { useNavigate } from "react-router-dom";
import { BOARD_DETAIL_PATH } from "constant";

//          interface: 게시물 리스트 아이템 컴포넌트 Props          //
interface Props {
  boardListItem: BoardListItem;
}

//          component: 게시물 리스트 아이템 컴포넌트         //
export default function BoardItem({ boardListItem }: Props) {
  //          state: Propertites          //
  const { boardNumber, title, content, boardTitleImage } = boardListItem;
  const { commentCount, favoriteCount, viewCount } = boardListItem;
  const { writeDatetime, writerNickname, writerProfileImage } = boardListItem;

  //          function: 네비게이트 함수         //
  const navigator = useNavigate();

  //          event handler: Card Click 이벤트 처리 함수          //
  const onCardClickHandler = () => {
    navigator(BOARD_DETAIL_PATH(boardNumber));
  };

  //          render: 게시물 리스트 아이템 컴포넌트 렌더링          //
  return (
    <div className="board-list-item-card" onClick={onCardClickHandler}>
      <div className="board-list-item-main-box">
        <div className="board-list-item-top">
          <div className="board-list-item-profile-box">
            <div
              className="board-list-item-profile-image"
              style={{
                backgroundImage: `url(${
                  writerProfileImage ? writerProfileImage : DefaultProfileImage
                })`,
              }}
            ></div>
          </div>
          <div className="board-list-item-write-box">
            <div className="board-list-item-nickname">{writerNickname}</div>
            <div className="board-list-item-write-date">{writeDatetime}</div>
          </div>
        </div>
        <div className="board-list-item-middle">
          <div className="board-list-item-title">{title}</div>
          <div className="board-list-item-contents">{content}</div>
        </div>
        <div className="board-list-item-bottom">
          <div className="board-list-item-counts">
            {`댓글 ${commentCount} · 좋아요 ${favoriteCount} · 조회수 ${viewCount}`}
          </div>
        </div>
      </div>
      {boardTitleImage !== null && (
        <div className="board-list-item-image-box">
          <div
            className="board-list-item-image"
            style={{ backgroundImage: `url(${boardTitleImage})` }}
          ></div>
        </div>
      )}
    </div>
  );
}
