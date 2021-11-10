package com.sample.ccm.service.impl;

import com.sample.ccm.dto.GetCompanyDTO;
import com.sample.ccm.dto.UpdateCompanyDTO;
import com.sample.ccm.exception.VatAlreadyExistException;
import com.sample.ccm.model.Company;
import com.sample.ccm.model.Contact;
import com.sample.ccm.repository.CompanyRepository;
import com.sample.ccm.repository.ContactRepository;
import com.sample.ccm.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GetCompanyDTO createCompany(UpdateCompanyDTO company) throws VatAlreadyExistException {
        if(!companyRepository.findByVatNumberIgnoreCase(company.getVatNumber()).isEmpty()) {
            throw new VatAlreadyExistException(company.getVatNumber());
        }
        Company companyToSave = new Company();
        updateCompanyForSave(companyToSave, company);
        return modelMapper.map(companyRepository.save(companyToSave), GetCompanyDTO.class);
    }

    @Override
    public void updateCompany(Long companyId, UpdateCompanyDTO company) throws VatAlreadyExistException {
        if(!companyRepository.findByVatNumberIgnoreCase(company.getVatNumber()).isEmpty()) {
            throw new VatAlreadyExistException(company.getVatNumber());
        }
        Company companyToUpdate = findCompanyOrThrowException(companyId);
        updateCompanyForSave(companyToUpdate, company);
        companyRepository.save(companyToUpdate);
    }

    @Override
    public List<GetCompanyDTO> getCompanies(String vatNumber) {
        if(StringUtils.hasText(vatNumber)) {
            return companyRepository.findByVatNumberIgnoreCase(vatNumber).stream().map(company ->  modelMapper.map(company, GetCompanyDTO.class))
                    .collect(Collectors.toList());
        } else {
            return companyRepository.findAll().stream().map(company ->  modelMapper.map(company, GetCompanyDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public GetCompanyDTO getCompany(Long id) {
        Company company = findCompanyOrThrowException(id);
        return modelMapper.map(company, GetCompanyDTO.class);
    }

    @Override
    public void addContactToCompany(Long companyId, Long contactId) {
        Company company = findCompanyOrThrowException(companyId);
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException("The contact with ID [" + contactId + "] was not found"));
        company.addContact(contact);
        companyRepository.save(company);
    }

    private void updateCompanyForSave(Company company, UpdateCompanyDTO companyDTO) {
        company.setName(companyDTO.getName());
        company.setVatNumber(companyDTO.getVatNumber());
        company.getAddress().setZipCode(companyDTO.getAddress().getZipCode());
        company.getAddress().setCountryCode(companyDTO.getAddress().getCountryCode());
        company.getAddress().setStreetAddress(companyDTO.getAddress().getStreetAddress());
        company.getAddress().setCity(companyDTO.getAddress().getCity());
    }

    private Company findCompanyOrThrowException(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("The company with ID [" + companyId + "] was not found"));
    }
}