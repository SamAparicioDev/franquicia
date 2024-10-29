package com.example.software.franquicia.repositories;

import com.example.software.franquicia.entities.FranchiseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FranchiseRepository extends ReactiveCrudRepository<FranchiseEntity,Long> {
}
