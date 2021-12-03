package com.griddynamics.gridu.phonebookmvc.service;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import com.griddynamics.gridu.phonebookmvc.exception.ContactAlreadyExistException;
import com.griddynamics.gridu.phonebookmvc.exception.ContactNotFoundException;

import java.util.List;

public interface PhonebookService {

    Contact saveContact(Contact contact) throws ContactAlreadyExistException;

    List<Contact> saveAllContacts(List<Contact> contacts) throws ContactAlreadyExistException;

    List<Contact> getContacts();

    Contact getContactById(int id) throws ContactNotFoundException;

    Contact getContactByName(String name) throws ContactNotFoundException;

    void deleteContact(String name) throws ContactNotFoundException;

    Contact updateContact(Contact updatedContact) throws ContactNotFoundException;

    Contact addPhoneToContactByName(String name, String phone) throws ContactNotFoundException;
}
