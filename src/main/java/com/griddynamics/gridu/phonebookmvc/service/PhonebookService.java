package com.griddynamics.gridu.phonebookmvc.service;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import exception.ContactNotFoundException;

import java.util.List;

public interface PhonebookService {

    Contact saveContact(Contact contact);

    List<Contact> saveAllContacts(List<Contact> contacts);

    List<Contact> getContacts();

    Contact getContactById(int id) throws ContactNotFoundException;

    Contact getContactByName(String name) throws ContactNotFoundException;

    void deleteContact(String name) throws ContactNotFoundException;

    Contact updateContact(Contact updatedContact) throws ContactNotFoundException;

    Contact addPhoneToContactByName(String name, String phone) throws ContactNotFoundException;
}
