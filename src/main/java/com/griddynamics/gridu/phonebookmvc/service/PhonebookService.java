package com.griddynamics.gridu.phonebookmvc.service;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import com.griddynamics.gridu.phonebookmvc.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Service
public class PhonebookService {

    @Autowired
    private ContactRepository repository;

    public Contact saveContact(Contact contact) {
        return repository.save(contact);
    }

    public List<Contact> saveAllContacts(List<Contact> contacts) {
        return repository.saveAll(contacts);
    }

    public List<Contact> getContacts() {
        return repository.findAll();
    }

    public Contact getContactByName(String name) {
        return repository.findById(name).orElse(null);
    }

    public String deleteContact(String name) {
        repository.deleteById(name);
        return String.format("Contact '%s' was removed.", name);
    }

    public Contact updateContact(Contact updatedContact) {
        Contact existingContact = repository.findById(updatedContact.getName()).orElse(updatedContact);
        existingContact.setName(updatedContact.getName());
        existingContact.setPhones(updatedContact.getPhones());
        return repository.save(existingContact);
    }
/*
    public Contact addPhoneToContact(String name, String phone) {
        Contact existingContact = repository.findById(name).orElse(null);
        if (!existingContact.equals(null)) {
            List<String> phones = existingContact.getPhones();
            if (!isPhoneExist(phone,phones)) phones.add(phone);
        } else {
            existingContact = new Contact(name, Arrays.asList(phone));
        }
        return existingContact;
    }

    public Contact removePhoneToContact(@RequestBody Contact updatedContact) {
        Contact existingContact = repository.findById(updatedContact.getName()).orElse(updatedContact);
        existingContact.setName(updatedContact.getName());
        existingContact.setPhones(updatedContact.getPhones());
        return existingContact;
    }

    private boolean isPhoneExist(String phone, List<String> phones) {
        for (String existingPhone : phones) {
            if (phone.equals(existingPhone)) return true;
        }
        return false;
    }*/
}
