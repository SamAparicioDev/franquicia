package com.example.software.franquicia.controllers;

import com.example.software.franquicia.entities.FranchiseEntity;
import com.example.software.franquicia.models.FranchiseDTO;
import com.example.software.franquicia.models.FranchiseSubsidiarieDTO;
import com.example.software.franquicia.models.FranchiseSubsidiaryDTO;
import com.example.software.franquicia.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("api/franchise")
@RestController
public class FranchiseController {
    @Autowired
    private FranchiseService franchiseService;

    @PostMapping
    public ResponseEntity<Mono<FranchiseEntity>> addFranchise(@RequestBody FranchiseDTO franchise) {
        return ResponseEntity.status(HttpStatus.CREATED).body(franchiseService.addFranchise(franchise));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Mono<FranchiseSubsidiaryDTO>> getSubsidiaryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(franchiseService.getFranchiseById(id));
    }
    @GetMapping("/get")
    public ResponseEntity<Flux<FranchiseSubsidiaryDTO>> getAllSubsidiaries() {
        return  ResponseEntity.status(HttpStatus.OK).body(franchiseService.getAllFranchises());
    }
    @GetMapping("/get/max/{id}")
    public ResponseEntity<Mono<FranchiseSubsidiarieDTO>> getFranchiseWithMostProductPerSubsidiary(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(franchiseService.getFranchiseWithMostProductPerSubsidiary(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<FranchiseEntity>> updateFranchise(@PathVariable Long id, @RequestBody FranchiseDTO franchise) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(franchiseService.updateFranchiseById(id, franchise));
    }

}
