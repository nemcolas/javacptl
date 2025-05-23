package com.quest.questoftheday.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entitys
public class SideQuest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @ManyToOne
    @JoinColumn(name = "main_quest_id")
    @NotNull(message = "A missão principal é obrigatória")
    private MainQuest mainQuest;

    private LocalDateTime deadline;
    private boolean completed = false;
    private int experiencePoints = 100;
}