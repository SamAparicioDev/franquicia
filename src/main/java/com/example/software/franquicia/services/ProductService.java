package com.example.software.franquicia.services;

import com.example.software.franquicia.entities.ProductEntity;
import com.example.software.franquicia.models.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductEntity> getProductById(Long id);
    Mono<ProductEntity> addProduct(ProductDTO product);
    Mono<Void> deleteProductById(Long id);
    Mono<ProductEntity> getProductWithMaxStockBySubsidiaryId(Long subsidiaryId);
    Mono<ProductEntity> updateProductById(Long id,ProductDTO product);
    Flux<ProductEntity> getProductBySubsidiaryId(Long subsidiaryId);
    Flux<ProductEntity> getAllProducts();
}
