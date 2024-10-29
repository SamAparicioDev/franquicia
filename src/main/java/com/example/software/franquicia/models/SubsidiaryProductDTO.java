package com.example.software.franquicia.models;

import com.example.software.franquicia.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubsidiaryProductDTO {
    private String name;
    private ProductEntity product;
    private Long franchiseId;

    public SubsidiaryProductDTO(String name, Long franchiseId){
        this.name = name;
        this.franchiseId = franchiseId;
    }
}
