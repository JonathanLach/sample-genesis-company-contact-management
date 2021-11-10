package com.sample.ccm.service;

import com.sample.ccm.dto.GetCompanyDTO;
import com.sample.ccm.dto.UpdateCompanyDTO;
import com.sample.ccm.exception.VatAlreadyExistException;

import java.util.List;

public interface CompanyService {
    GetCompanyDTO createCompany(UpdateCompanyDTO company) throws VatAlreadyExistException;

    void updateCompany(Long companyId, UpdateCompanyDTO company) throws VatAlreadyExistException;

    List<GetCompanyDTO> getCompanies(String vatNumber);

    GetCompanyDTO getCompany(Long id);

    void addContactToCompany(Long companyId, Long contactId);
}
