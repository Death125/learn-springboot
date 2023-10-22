package com.learnspringboot.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "monsterUltimate", column = @Column(name = "monster_ultimate")),
        @AttributeOverride(name = "ultimateCount", column = @Column(name = "ultimate_count"))
})
public class LegendaryMonster {
    private String monsterUltimate;
    private int ultimateCount;
}
