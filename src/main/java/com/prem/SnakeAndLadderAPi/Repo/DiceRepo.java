package com.prem.SnakeAndLadderAPi.Repo;

import com.prem.SnakeAndLadderAPi.Pojo.Dice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiceRepo extends JpaRepository<Dice,String> {
}
