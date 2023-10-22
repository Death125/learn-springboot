package com.learnspringboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnspringboot.dto.HeadquarterRequest;
import com.learnspringboot.models.Headquarter;
import com.learnspringboot.models.Hero;
import com.learnspringboot.repositories.HeadquarterRepository;
import com.learnspringboot.repositories.HeroRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HeadquarterService {

    @Autowired
    private HeroRepository heroRepository;
    private final HeadquarterRepository headquarterRepository;

    public Headquarter createHeadquarter(HeadquarterRequest headquarterRequest) {
        Headquarter headquarter = Headquarter.builder()
                .name(headquarterRequest.getName())
                // .heroes(Set.of(hero, hero2))
                // .heroes(null)
                .build();

        return headquarterRepository.save(headquarter);
    }

    public Headquarter createHeadQuarterWithHeroes(HeadquarterRequest headquarterRequest) {
        Hero hero = Hero.builder()
                .name("Saitama")
                .power(999)
                .build();

        Hero hero2 = Hero.builder()
                .name("Genzo")
                .power(222)
                .build();

        Hero hero3 = Hero.builder()
                .name("Tornado")
                .power(888)
                .build();

        Headquarter headquarter = Headquarter.builder()
                .name(headquarterRequest.getName())
                .heroes(List.of(hero, hero2, hero3))
                .build();

        return headquarterRepository.save(headquarter);
    }

    public Iterable<Headquarter> getListOfHeadquarter() {
        return headquarterRepository.findAll();
    }

    public Headquarter rentHero(Long headquarterId, Long heroId) {
        Headquarter headquarter = headquarterRepository.findById(headquarterId).get();
        Optional<Hero> hero = heroRepository.findById(heroId);

        if (hero.isPresent()) {
            headquarter.addHero(hero.get());
            return headquarterRepository.save(headquarter);
        } else {
            return null;
        }
    }

    // public Headquarter rentHero(Long headquarterId, Long heroId) {
    // Headquarter headquarter =
    // headquarterRepository.findById(headquarterId).get();
    // Hero hero = heroRepository.findById(heroId).get();

    // headquarter.addHero(hero);
    // return headquarterRepository.save(headquarter);
    // }
}