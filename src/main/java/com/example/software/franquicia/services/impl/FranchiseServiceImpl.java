package com.example.software.franquicia.services.impl;

import com.example.software.franquicia.entities.FranchiseEntity;
import com.example.software.franquicia.exceptions.service.AlreadyExistsException;
import com.example.software.franquicia.exceptions.service.NotFoundException;
import com.example.software.franquicia.mappers.FranchiseMapper;
import com.example.software.franquicia.models.FranchiseDTO;
import com.example.software.franquicia.models.FranchiseSubsidiarieDTO;
import com.example.software.franquicia.models.FranchiseSubsidiaryDTO;
import com.example.software.franquicia.repositories.FranchiseRepository;
import com.example.software.franquicia.services.FranchiseService;
import com.example.software.franquicia.services.ProductService;
import com.example.software.franquicia.services.SubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FranchiseServiceImpl implements FranchiseService {
    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private SubsidiaryService subsidiaryService;
    @Autowired
    private ProductService productService;




    @Override
    public Mono<FranchiseEntity> addFranchise(FranchiseDTO franchise) {
        return franchiseRepository.save(FranchiseMapper.toEntity(franchise))
                .onErrorResume(e -> Mono.error(new AlreadyExistsException("Franchise already exists")));
    }

    @Override
    public Mono<FranchiseEntity> updateFranchiseById(Long id,FranchiseDTO franchise) {
        return franchiseRepository.findById(id).flatMap((franchiseEntity)->{
            franchiseEntity.setName(franchise.name());
            return franchiseRepository.save(franchiseEntity);
        });
    }

    @Override
    public Mono<FranchiseSubsidiaryDTO> getFranchiseById(Long id) {
        return franchiseRepository.findById(id)
                .flatMap((franchise) ->{
                    FranchiseSubsidiaryDTO franchiseSubsidiaryDTO = new FranchiseSubsidiaryDTO(franchise.getName());
                    return subsidiaryService.getSubsidiariesByFranchiseId(franchise.getId())
                            .collectList()
                            .flatMap((subsidiaries)->{
                                franchiseSubsidiaryDTO.setSubsidiaries(subsidiaries);
                                return Mono.just(franchiseSubsidiaryDTO);
                            })
                            .switchIfEmpty(Mono.error(new NotFoundException("Franchise not found")));
                });
    }
    @Override
    public Mono<FranchiseSubsidiarieDTO> getFranchiseWithMostProductPerSubsidiary(Long id) {
        return franchiseRepository.findById(id)
                .flatMap(franchise -> {
                    FranchiseSubsidiarieDTO franchiseSubsidiaryDTO = new FranchiseSubsidiarieDTO(franchise.getName());
                    return subsidiaryService.getBySubsidiaryIdWithMostStockProduct(franchise.getId())
                            .collectList()
                            .flatMap(subsidiaries -> {
                                franchiseSubsidiaryDTO.setSubsidiaries(subsidiaries);
                                return Mono.just(franchiseSubsidiaryDTO);
                            })
                            .switchIfEmpty(Mono.error(new NotFoundException("No subsidiaries found for this franchise.")));
                })
                .switchIfEmpty(Mono.error(new NotFoundException("Franchise not found")));
    }


    @Override
    public Flux<FranchiseSubsidiaryDTO> getAllFranchises() {
        return franchiseRepository.findAll()
                .flatMap(franchises->
                        subsidiaryService.getSubsidiariesByFranchiseId(franchises.getId())
                                .collectList()
                                .flatMap((subsidiaries)->{
                                    FranchiseSubsidiaryDTO franchiseSubsidiaryDTO = new FranchiseSubsidiaryDTO(franchises.getName());
                                    franchiseSubsidiaryDTO.setSubsidiaries(subsidiaries);
                                    return Mono.just(franchiseSubsidiaryDTO);
                                }));
    }
}
