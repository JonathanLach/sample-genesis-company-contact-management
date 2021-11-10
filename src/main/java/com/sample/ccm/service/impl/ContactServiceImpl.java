package com.sample.ccm.service.impl;

import com.sample.ccm.dto.GetContactDTO;
import com.sample.ccm.dto.UpdateContactDTO;
import com.sample.ccm.exception.VatAlreadyExistException;
import com.sample.ccm.model.Company;
import com.sample.ccm.model.Contact;
import com.sample.ccm.repository.CompanyRepository;
import com.sample.ccm.repository.ContactRepository;
import com.sample.ccm.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GetContactDTO createContact(UpdateContactDTO contactDTO) throws VatAlreadyExistException {
        if(!contactRepository.findByVatNumberIgnoreCase(contactDTO.getVatNumber()).isEmpty()) {
            throw new VatAlreadyExistException(contactDTO.getVatNumber());
        }
        Contact contact = new Contact();
        updateContactForSave(contact, contactDTO);
        contact = contactRepository.save(contact);
        return modelMapper.map(contact, GetContactDTO.class);
    }

    @Override
    public void updateContact(Long contactId, UpdateContactDTO contactDTO) throws VatAlreadyExistException {
        if(!contactRepository.findByVatNumberIgnoreCase(contactDTO.getVatNumber()).isEmpty()) {
            throw new VatAlreadyExistException(contactDTO.getVatNumber());
        }
        Contact contactToUpdate = findContactOrThrowException(contactId);
        updateContactForSave(contactToUpdate, contactDTO);
        contactRepository.save(contactToUpdate);
    }

    @Override
    public List<GetContactDTO> getContacts() {
        return contactRepository.findAll().stream().map((contact) -> modelMapper.map(contact, GetContactDTO.class))
                        .collect(Collectors.toList());
    }

    @Override
    public GetContactDTO getContact(Long id) {
        Contact contact = findContactOrThrowException(id);
        return modelMapper.map(contact, GetContactDTO.class);
    }

    @Override
    public void removeContact(Long id) {
        Contact contact = findContactOrThrowException(id);
        for(Company company : contact.getCompanies()) {
            company.removeContact(contact);
        }
        companyRepository.saveAll(contact.getCompanies());
        contactRepository.deleteById(id);
    }

    private Contact updateContactForSave(Contact contact, UpdateContactDTO contactDTO) {
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setContactType(contactDTO.getContactType());
        contact.setVatNumber(contactDTO.getVatNumber());
        contact.getAddress().setStreetAddress(contactDTO.getAddress().getStreetAddress());
        contact.getAddress().setCity(contactDTO.getAddress().getCity());
        contact.getAddress().setCountryCode(contactDTO.getAddress().getCountryCode());
        contact.getAddress().setZipCode(contactDTO.getAddress().getZipCode());
        return contact;
    }

    private Contact findContactOrThrowException(Long contactId) {
        return contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException("The contact with ID [" + contactId + "] was not found"));
    }
}
