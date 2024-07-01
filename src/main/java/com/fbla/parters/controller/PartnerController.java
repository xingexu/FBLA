package com.fbla.parters.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fbla.parters.model.FilterDTO;
import com.fbla.parters.model.Partner;
import com.fbla.parters.model.Tag;
import com.fbla.parters.service.PartnerService;
import com.fbla.parters.service.icon.IconService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private IconService iconService;

    private static final String PARTNERS_ATTRIBUTE = "partners";
    private static final String TAGS_ATTRIBUTE = "allTags";
    private static final String FILTER_ATTRIBUTE = "filter";

    @GetMapping("/partners")
    public String listPartners(@ModelAttribute("filter") FilterDTO filter, Model model, String keyword) {

        var filterDto = new FilterDTO();
        List<Tag> allTags = partnerService.findAllTags();

        if (StringUtils.hasText(keyword)) {

            if (filter != null && !CollectionUtils.isEmpty(filter.getTagIds())) {
                filterDto.setTagIds(filter.getTagIds());
                Set<Tag> selectedTags = partnerService.findByTagIds(filter.getTagIds());
                List<Partner> partners = partnerService.filterItemsByTagsAndKeyword(selectedTags, keyword);
                model.addAttribute(PARTNERS_ATTRIBUTE, partners);
            } else {
                model.addAttribute(PARTNERS_ATTRIBUTE, partnerService.findByKeyword(keyword));
                filterDto.setTagIds(allTags.stream().map(tag -> tag.getId()).collect(Collectors.toSet()));
            }

        } else {

            if (filter != null && !CollectionUtils.isEmpty(filter.getTagIds())) {
                filterDto.setTagIds(filter.getTagIds());
                Set<Tag> selectedTags = partnerService.findByTagIds(filter.getTagIds());
                List<Partner> partners = partnerService.filterItemsByTags(selectedTags);
                model.addAttribute(PARTNERS_ATTRIBUTE, partners);
            } else {

                model.addAttribute(PARTNERS_ATTRIBUTE, partnerService.findAll());
                filterDto.setTagIds(allTags.stream().map(tag -> tag.getId()).collect(Collectors.toSet()));
            }
        }

        model.addAttribute(FILTER_ATTRIBUTE, filterDto);
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
    public String savePartner(@ModelAttribute Partner partner, @RequestParam("partnerImage") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            String iconUrl;
            try {
                iconUrl = iconService.uploadIcon(file);
                partner.setLogoPath(iconUrl);
            } catch (IOException e) {
                partner.setLogoPath(fileName);
            }
        }

        partnerService.save(partner);
        redirectAttributes.addFlashAttribute("message", "Partner saved successfully!");
        return "redirect:/partners";
    }
}
