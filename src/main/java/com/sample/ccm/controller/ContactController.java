package com.sample.ccm.controller;

import com.sample.ccm.dto.GetContactDTO;
import com.sample.ccm.dto.UpdateContactDTO;
import com.sample.ccm.exception.VatAlreadyExistException;
import com.sample.ccm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<List<GetContactDTO>> getContacts() {
        return ResponseEntity.ok(contactService.getContacts());
    }

    @GetMapping("/contacts/{contactId}")
    public ResponseEntity<GetContactDTO> getContact(@PathVariable Long contactId) {
        return ResponseEntity.ok(contactService.getContact(contactId));
    }

    @PostMapping("/contacts")
    public ResponseEntity<GetContactDTO> createContact(@RequestBody @Valid UpdateContactDTO contact) throws VatAlreadyExistException {
        GetContactDTO contactToReturn = contactService.createContact(contact);
        return ResponseEntity.created(URI.create("/contacts/" + contactToReturn.getId())).body(contactToReturn);
    }

    @PutMapping("/contacts/{contactId}")
    public ResponseEntity<Void> updateContact(@PathVariable Long contactId, @RequestBody @Valid UpdateContactDTO contact) throws VatAlreadyExistException {
        contactService.updateContact(contactId, contact);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Void> removeContact(@PathVariable Long contactId) {
        contactService.removeContact(contactId);
        return ResponseEntity.noContent().build();
    }
}
