package com.learnspringboot.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learnspringboot.dto.HeroRequest;
import com.learnspringboot.enums.LegendaryHeroEnum;
import com.learnspringboot.exception.HeroNotFoundException;
import com.learnspringboot.models.Headquarter;
import com.learnspringboot.models.Hero;
import com.learnspringboot.models.Monster;
import com.learnspringboot.models.MonsterAttribute;
import com.learnspringboot.repositories.HeroRepository;
import com.learnspringboot.repositories.MonsterRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private MonsterRepository monsterRepository;

    public List<String> getHeroName() {
        List<String> heroListStream = getAllHero().stream()
                .map(Hero::getName)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return heroListStream;
    }

    public List<Hero> getAllHero() {
        List<Hero> heroList = new ArrayList<>();

        Hero saitama = new Hero("Saitama", 99999999);
        Hero ren = new Hero("Ren", 28000);
        Hero seria = new Hero("Seria", 90000);
        Hero zed = new Hero("Zed", 0);

        heroList.add(saitama);
        heroList.add(ren);
        heroList.add(seria);
        heroList.add(zed);

        return heroList;
    }

    public void saveHeroIntoFile(String fileName) {
        try {
            File file = new File("src/main/java/com/learnspringboot/save_data/" + fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (var hero : getAllHero()) {
                writer.write(hero.getName() + "-");
                writer.write(hero.getPower() + "\n");
            }

            System.out.println("Save Successfully");
            writer.close();
        } catch (IOException ex) {
            System.out.println("Save Failed " + ex.getMessage());
        }
    }

    public void loadHeroFromFile() {
        try {
            File fileName = new File("src/main/java/com/learnspringboot/save_data/hero.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            Files.lines(Path.of("src/main/java/com/learnspringboot/save_data/hero.txt"))
                    .forEach(line -> System.out.println(line));

            System.out.println("Load Successfully");
            reader.close();
        } catch (IOException ex) {
            System.out.println("Load Failed " + ex.getMessage());
        }
    }

    public List<String> getLegendaryHeroes() {
        List<LegendaryHeroEnum> legendaryHeroes = List.of(
                LegendaryHeroEnum.values());

        List<String> legendaryHeroesStream = legendaryHeroes.stream()
                .map(LegendaryHeroEnum::toString)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return legendaryHeroesStream;
    }

    public Hero createHero(HeroRequest heroRequest) {
        Hero hero = Hero.builder()
                .name(heroRequest.getName())
                .power(heroRequest.getPower())
                .dateCreated(heroRequest.getDateCreated())
                .dateUpdated(heroRequest.getDateUpdated())
                .build();

        return heroRepository.save(hero);
    }

    public Hero createHeroWithHeadquarters(HeroRequest heroRequest) {
        Headquarter headquarter = Headquarter.builder()
                .name("Osaka")
                .build();

        Hero hero = Hero.builder()
                .name(heroRequest.getName())
                .power(heroRequest.getPower())
                .headquarter(headquarter)
                .dateCreated(heroRequest.getDateCreated())
                .dateUpdated(heroRequest.getDateUpdated())
                .build();

        return heroRepository.save(hero);
    }

    public List<Hero> createManyHero() {
        return heroRepository.saveAll(getAllHero());
    }

    public List<Hero> getListHero() {
        return heroRepository.findAll();
    }

    public Hero fightAgainstMonster(Long heroId, Long monsterId) {
        Hero hero = heroRepository.findById(heroId).get();
        Monster monster = monsterRepository.findById(monsterId).get();

        hero.addMonster(monster);
        return heroRepository.save(hero);
    }

    public Hero saveHeroWithMonsterAndHeadQuarter(HeroRequest heroRequest) {
        MonsterAttribute monsterAttribute = MonsterAttribute.builder()
                .attributeName("Light")
                .attributePower(9999)
                .attributeRarity("Very Rare")
                .build();

        Headquarter headquarter = Headquarter.builder()
                .name("Tokyo")
                .build();

        Monster monster = Monster.builder()
                .name("Gorilla")
                .power(999)
                .monsterAttribute(monsterAttribute)
                .build();

        Hero hero = Hero.builder()
                .name(heroRequest.getName())
                .power(heroRequest.getPower())
                .headquarter(headquarter)
                .build();

        hero.addMonster(monster);

        return heroRepository.save(hero);
    }

    public Hero updateHero(HeroRequest updatedHero) throws HeroNotFoundException {
        Optional<Hero> hero = heroRepository.findById(updatedHero.getId());

        if (hero.isPresent()) {
            hero.get().setName(updatedHero.getName());
            hero.get().setPower(updatedHero.getPower());

            return heroRepository.save(hero.get());
        } else {
            throw new HeroNotFoundException("Hero with id " + updatedHero.getId() + " Not Found");
        }
    }

    public Hero findSingleHero(Long id) {
        Optional<Hero> hero = heroRepository.findById(id);

        if (hero.isPresent()) {
            return hero.get();
        } else {
            return null;
        }
    }

    public void deleteHero(Long id) {
        heroRepository.deleteById(id);
    }

    public List<Hero> searchHeroes(String query) {
        List<Hero> heroes = heroRepository.searchHeroes(query);
        return heroes;
    }

    public List<Hero> findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithThreeRecords = PageRequest.of(0, 2);

        long totalElements = heroRepository.findAll(secondPageWithThreeRecords).getTotalElements();
        long totalPages = heroRepository.findAll(secondPageWithThreeRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        List<Hero> heroes = heroRepository.findAll(secondPageWithThreeRecords).getContent();
        return heroes;
    }

    public List<Hero> findAllSorting() {
        Pageable sortByName = PageRequest.of(0, 2, Sort.by("name"));
        Pageable sortByPowerDesc = PageRequest.of(0, 2, Sort.by("power").descending());

        Pageable sortByNameAndPowerDesc = PageRequest.of(0, 2, Sort.by("name").descending().and(Sort.by("power")));

        List<Hero> heroes = heroRepository.findAll(sortByNameAndPowerDesc).getContent();

        return heroes;
    }

    public Page<Hero> printFindByNameContaining() {
        Pageable firstPageTenRecords = PageRequest.of(0, 10);

        Page<Hero> heroes = heroRepository.findByNameContaining("S", firstPageTenRecords);

        return heroes;
    }
}
