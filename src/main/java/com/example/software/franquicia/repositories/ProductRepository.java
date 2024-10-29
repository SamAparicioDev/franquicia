package com.example.software.franquicia.repositories;

import com.example.software.franquicia.entities.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity,Long> {
    Flux<ProductEntity> getProductBySubsidiaryId(Long subsidiaryId);
}
