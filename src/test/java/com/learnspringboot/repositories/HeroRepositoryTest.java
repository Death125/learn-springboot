package com.learnspringboot.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.learnspringboot.models.Hero;
import com.learnspringboot.services.HeroService;

@SpringBootTest
public class HeroRepositoryTest {
    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroService heroService;

    @Test
    void saveMethod() {
        List<Hero> savedObject = heroRepository.saveAll(heroService.getAllHero());
        System.out.println(savedObject.size());
        savedObject.forEach((hero) -> {
            System.out.println(hero.getName());
            System.out.println(hero.getPower());
        });
    }

    @Test
    void countMethod() {
        long count = heroRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod() {
        Long id = 9L;

        boolean result = heroRepository.existsById(id);
        System.out.println("Hero With ID " + id + " is " + result);
    }

    @Test
    void findByDateCreatedBetweenMethod() {
        LocalDateTime startDate = LocalDateTime.of(2023, 10, 18, 19, 00, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 10, 18, 21, 30, 0);

        List<Hero> heroes = heroRepository.findByDateCreatedBetween(startDate, endDate);
        heroes.forEach((h) -> {
            System.out.println(h.getId() + "-");
            System.out.print(h.getName() + "-");
            System.out.print(h.getPower());
        });
    }

    @Test
    void sortingMethod() {
        String sortBy = "power";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        List<Hero> heroes = heroRepository.findAll(sort);
        heroes.forEach((h) -> {
            System.out.println(h);
        });
    }

    @Test
    void sortingByMultipleFields() {
        String sortBy = "power";
        String sortByNam = "name";
        String sortDir = "desc";

        Sort sortByPower = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortByNam).ascending()
                : Sort.by(sortByNam).descending();

        Sort groupBySort = sortByPower.and(sortByName);

        List<Hero> heroes = heroRepository.findAll(groupBySort);

        heroes.forEach((h) -> {
            System.out.println(h);
        });
    }

}