package com.quest.questoftheday.repository;

import com.quest.questoftheday.model.MainQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainQuestRepository extends JpaRepository<MainQuest, Long> {
} 