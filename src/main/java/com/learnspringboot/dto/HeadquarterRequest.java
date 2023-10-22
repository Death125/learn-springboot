package com.learnspringboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HeadquarterRequest {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
