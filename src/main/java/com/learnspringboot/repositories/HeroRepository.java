package com.learnspringboot.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learnspringboot.models.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findByDateCreatedBetween(LocalDateTime start, LocalDateTime endDate);

    Optional<Hero> findByName(String name);

    @Query("SELECT h FROM Hero h WHERE " +
            "h.name LIKE CONCAT('%', :query, '%')" +
            "OR h.power LIKE CONCAT('%', :query, '%')")
    List<Hero> searchHeroes(String query);

    Page<Hero> findByNameContaining(
            String name,
            Pageable pageRequest);
}