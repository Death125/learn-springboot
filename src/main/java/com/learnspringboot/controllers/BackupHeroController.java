package com.learnspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.learnspringboot.models.Hero;
import com.learnspringboot.services.HeroService;

public class BackupHeroController {
    @Autowired
    private HeroService heroService;

    public List<String> getHeroName() {
        return heroService.getHeroName();
    }

    public List<Hero> getAllHero() {
        return heroService.getAllHero();
    }

    public void saveHero() {
        heroService.saveHeroIntoFile("Hero.txt");
    }

    public void loadHero() {
        heroService.loadHeroFromFile();
    }

    public List<String> getLegendaryHeroes() {
        return heroService.getLegendaryHeroes();
    }

    public void init() {
        System.out.println("Initialization Logic");
    }

    public void destroy() {
        System.out.println("Destruction Logic");
    }
}
