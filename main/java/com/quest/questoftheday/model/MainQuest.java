package com.quest.questoftheday.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MainQuest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @NotNull(message = "A duração diária é obrigatória")
    private String dailyDuration;

    @NotNull(message = "O número de dias por semana é obrigatório")
    private Integer daysPerWeek;

    @OneToMany(mappedBy = "mainQuest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SideQuest> sideQuests = new ArrayList<>();

    private boolean completed = false;
} 