package org.jboss.as.quickstarts.kitchensink.controller;

import jakarta.validation.Valid;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.jboss.as.quickstarts.kitchensink.util.MemberListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberRegistration service;
    private final MemberListHolder memberListHolder;

    public MemberController(MemberRegistration service, MemberListHolder memberListHolder) {
        this.service = service;
        this.memberListHolder = memberListHolder;
    }

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberListHolder.getMembers());
        model.addAttribute("member", new Member(null, "", "", ""));
        return "members/list";
    }

    @PostMapping
    public String addMember(@Valid @ModelAttribute("member") Member member,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("members", memberListHolder.getMembers());
            return "members/list"; // Re-render form with errors
        }
        try{
            service.registerMember(member);
        } catch (IllegalStateException e) {
            // Custom business validation errors
            if (e.getMessage().contains("Phone")) {
                result.rejectValue("phoneNumber", "error.member", e.getMessage());
            } else {
                result.rejectValue("email", "error.member", e.getMessage());
            }
            model.addAttribute("members", memberListHolder.getMembers());
            return "members/list";
        }
        return "redirect:/members?success";
    }
}
