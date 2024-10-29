package com.example.software.franquicia.mappers;

import com.example.software.franquicia.entities.SubsidiaryEntity;
import com.example.software.franquicia.models.SubsidiaryDTO;

public class SubsidiaryMapper {
    public static SubsidiaryEntity toEntity(SubsidiaryDTO subsidiaryDTO){
        return new SubsidiaryEntity(subsidiaryDTO.name(), subsidiaryDTO.franchiseId());
    }
}
