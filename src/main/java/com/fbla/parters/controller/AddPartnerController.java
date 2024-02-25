package com.fbla.parters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fbla.parters.model.Partner;
import com.fbla.parters.model.Tag;
import com.fbla.parters.service.PartnerService;

@Controller
public class AddPartnerController {

    private static final String TAGS_ATTRIBUTE = "allTags";
    @Autowired
    private PartnerService partnerService;

    // Method to display the form
    @GetMapping("/addpartner")
    public String showAddPartnerForm(Model model) {
        List<Tag> allTags = partnerService.findAllTags();
        model.addAttribute("partner", new Partner());
        model.addAttribute(TAGS_ATTRIBUTE, allTags);
        return "add-partner";
    }

    // Method to process the form submission
    @PostMapping("/addpartner")
    public String addPartner(Partner partner, RedirectAttributes redirectAttributes) {
        partnerService.save(partner);
        redirectAttributes.addFlashAttribute("message", "Partner added successfully!");
        return "redirect:/partners"; // Redirect to the list of partners
    }

    @PostMapping("/deletepartner/{id}")
    public String deletePartner(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        partnerService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Partner deleted successfully!");
        return "redirect:/partners"; // Redirect back to the list of partners
    }

}
