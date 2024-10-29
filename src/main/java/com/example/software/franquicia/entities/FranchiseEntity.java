package com.example.software.franquicia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("franchise")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FranchiseEntity {
    @Id
    private Long id;
    private String name;


    public FranchiseEntity(String name) {
        this.name = name;
    }
}
