package com.example.fakecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetProductResponseDto {
    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private String image;

    private String rating;
}
