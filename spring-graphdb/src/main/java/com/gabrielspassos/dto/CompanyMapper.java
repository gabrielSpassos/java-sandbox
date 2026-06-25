package com.gabrielspassos.dto;

import com.gabrielspassos.domain.Company;

public final class CompanyMapper {

    private CompanyMapper() {
    }

    public static CompanyDto toDto(Company company) {
        return new CompanyDto(company.getId(), company.getName());
    }
}
