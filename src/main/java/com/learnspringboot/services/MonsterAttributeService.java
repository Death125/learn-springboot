package com.learnspringboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learnspringboot.dto.MonsterAttributeRequest;
import com.learnspringboot.models.MonsterAttribute;
import com.learnspringboot.repositories.MonsterAttributeRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MonsterAttributeService {
    private final MonsterAttributeRepo attributeRepo;

    public MonsterAttribute createMonsterAttribute(MonsterAttributeRequest attributeRequest) {
        MonsterAttribute monsterAttribute = MonsterAttribute.builder()
                .id(0L)
                .attributeName(attributeRequest.getAttributeName())
                .attributePower(attributeRequest.getAttributePower())
                .attributeRarity(attributeRequest.getAttributeRarity())
                .build();

        return attributeRepo.save(monsterAttribute);
    }

    public List<MonsterAttribute> getListMonsterAttribute() {
        return attributeRepo.findAll();
    }
}
