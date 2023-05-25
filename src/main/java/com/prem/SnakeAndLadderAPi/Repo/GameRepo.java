package com.prem.SnakeAndLadderAPi.Repo;

import com.prem.SnakeAndLadderAPi.Pojo.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game,String> {
}
