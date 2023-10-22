package com.learnspringboot.repositories;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.learnspringboot.models.Hero;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HeroRepositoryTest2 {
    @Autowired
    private HeroRepository heroRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveHeroTest() {
        Hero hero = Hero.builder()
                .name("Rizz")
                .power(980000)
                .build();

        heroRepository.save(hero);

        Assertions.assertThat(hero.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getHeroTest() {
        Hero hero = heroRepository.findById(1L).get();

        Assertions.assertThat(hero.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void getListOfHeroTest() {
        List<Hero> heroes = heroRepository.findAll();

        Assertions.assertThat(heroes.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateHeroTest() {
        Hero hero = heroRepository.findById(1L).get();

        hero.setPower(0);

        Hero heroUpdated = heroRepository.save(hero);

        Assertions.assertThat(heroUpdated.getPower()).isEqualTo(0);
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteHeroTest() {
        Hero hero = heroRepository.findById(1L).get();

        heroRepository.delete(hero);

        Hero hero1 = null;

        Optional<Hero> optionalHero = heroRepository.findByName("Rizz");

        if (optionalHero.isPresent()) {
            hero1 = optionalHero.get();
        } else {
            Assertions.assertThat(hero1).isNull();
        }
    }
}
