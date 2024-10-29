package com.example.software.franquicia.models;

import lombok.Data;

import java.util.List;
@Data
public class FranchiseSubsidiarieDTO {
    private String name;
    private List<SubsidiaryProductDTO> subsidiaries;

    public FranchiseSubsidiarieDTO(String name) {
        this.name = name;
    }
}
