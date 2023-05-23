package com.prem.SnakeAndLadderAPi.Repo;

import com.prem.SnakeAndLadderAPi.Pojo.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<Board ,String> {
}
