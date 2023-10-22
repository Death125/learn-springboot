package com.learnspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnspringboot.models.MonsterAttribute;

public interface MonsterAttributeRepo extends JpaRepository<MonsterAttribute, Long> {

}
