package com.sample.ccm.service;

import com.sample.ccm.dto.GetContactDTO;
import com.sample.ccm.dto.UpdateContactDTO;
import com.sample.ccm.exception.VatAlreadyExistException;

import java.util.List;

public interface ContactService {
    GetContactDTO createContact(UpdateContactDTO Contact) throws VatAlreadyExistException;

    void updateContact(Long ContactId, UpdateContactDTO Contact) throws VatAlreadyExistException;

    List<GetContactDTO> getContacts();

    GetContactDTO getContact(Long id);

    void removeContact(Long id);
}
