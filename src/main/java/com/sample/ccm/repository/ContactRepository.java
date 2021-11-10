package com.sample.ccm.repository;

import com.sample.ccm.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByVatNumberIgnoreCase(String vatNumber);
}