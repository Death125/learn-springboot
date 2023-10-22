package com.learnspringboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonsterAttributeRequest {
    private Long id;

    @NotEmpty(message = "attribute name cannot be empty")
    private String attributeName;

    @NotEmpty(message = "attribute power cannot be empty")
    private Integer attributePower;

    @NotEmpty(message = "attribute rarity cannot be empty")
    private String attributeRarity;
}
