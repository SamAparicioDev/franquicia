package com.example.software.franquicia.services;

import com.example.software.franquicia.entities.FranchiseEntity;
import com.example.software.franquicia.models.FranchiseDTO;
import com.example.software.franquicia.models.FranchiseSubsidiarieDTO;
import com.example.software.franquicia.models.FranchiseSubsidiaryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    Mono<FranchiseSubsidiarieDTO> getFranchiseWithMostProductPerSubsidiary(Long id);
    Mono<FranchiseEntity> addFranchise(FranchiseDTO franchise);
    Mono<FranchiseEntity> updateFranchiseById(Long id,FranchiseDTO franchise);
    Mono<FranchiseSubsidiaryDTO> getFranchiseById(Long id);
    Flux<FranchiseSubsidiaryDTO> getAllFranchises();
}
