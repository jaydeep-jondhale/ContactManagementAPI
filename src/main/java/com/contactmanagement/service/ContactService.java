package com.contactmanagement.service;

import com.contactmanagement.model.Contact;
import com.contactmanagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact contactDetails) {
        Optional<Contact> existingContactOptional = contactRepository.findById(id);
        if (existingContactOptional.isPresent()) {
            Contact existingContact = existingContactOptional.get();
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
        contactRepository.deleteById(id);
    }
}

class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
