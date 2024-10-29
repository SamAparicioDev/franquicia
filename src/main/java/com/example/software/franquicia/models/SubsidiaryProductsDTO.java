package com.example.software.franquicia.models;

import com.example.software.franquicia.entities.ProductEntity;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class SubsidiaryProductsDTO{
    private String name;
    private List<ProductEntity> products;
    private Long franchiseId;

    public SubsidiaryProductsDTO(String name, Long franchiseId){
        this.name = name;
        this.franchiseId = franchiseId;
    }
}
