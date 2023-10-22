package com.learnspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnspringboot.models.Headquarter;

public interface HeadquarterRepository extends JpaRepository<Headquarter, Long> {

}
