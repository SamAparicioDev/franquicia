package com.example.software.franquicia.mappers;

import com.example.software.franquicia.entities.ProductEntity;
import com.example.software.franquicia.models.ProductDTO;

public class ProductMapper {
    public static ProductEntity toEntity(ProductDTO dto) {
        return new ProductEntity(dto.name(), dto.stock(), dto.description(),dto.price(), dto.subsidiaryId());
    }
}
