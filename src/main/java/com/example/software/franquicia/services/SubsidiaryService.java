package com.example.software.franquicia.services;

import com.example.software.franquicia.entities.SubsidiaryEntity;
import com.example.software.franquicia.models.SubsidiaryDTO;
import com.example.software.franquicia.models.SubsidiaryProductDTO;
import com.example.software.franquicia.models.SubsidiaryProductsDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubsidiaryService {
    Mono<SubsidiaryEntity> addSubsidiary(SubsidiaryDTO subsidiary);
    Mono<SubsidiaryEntity> updateSubsidiaryById(Long id,SubsidiaryDTO subsidiary);
    Flux<SubsidiaryProductsDTO> getSubsidiariesByFranchiseId(Long franchiseId);
    Flux<SubsidiaryProductDTO> getBySubsidiaryIdWithMostStockProduct(Long franchiseId);
    Mono<SubsidiaryProductsDTO> getSubsidiaryProducts(Long id);
    Flux<SubsidiaryProductsDTO> getAllSubsidiaryProducts();
}
