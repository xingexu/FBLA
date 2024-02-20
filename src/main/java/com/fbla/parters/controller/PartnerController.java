package com.fbla.parters.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fbla.parters.model.FilterDTO;
import com.fbla.parters.model.Partner;
import com.fbla.parters.model.Tag;
import com.fbla.parters.service.PartnerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    private static final String PARTNERS_ATTRIBUTE = "partners";
    private static final String TAGS_ATTRIBUTE = "allTags";
    private static final String FILTER_ATTRIBUTE = "filter";

    @GetMapping("/partners")
    public String listPartners(@ModelAttribute("filter") FilterDTO filter, Model model, String keyword) {
        // model.addAttribute("partners", partnerService.findAll());

        if (filter != null && filter.getTagIds() != null) {
            Set<Tag> selectedTags = partnerService.findByTagIds(filter.getTagIds());
            List<Partner> partners = partnerService.filterItemsByTags(selectedTags);
            model.addAttribute(PARTNERS_ATTRIBUTE, partners);
        }

        List<Tag> allTags = partnerService.findAllTags();

        if (keyword != null) {
            model.addAttribute(PARTNERS_ATTRIBUTE, partnerService.findByKeyword(keyword));
        } else {
            model.addAttribute(PARTNERS_ATTRIBUTE, partnerService.findAll());
        }

        model.addAttribute(FILTER_ATTRIBUTE, new FilterDTO());

        model.addAttribute(TAGS_ATTRIBUTE, allTags);

        return "partners"; // Name of the Thymeleaf template
    }

    // Method to handle the deletion of a partner
    @PostMapping("/partners/{id}/delete")
    public String deletePartner(@PathVariable Long id) {
        partnerService.delete(id);
        return "redirect:/partners";
    }

    @PostMapping("/savepartner")
    public String savePartner(Partner partner, RedirectAttributes redirectAttributes) {
        partnerService.save(partner);
        redirectAttributes.addFlashAttribute("message", "Partner saved successfully!");
        return "redirect:/partners";
    }

    // Additional methods to handle search and filter requests
}
