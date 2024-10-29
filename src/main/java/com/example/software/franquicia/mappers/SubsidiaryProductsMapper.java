package com.example.software.franquicia.mappers;

import com.example.software.franquicia.entities.SubsidiaryEntity;
import com.example.software.franquicia.models.SubsidiaryProductsDTO;

public class SubsidiaryProductsMapper {
    public static SubsidiaryProductsDTO toSubsidiaryProductsDTO(SubsidiaryEntity subsidiaryEntity) {
        return new SubsidiaryProductsDTO(subsidiaryEntity.getName(), subsidiaryEntity.getFranchiseId());
    }
}
