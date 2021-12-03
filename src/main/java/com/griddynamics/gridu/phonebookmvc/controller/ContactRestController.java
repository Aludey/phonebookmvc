package com.griddynamics.gridu.phonebookmvc.controller;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import com.griddynamics.gridu.phonebookmvc.service.PhonebookService;
import com.griddynamics.gridu.phonebookmvc.exception.ContactAlreadyExistException;
import com.griddynamics.gridu.phonebookmvc.exception.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("rest")
@RequestMapping("/api/v1/customers")
public class ContactRestController {

    @Autowired
    private PhonebookService service;

    @PostMapping("/addContact")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact addContact(@RequestBody Contact contact) throws ContactAlreadyExistException {
        return service.saveContact(contact);
    }

    @PostMapping("/addContacts")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Contact> saveAllContacts(@RequestBody List<Contact> contacts) throws ContactAlreadyExistException {
        return service.saveAllContacts(contacts);
    }

    @GetMapping("/getContacts")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Contact> getContacts() {
        return service.getContacts();
    }

    @GetMapping("/getContact/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public Contact getContactByName(@PathVariable String name) throws ContactNotFoundException {
        return service.getContactByName(name);
    }

    @DeleteMapping("/deleteContact/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(@PathVariable String name) throws ContactNotFoundException {
        service.deleteContact(name);
    }

    @PutMapping("/updateContact")
    @ResponseStatus(HttpStatus.OK)
    public Contact updateContact(@RequestBody Contact updatedContact) throws ContactNotFoundException {
        return service.updateContact(updatedContact);
    }

    @PutMapping("/addPhone/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Contact addPhoneToContactByName(@PathVariable String name, @RequestBody String phone) throws ContactNotFoundException {
        return service.addPhoneToContactByName(name, phone);
    }
}
