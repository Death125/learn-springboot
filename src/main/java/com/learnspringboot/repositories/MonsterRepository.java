package com.learnspringboot.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learnspringboot.models.Monster;

import jakarta.transaction.Transactional;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
    List<Monster> findByDateCreatedBetween(LocalDateTime start, LocalDateTime endDate);

    Optional<Monster> findByName(String name);

    @Query("SELECT m FROM Monster m WHERE " +
            "m.name LIKE CONCAT('%', :query, '%')" +
            "OR m.power LIKE CONCAT('%', :query, '%')")
    List<Monster> searchMonsters(String query);

    // native query & param
    @Query(value = "SELECT * FROM monsters WHERE monster_power = :monsterPower", nativeQuery = true)
    Monster findMonsterByPower(
            @Param("monsterPower") Integer monsterPower);

    // Native Query & Question Mark
    // You use transactional annotation when you perform any changes in your field
    // With Transactional Annotation we can perform any query method like create,
    // update , delete etc, than just fetching the data, we can added in service
    // layer.
    @Modifying
    @Transactional
    @Query(value = "UPDATE monsters SET monster_name = ?1 WHERE monster_power = ?2", nativeQuery = true)
    int updateMonsterNameByPower(String name, int power);
}
