package com.contactmanagement.service;

import com.contactmanagement.entity.Contact;
import com.contactmanagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

// Define custom exception in the same file as per agent constraints
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Get all contacts
     */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Get contact by ID
     */
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    /**
     * Get contact by email
     */
    public Optional<Contact> getContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    /**
     * Create new contact
     */
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Update contact
     */
    public Contact updateContact(Long id, Contact contactDetails) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            Contact existingContact = contact.get();
            existingContact.setFirstName(contactDetails.getFirstName());
            existingContact.setLastName(contactDetails.getLastName());
            existingContact.setEmail(contactDetails.getEmail());
            existingContact.setPhoneNumber(contactDetails.getPhoneNumber());
            existingContact.setAddress(contactDetails.getAddress());
            existingContact.setCity(contactDetails.getCity());
            existingContact.setState(contactDetails.getState());
            existingContact.setZipCode(contactDetails.getZipCode());
            return contactRepository.save(existingContact);
        }
        throw new ContactNotFoundException("Contact not found with id: " + id);
    }

    /**
     * Delete contact
     */
    public void deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        } else {
            throw new ContactNotFoundException("Contact not found with id: " + id);
        }
    }
}
