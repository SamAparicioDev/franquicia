package com.example.software.franquicia.controllers;

import com.example.software.franquicia.entities.ProductEntity;
import com.example.software.franquicia.models.ProductDTO;
import com.example.software.franquicia.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Mono<ProductEntity>> addProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }
    @GetMapping("/subsidiary/{subsidiaryId}")
    public ResponseEntity<Flux<ProductEntity>> getProductsBySubsidiaryId(@PathVariable Long subsidiaryId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductBySubsidiaryId(subsidiaryId));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Mono<ProductEntity>> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }
    @GetMapping("/get")
    public ResponseEntity<Flux<ProductEntity>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
    @GetMapping("/subsidiary/max/{subsidiaryId}")
    public ResponseEntity<Mono<ProductEntity>> getMaxStockProductBySubsidiaryId(@PathVariable Long subsidiaryId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductWithMaxStockBySubsidiaryId(subsidiaryId));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mono<Void>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.deleteProductById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<ProductEntity>> updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.updateProductById(id, product));
    }
}
