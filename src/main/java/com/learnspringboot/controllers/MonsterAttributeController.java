package com.learnspringboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnspringboot.dto.MonsterAttributeRequest;
import com.learnspringboot.models.MonsterAttribute;
import com.learnspringboot.services.MonsterAttributeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/monster/attribute")
@RequiredArgsConstructor
public class MonsterAttributeController {
    private final MonsterAttributeService attributeService;

    @PostMapping("/create")
    private ResponseEntity<MonsterAttribute> createMonsterAttribute(
            @RequestBody MonsterAttributeRequest monsterAttribute) {
        return ResponseEntity.ok(attributeService.createMonsterAttribute(monsterAttribute));
    }

    @GetMapping("/getAllMonsterAttribute")
    private ResponseEntity<List<MonsterAttribute>> getAllMonsterAttribute() {
        return ResponseEntity.ok(attributeService.getListMonsterAttribute());
    }
}
