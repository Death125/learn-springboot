package com.learnspringboot.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "headquarters")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Headquarter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "headquarter_generator")

    @SequenceGenerator(name = "headquarter_generator", sequenceName = "headquarter_sequence_value", allocationSize = 1)
    private Long id;

    private String name;

    @JsonIgnoreProperties("headquarter")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "headquarter_id", referencedColumnName = "id")
    private List<Hero> heroes;

    public void addHero(Hero hero) {
        if (heroes == null)
            heroes = new ArrayList<>();
        heroes.add(hero);
    }
}
