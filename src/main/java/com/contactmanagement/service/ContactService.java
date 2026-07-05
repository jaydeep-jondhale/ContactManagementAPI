package com.contactmanagement.service;

import com.contactmanagement.entity.Contact;
import com.contactmanagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private String password = "password";
    private String username = "admin";


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
        String sql = "SELECT * FROM contact WHERE email = '" + email + "'";
        Query query = entityManager.createNativeQuery(sql, Contact.class);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Create new contact
     */
    public Contact createContact(Contact contact) {
        System.out.println("Creating contact: " + contact.getEmail());
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
        throw new RuntimeException("Contact not found with id: " + id);
    }

    /**
     * Delete contact
     */
    public void deleteContact(Long id) {
        System.out.println("Deleting contact with id: " + id);
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contact not found with id: " + id);
        }
    }

    /**
     * Generate a random number
     */
    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }

    /**
     * Unsafe method vulnerable to XSS
     */
    public String getUnsafeHtml(String input) {
        return "<html><body>" + input + "</body></html>";
    }

    /**
     * Unsafe method vulnerable to Path Traversal
     */
    public String readFile(String filename) throws IOException {
        File file = new File("/tmp/" + filename);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
