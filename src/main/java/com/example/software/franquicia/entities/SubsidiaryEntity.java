package com.example.software.franquicia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table("subsidiary")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubsidiaryEntity {
    @Id
    private Long id;
    private String name;
    private Long franchiseId;

    public SubsidiaryEntity(String name, Long franchiseId) {
        this.name = name;
        this.franchiseId = franchiseId;
    }
}
