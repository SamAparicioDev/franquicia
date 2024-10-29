package com.example.software.franquicia.models;

import com.example.software.franquicia.entities.SubsidiaryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class FranchiseSubsidiaryDTO{
        private String name;
        private List<SubsidiaryProductsDTO> subsidiaries;

        public FranchiseSubsidiaryDTO(String name) {
            this.name = name;
        }
}
