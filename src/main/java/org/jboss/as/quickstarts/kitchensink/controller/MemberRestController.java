package org.jboss.as.quickstarts.kitchensink.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Member API", description = "Member management endpoints")
public class MemberRestController {

    private final MemberRegistration service;

    public MemberRestController(MemberRegistration service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all members")
    public List<Member> getAll() {
        return service.getAllMembers();
    }

    @PostMapping
    @Operation(summary = "Create a new member")
    public ResponseEntity<Void> create(@RequestBody @Valid Member member) {
        Member created = service.registerMember(member);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
