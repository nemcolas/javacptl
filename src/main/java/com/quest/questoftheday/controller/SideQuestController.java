package com.quest.questoftheday.controller;

import com.quest.questoftheday.model.MainQuest;
import com.quest.questoftheday.model.SideQuest;
import com.quest.questoftheday.repository.MainQuestRepository;
import com.quest.questoftheday.repository.SideQuestRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/side-quests")
public class SideQuestController {

    private final SideQuestRepository sideQuestRepository;
    private final MainQuestRepository mainQuestRepository;

    public SideQuestController(SideQuestRepository sideQuestRepository, MainQuestRepository mainQuestRepository) {
        this.sideQuestRepository = sideQuestRepository;
        this.mainQuestRepository = mainQuestRepository;
    }

    @GetMapping
    public String listSideQuests(Model model) {
        model.addAttribute("sideQuests", sideQuestRepository.findAll());
        return "side-quest/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("sideQuest", new SideQuest());
        model.addAttribute("mainQuests", mainQuestRepository.findAll());
        return "side-quest/form";
    }

    @PostMapping
    public String createSideQuest(@Valid SideQuest sideQuest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mainQuests", mainQuestRepository.findAll());
            return "side-quest/form";
        }
        sideQuestRepository.save(sideQuest);
        return "redirect:/side-quests";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        SideQuest sideQuest = sideQuestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("sideQuest", sideQuest);
        model.addAttribute("mainQuests", mainQuestRepository.findAll());
        return "side-quest/form";
    }

    @PostMapping("/{id}")
    public String updateSideQuest(@PathVariable Long id, @Valid SideQuest sideQuest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mainQuests", mainQuestRepository.findAll());
            return "side-quest/form";
        }
        sideQuest.setId(id);
        sideQuestRepository.save(sideQuest);
        return "redirect:/side-quests";
    }

    @GetMapping("/{id}/delete")
    public String deleteSideQuest(@PathVariable Long id) {
        sideQuestRepository.deleteById(id);
        return "redirect:/side-quests";
    }
} 