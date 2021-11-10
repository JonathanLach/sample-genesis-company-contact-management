package com.sample.ccm.repository;

import com.sample.ccm.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByVatNumberIgnoreCase(String vatNumber);
}
