package com.example.software.franquicia.controllers;

import com.example.software.franquicia.entities.SubsidiaryEntity;
import com.example.software.franquicia.models.SubsidiaryDTO;
import com.example.software.franquicia.models.SubsidiaryProductsDTO;
import com.example.software.franquicia.services.SubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/subsidiary")
public class SubsidiaryController {
    @Autowired
    private SubsidiaryService subsidiaryService;

    @PostMapping
    public ResponseEntity<Mono<SubsidiaryEntity>> addSubsidiary(@RequestBody SubsidiaryDTO subsidiary) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subsidiaryService.addSubsidiary(subsidiary));
    }
    @GetMapping("/franchise/{franchiseId}")
    public ResponseEntity<Flux<SubsidiaryProductsDTO>> getAllSubsidiariesByFranchise(@PathVariable Long franchiseId) {
        return ResponseEntity.status(HttpStatus.OK).body(subsidiaryService.getSubsidiariesByFranchiseId(franchiseId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Mono<SubsidiaryProductsDTO>> getSubsidiaryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subsidiaryService.getSubsidiaryProducts(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Flux<SubsidiaryProductsDTO>> getAllSubsidiaries() {
        return  ResponseEntity.status(HttpStatus.OK).body(subsidiaryService.getAllSubsidiaryProducts());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<SubsidiaryEntity>> updateSubsidiary(@PathVariable Long id, @RequestBody SubsidiaryDTO subsidiary) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(subsidiaryService.updateSubsidiaryById(id, subsidiary));
    }
}
