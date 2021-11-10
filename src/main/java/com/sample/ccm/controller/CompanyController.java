package com.sample.ccm.controller;

import com.sample.ccm.dto.GetCompanyDTO;
import com.sample.ccm.dto.UpdateCompanyDTO;
import com.sample.ccm.exception.VatAlreadyExistException;
import com.sample.ccm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<GetCompanyDTO>> getCompanies(@RequestParam(required = false) String vatNumber) {
        return ResponseEntity.ok(companyService.getCompanies(vatNumber));
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<GetCompanyDTO> getCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.getCompany(companyId));
    }

    @PostMapping("/companies")
    public ResponseEntity<GetCompanyDTO> createCompany(@RequestBody @Valid UpdateCompanyDTO company) throws VatAlreadyExistException {
        GetCompanyDTO companyToReturn = companyService.createCompany(company);
        return ResponseEntity.created(URI.create("/companies/" + companyToReturn.getId())).body(companyToReturn);
    }

    @PutMapping("/companies/{companyId}")
    public ResponseEntity<Void> updateCompany(@PathVariable Long companyId, @RequestBody @Valid UpdateCompanyDTO company) throws VatAlreadyExistException {
        companyService.updateCompany(companyId, company);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/companies/{companyId}/contacts/{contactId}")
    public ResponseEntity<Void> addContactToCompany(@PathVariable Long companyId, @PathVariable Long contactId) {
        companyService.addContactToCompany(companyId, contactId);
        return ResponseEntity.noContent().build();
    }
}
