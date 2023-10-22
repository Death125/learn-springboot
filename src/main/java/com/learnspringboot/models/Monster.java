package com.learnspringboot.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "monsters")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Monster {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monster_generator")
    @SequenceGenerator(name = "monster_generator", sequenceName = "monster_sequence_value", allocationSize = 1)
    private Long id;

    @Column(name = "monster_name", nullable = false)
    private String name;

    @Column(name = "monster_power", nullable = false)
    private int power;

    @JsonIgnoreProperties("monsters")
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "monsters")
    private List<Hero> heroes;

    @Embedded
    private LegendaryMonster legendaryMonster;

    @JsonIgnoreProperties("monster")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "monsterAttribute_id", referencedColumnName = "id")
    private MonsterAttribute monsterAttribute;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;
}
