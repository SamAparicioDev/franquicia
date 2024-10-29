package com.example.software.franquicia.repositories;

import com.example.software.franquicia.entities.SubsidiaryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SubsidiaryRepository extends ReactiveCrudRepository<SubsidiaryEntity,Long> {
    Flux<SubsidiaryEntity> getSubsidiaryByFranchiseId(Long franchiseId);
}
