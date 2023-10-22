package com.learnspringboot.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnspringboot.dto.MonsterRequest;
import com.learnspringboot.models.Monster;
import com.learnspringboot.services.MonsterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monster")
public class MonsterController {
    private final MonsterService monsterService;

    @GetMapping("/getListMonster")
    private ResponseEntity<List<Monster>> getListMonster() {
        return ResponseEntity.ok(monsterService.getListMonster());
    }

    @PostMapping("/create")
    private ResponseEntity<Monster> createMonster(@RequestBody @Valid MonsterRequest monsterRequest) {
        return new ResponseEntity<Monster>(monsterService.createMonster(monsterRequest), HttpStatus.CREATED);
    }

    @PostMapping("/createWithAttribute")
    private ResponseEntity<Monster> createMonsterWithAttribute(@RequestBody @Valid MonsterRequest monsterRequest) {
        return new ResponseEntity<Monster>(monsterService.createMonsterWithAttribute(monsterRequest),
                HttpStatus.CREATED);
    }

    @PostMapping("/createLegendaryMonster")
    private ResponseEntity<Monster> createLegendaryMonster(@RequestBody @Valid MonsterRequest monsterRequest) {
        return new ResponseEntity<Monster>(monsterService.createLegendaryMonster(monsterRequest), HttpStatus.CREATED);
    }

    @GetMapping("/updateByPower")
    private void update() {
        monsterService.updateMonsterNameByPower();
    }
}
