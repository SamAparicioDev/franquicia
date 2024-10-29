package com.example.software.franquicia.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private Long stock;
    private String description;
    private Double price;
    private Long subsidiaryId;

    public ProductEntity(String name, Long stock, String description, Double price, Long subsidiaryId) {
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.price = price;
        this.subsidiaryId = subsidiaryId;
    }

}
