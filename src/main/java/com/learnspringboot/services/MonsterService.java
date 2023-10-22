package com.learnspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnspringboot.dto.MonsterRequest;
import com.learnspringboot.models.LegendaryMonster;
import com.learnspringboot.models.Monster;
import com.learnspringboot.models.MonsterAttribute;
import com.learnspringboot.repositories.MonsterRepository;

@Service
public class MonsterService {
    @Autowired
    private MonsterRepository monsterRepository;

    public Monster createMonster(MonsterRequest monsterRequest) {
        Monster monster = Monster.builder()
                .name(monsterRequest.getName())
                .power(monsterRequest.getPower())
                .dateCreated(monsterRequest.getDateCreated())
                .dateUpdated(monsterRequest.getDateUpdated())
                .build();

        return monsterRepository.save(monster);
    }

    public Monster createMonsterWithAttribute(MonsterRequest monsterRequest) {
        MonsterAttribute monsterAttribute = MonsterAttribute.builder()
                .attributeName("Dark")
                .attributePower(8999)
                .attributeRarity("Super Rare")
                .build();

        Monster monster = Monster.builder()
                .name(monsterRequest.getName())
                .power(monsterRequest.getPower())
                .monsterAttribute(monsterAttribute)
                .dateCreated(monsterRequest.getDateCreated())
                .dateUpdated(monsterRequest.getDateUpdated())
                .build();

        return monsterRepository.save(monster);
    }

    public List<Monster> getListMonster() {
        return monsterRepository.findAll();
    }

    public Monster createLegendaryMonster(MonsterRequest monsterRequest) {
        LegendaryMonster legendaryMonster = LegendaryMonster.builder()
                .monsterUltimate("Chaos")
                .ultimateCount(3)
                .build();

        Monster monster = Monster.builder()
                .name(monsterRequest.getName())
                .power(monsterRequest.getPower())
                .legendaryMonster(legendaryMonster)
                .dateCreated(monsterRequest.getDateCreated())
                .dateUpdated(monsterRequest.getDateUpdated())
                .build();

        return monsterRepository.save(monster);
    }

    public void updateMonsterNameByPower() {
        monsterRepository.updateMonsterNameByPower("Drakath", 0);
    }

}
