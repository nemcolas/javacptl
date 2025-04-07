package com.quest.questoftheday.repository;

import com.quest.questoftheday.model.SideQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SideQuestRepository extends JpaRepository<SideQuest, Long> {
    List<SideQuest> findByMainQuestId(Long mainQuestId);
} 