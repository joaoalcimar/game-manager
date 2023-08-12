package com.inatel.gamemanager.repositories;

import com.inatel.gamemanager.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameManagementRepository extends JpaRepository<Game, UUID> {

    List<Game> findByPublisherIdIgnoreCaseContaining(String name);
}
