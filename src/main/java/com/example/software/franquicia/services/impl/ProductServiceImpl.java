package com.example.software.franquicia.services.impl;

import com.example.software.franquicia.entities.ProductEntity;
import com.example.software.franquicia.exceptions.service.NotFoundException;
import com.example.software.franquicia.mappers.ProductMapper;
import com.example.software.franquicia.models.ProductDTO;
import com.example.software.franquicia.repositories.ProductRepository;
import com.example.software.franquicia.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Mono<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id).onErrorResume(e -> Mono.error(new NotFoundException("Product not found")));
    }

    @Override
    public Mono<ProductEntity> addProduct(ProductDTO product) {
        return productRepository.save(ProductMapper.toEntity(product));
    }

    @Override
    public Mono<Void> deleteProductById(Long id) {
        return productRepository.deleteById(id);
    }

    @Override
    public Mono<ProductEntity> getProductWithMaxStockBySubsidiaryId(Long subsidiaryId) {
        return productRepository.getProductBySubsidiaryId(subsidiaryId)
                .collectList()
                .flatMap(products ->
                    products.stream()
                            .max(Comparator.comparing(ProductEntity::getStock))
                            .map(Mono::just)
                            .orElse(Mono.empty()));

    }

    @Override
    public Mono<ProductEntity> updateProductById(Long id,ProductDTO product) {
        return productRepository.findById(id).flatMap((productEntity)->{
                    productEntity.setName(product.name());
                    productEntity.setDescription(product.description());
                    productEntity.setStock(product.stock());
                    productEntity.setPrice(product.price());
                    productEntity.setSubsidiaryId(product.subsidiaryId());
                    return productRepository.save(productEntity);
                }
        );
    }

    @Override
    public Flux<ProductEntity> getProductBySubsidiaryId(Long subsidiaryId) {
        return productRepository.getProductBySubsidiaryId(subsidiaryId)
                .onErrorResume(e -> Mono.error(new NotFoundException("Subsidiary Not Found")));
    }

    @Override
    public Flux<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
}
