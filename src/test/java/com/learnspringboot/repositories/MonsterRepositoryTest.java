package com.learnspringboot.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MonsterRepositoryTest {

    @Autowired
    private MonsterRepository monsterRepository;

    @Test
    void testUpdateMonsterNameByPower() {
        monsterRepository.updateMonsterNameByPower("Zelda", 444);
    }
}
