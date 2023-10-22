package com.learnspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnspringboot.dto.HeroRequest;
import com.learnspringboot.exception.HeroNotFoundException;
import com.learnspringboot.models.Hero;
import com.learnspringboot.services.HeroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hero")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @PostMapping("/create")
    public ResponseEntity<Hero> createHero(@RequestBody @Valid HeroRequest heroRequest) {
        return new ResponseEntity<Hero>(heroService.createHero(heroRequest), HttpStatus.CREATED);
    }

    @PostMapping("/createWithHeadquarter")
    public ResponseEntity<Hero> createHeroWithHeadquarters(@RequestBody @Valid HeroRequest heroRequest) {
        return new ResponseEntity<Hero>(heroService.createHeroWithHeadquarters(heroRequest), HttpStatus.CREATED);
    }

    @PostMapping("/createWithMonsterHeadquarter")
    public ResponseEntity<Hero> createHeroWithMonsterHeadquarter(@RequestBody @Valid HeroRequest heroRequest) {
        return new ResponseEntity<Hero>(heroService.saveHeroWithMonsterAndHeadQuarter(heroRequest), HttpStatus.CREATED);
    }

    @GetMapping("/createMany")
    public ResponseEntity<List<Hero>> createManyHero() {
        return ResponseEntity.ok(heroService.createManyHero());
    }

    @GetMapping("/getListHero")
    public ResponseEntity<List<Hero>> getListHero() {
        return ResponseEntity.ok(heroService.getListHero());
    }

    @PutMapping("/update")
    public ResponseEntity<Hero> updateHero(@RequestBody @Valid HeroRequest heroRequest) throws HeroNotFoundException {
        return new ResponseEntity<Hero>(heroService.updateHero(heroRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findSingleHero/{id}")
    public ResponseEntity<Hero> findSingleHero(@PathVariable Long id) {
        return ResponseEntity.ok(heroService.findSingleHero(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHero(@PathVariable Long id) {
        heroService.deleteHero(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam("query") String query) {
        return ResponseEntity.ok(heroService.searchHeroes(query));
    }

    @GetMapping("/getPage")
    public ResponseEntity<List<Hero>> findAllPagination() {
        return ResponseEntity.ok(heroService.findAllPagination());
    }

    @GetMapping("/getSortedPage")
    public ResponseEntity<List<Hero>> findAllSorting() {
        return ResponseEntity.ok(heroService.findAllSorting());
    }

    @GetMapping("/findByNameContaining")
    public ResponseEntity<Page<Hero>> printFindByNameContaining() {
        return ResponseEntity.ok(heroService.printFindByNameContaining());
    }

    @PutMapping("/{hero_id}/monster/{monster_id}")
    public ResponseEntity<Hero> fightAgaintsMonster(
            @PathVariable Long hero_id,
            @PathVariable Long monster_id) {
        return new ResponseEntity<Hero>(heroService.fightAgainstMonster(hero_id,
                monster_id), HttpStatus.CREATED);
    }

    public void init() {
        System.out.println("Initialization Logic");
    }

    public void destroy() {
        System.out.println("Destruction Logic");
    }
}