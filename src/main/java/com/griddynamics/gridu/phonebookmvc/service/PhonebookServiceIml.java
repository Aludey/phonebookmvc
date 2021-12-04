package com.griddynamics.gridu.phonebookmvc.service;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import com.griddynamics.gridu.phonebookmvc.repository.ContactRepository;
import com.griddynamics.gridu.phonebookmvc.exception.ContactAlreadyExistException;
import com.griddynamics.gridu.phonebookmvc.exception.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhonebookServiceIml implements  PhonebookService {

    @Autowired
    private ContactRepository repository;

    @Override
    public Contact saveContact(Contact contact) throws ContactAlreadyExistException {
        if (doesContactExist(contact)) {
            throw new ContactAlreadyExistException(contact.getName());
        } else {
            return repository.save(contact);
        }
    }

    @Override
    public List<Contact> saveAllContacts(List<Contact> contacts) throws ContactAlreadyExistException {
        for (Contact contact : contacts) {
            if (doesContactExist(contact)) throw new ContactAlreadyExistException(contact.getName());
        }
        return repository.saveAll(contacts);
    }

    @Override
    public List<Contact> getContacts() {
        return repository.findAll();
    }

    @Override
    public Contact getContactById(int id) throws ContactNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ContactNotFoundException(id));
    }

    @Override
    public Contact getContactByName(String name) throws ContactNotFoundException{
        return repository.findByName(name).orElseThrow(
                () -> new ContactNotFoundException(name));
    }

    @Override
    public void deleteContact(String name) throws ContactNotFoundException {
        Contact existingContact = getContactByName(name);
        repository.delete(existingContact);
    }

    @Override
    public Contact updateContact(Contact updatedContact) throws ContactNotFoundException {
            Contact existingContact = getContactByName(updatedContact.getName());
            existingContact.setName(updatedContact.getName());
            existingContact.setPhones(updatedContact.getPhones());
            return repository.save(existingContact);
    }

    @Override
    public Contact addPhoneToContactByName(String name, String phone) throws ContactNotFoundException {
        Contact existingContact = getContactByName(name);
        existingContact.setPhones(existingContact.getPhones() + ", " + phone);
        return repository.save(existingContact);
    }

    private boolean doesContactExist(Contact contact) {
        return repository.findByName(contact.getName()).isPresent();
    }
}