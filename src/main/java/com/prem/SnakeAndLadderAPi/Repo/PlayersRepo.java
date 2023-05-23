package com.prem.SnakeAndLadderAPi.Repo;

import com.prem.SnakeAndLadderAPi.Pojo.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepo extends JpaRepository<Player,String> {
}
