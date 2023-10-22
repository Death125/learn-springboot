package com.learnspringboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnspringboot.dto.HeadquarterRequest;
import com.learnspringboot.models.Headquarter;
import com.learnspringboot.services.HeadquarterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/headquarter")
public class HeadquarterController {
    private final HeadquarterService headquarterService;

    @GetMapping("/getListOfHeadquarter")
    private ResponseEntity<Iterable<Headquarter>> getListOfHeadquater() {
        return ResponseEntity.ok(headquarterService.getListOfHeadquarter());
    }

    @PostMapping("/create")
    private ResponseEntity<Headquarter> createHeadquarter(@RequestBody @Valid HeadquarterRequest headquarterRequest) {
        return new ResponseEntity<Headquarter>(headquarterService.createHeadquarter(headquarterRequest),
                HttpStatus.CREATED);
    }

    @PostMapping("/createWithHeroes")
    private ResponseEntity<Headquarter> createHeadQuarterWithHeroes(
            @RequestBody @Valid HeadquarterRequest headquarterRequest) {
        return new ResponseEntity<Headquarter>(headquarterService.createHeadQuarterWithHeroes(headquarterRequest),
                HttpStatus.CREATED);
    }

    @PutMapping("/{headquarter_id}/hero/{hero_id}")
    private ResponseEntity<Headquarter> rentHero(
            @PathVariable Long headquarter_id,
            @PathVariable Long hero_id) {
        return ResponseEntity.ok(headquarterService.rentHero(headquarter_id, hero_id));
    }
}
