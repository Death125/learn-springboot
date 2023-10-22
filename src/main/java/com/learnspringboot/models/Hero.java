package com.learnspringboot.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
@Table(name = "heroes")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hero_generator")
    @SequenceGenerator(name = "hero_generator", sequenceName = "hero_sequence_value", allocationSize = 1)
    private Long id;

    @Column(name = "hero_name", nullable = false)
    String name;

    @Column(name = "hero_power", nullable = false)
    int power;

    @JsonIgnoreProperties("heroes")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "hero_monster_map", joinColumns = @JoinColumn(name = "monster_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "hero_id", referencedColumnName = "id"))
    private List<Monster> monsters;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "heroes" })
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "headquarter_id", referencedColumnName = "id")
    private Headquarter headquarter;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public Hero(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public void addMonster(Monster monster) {
        if (monsters == null)
            monsters = new ArrayList<>();
        monsters.add(monster);
    }
}