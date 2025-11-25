package com.management.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApiApplication.class, args);
	}

}


import com.management.contact.model.Contact;
import com.management.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling contact-related operations.
 */
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
     * Get contact by id
     */
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
    }


    /**
     * Save contact
     */
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Update contact
     */
    public Contact updateContact(Long id, Contact contactDetails) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
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

/**
 * Custom exception to indicate that a contact was not found.
 */
class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
