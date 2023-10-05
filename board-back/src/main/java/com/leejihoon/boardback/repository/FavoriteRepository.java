package com.leejihoon.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leejihoon.boardback.entity.FavoriteEntity;
import com.leejihoon.boardback.entity.primaryKey.FavoritePk;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk>{
    
    boolean existsByUserEmailAndBoardNumber(String userEmail, Integer boardNumber);

}
