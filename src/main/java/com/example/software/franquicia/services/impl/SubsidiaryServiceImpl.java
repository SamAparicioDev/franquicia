package com.example.software.franquicia.services.impl;

import com.example.software.franquicia.entities.SubsidiaryEntity;
import com.example.software.franquicia.exceptions.service.NotFoundException;
import com.example.software.franquicia.mappers.SubsidiaryMapper;
import com.example.software.franquicia.models.SubsidiaryDTO;
import com.example.software.franquicia.models.SubsidiaryProductDTO;
import com.example.software.franquicia.models.SubsidiaryProductsDTO;
import com.example.software.franquicia.repositories.SubsidiaryRepository;
import com.example.software.franquicia.services.SubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class SubsidiaryServiceImpl implements SubsidiaryService {
    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Override
    public Mono<SubsidiaryEntity> addSubsidiary(SubsidiaryDTO subsidiary) {
        return subsidiaryRepository.save(SubsidiaryMapper.toEntity(subsidiary))
                .onErrorResume(e -> Mono.error(new NotFoundException("Subsidiary Already Exists")));
    }

    @Override
    public Mono<SubsidiaryEntity> updateSubsidiaryById(Long id,SubsidiaryDTO subsidiary) {
        return subsidiaryRepository.findById(id)
                .flatMap((subsidiaryEntity -> {
                    subsidiaryEntity.setName(subsidiary.name());
                    subsidiaryEntity.setFranchiseId(subsidiary.franchiseId());
                    return subsidiaryRepository
                            .save(subsidiaryEntity);
                }));
    }

    @Override
    public Flux<SubsidiaryProductsDTO> getSubsidiariesByFranchiseId(Long franchiseId) {
        return subsidiaryRepository.getSubsidiaryByFranchiseId(franchiseId)
                .flatMap((subsidiaries) -> {
                    SubsidiaryProductsDTO subsidiaryDTO = new SubsidiaryProductsDTO(subsidiaries.getName(), subsidiaries.getFranchiseId());
                    return productServiceImpl.getProductBySubsidiaryId(subsidiaries.getId())
                            .collectList()
                            .flatMap((products)->{
                                subsidiaryDTO.setProducts(products);
                                return Mono.just(subsidiaryDTO);
                            });

                });
    }

    @Override
    public Flux<SubsidiaryProductDTO> getBySubsidiaryIdWithMostStockProduct(Long franchiseId) {
        return subsidiaryRepository.getSubsidiaryByFranchiseId(franchiseId)
                .flatMap(subsidiaryEntity ->
                        productServiceImpl.getProductWithMaxStockBySubsidiaryId(subsidiaryEntity.getId())
                                .map(product -> {
                                    SubsidiaryProductDTO dto = new SubsidiaryProductDTO(subsidiaryEntity.getName(), subsidiaryEntity.getFranchiseId());
                                    dto.setProduct(product);
                                    return dto;
                                })
                                .defaultIfEmpty(new SubsidiaryProductDTO(subsidiaryEntity.getName(), subsidiaryEntity.getFranchiseId())) // Manejo de caso sin productos
                );
    }

    @Override
    public Mono<SubsidiaryProductsDTO> getSubsidiaryProducts(Long id) {
       return subsidiaryRepository.findById(id)
               .flatMap((subsidiaryEntity) -> {
                   SubsidiaryProductsDTO subsidiaryProductsDTO = new SubsidiaryProductsDTO(subsidiaryEntity.getName(), subsidiaryEntity.getFranchiseId());
                   return productServiceImpl.getProductBySubsidiaryId(subsidiaryEntity.getId())
                           .collectList()
                           .flatMap((products) ->{
                               subsidiaryProductsDTO.setProducts(products);
                               return Mono.just(subsidiaryProductsDTO);
                           });
               })
               .switchIfEmpty(Mono.error(new NotFoundException("Subsidiary not found")));

    }

    @Override
    public Flux<SubsidiaryProductsDTO> getAllSubsidiaryProducts() {
        return subsidiaryRepository.findAll()
                .flatMap((subsidiariesEntities) ->
                    productServiceImpl.getProductBySubsidiaryId(subsidiariesEntities.getId())
                            .collectList()
                            .flatMap((products)->{
                                SubsidiaryProductsDTO subsidiaryProductsDTO = new SubsidiaryProductsDTO(subsidiariesEntities.getName(), subsidiariesEntities.getId());
                                subsidiaryProductsDTO.setProducts(products);
                                return Mono.just(subsidiaryProductsDTO);
                            })
                );
    }


}
