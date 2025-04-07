package com.quest.questoftheday.controller;

import com.quest.questoftheday.model.MainQuest;
import com.quest.questoftheday.repository.MainQuestRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main-quests")
public class MainQuestController {

    private final MainQuestRepository mainQuestRepository;

    public MainQuestController(MainQuestRepository mainQuestRepository) {
        this.mainQuestRepository = mainQuestRepository;
    }

    @GetMapping
    public String listMainQuests(Model model) {
        model.addAttribute("mainQuests", mainQuestRepository.findAll());
        return "main-quest/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("mainQuest", new MainQuest());
        return "main-quest/form";
    }

    @PostMapping
    public String createMainQuest(@Valid MainQuest mainQuest, BindingResult result) {
        if (result.hasErrors()) {
            return "main-quest/form";
        }
        mainQuestRepository.save(mainQuest);
        return "redirect:/main-quests";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        MainQuest mainQuest = mainQuestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("mainQuest", mainQuest);
        return "main-quest/form";
    }

    @PostMapping("/{id}")
    public String updateMainQuest(@PathVariable Long id, @Valid MainQuest mainQuest, BindingResult result) {
        if (result.hasErrors()) {
            return "main-quest/form";
        }
        mainQuest.setId(id);
        mainQuestRepository.save(mainQuest);
        return "redirect:/main-quests";
    }

    @GetMapping("/{id}/delete")
    public String deleteMainQuest(@PathVariable Long id) {
        mainQuestRepository.deleteById(id);
        return "redirect:/main-quests";
    }
}