package com.example.software.franquicia.mappers;

import com.example.software.franquicia.entities.FranchiseEntity;
import com.example.software.franquicia.models.FranchiseDTO;

public class FranchiseMapper {
    public static FranchiseEntity toEntity(FranchiseDTO franchiseDTO){
        return new FranchiseEntity(franchiseDTO.name());
    }
}
