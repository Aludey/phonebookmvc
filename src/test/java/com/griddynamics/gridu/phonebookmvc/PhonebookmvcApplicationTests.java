package com.griddynamics.gridu.phonebookmvc;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import com.griddynamics.gridu.phonebookmvc.exception.ContactAlreadyExistException;
import com.griddynamics.gridu.phonebookmvc.exception.ContactNotFoundException;
import com.griddynamics.gridu.phonebookmvc.service.PhonebookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;

@SpringBootTest
class PhonebookmvcApplicationTests {

	@Autowired
	private PhonebookService service;

	@Test
	void addNewContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		contact.setName("AddNewContactTest");
		contact.setPhones("89123456752");
		service.saveContact(contact);
		Assert.isTrue(service.getContacts().contains(contact), "Contact wasn't created!");
		service.deleteContact(contact.getName());
	}

	@Test
	void addSeveralContactsTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact firstContact = new Contact();
		Contact secondContact = new Contact();
		firstContact.setName("firstContact");
		firstContact.setPhones("89123456752");
		secondContact.setName("secondContact");
		secondContact.setPhones("89122576543");
		service.saveAllContacts(Arrays.asList(firstContact, secondContact));
		Assert.isTrue(service.getContacts().contains(firstContact), "First contact wasn't created!");
		Assert.isTrue(service.getContacts().contains(secondContact), "Second contact wasn't created!");
		service.deleteContact(firstContact.getName());
		service.deleteContact(secondContact.getName());
	}

	@Test
	void removeCreatedContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		contact.setName("RemoveNewContactTest");
		contact.setPhones("89123456752");
		service.saveContact(contact);
		Assert.isTrue(service.getContacts().contains(contact), "Contact wasn't created!");
		service.deleteContact(contact.getName());
		Assert.isTrue(!service.getContacts().contains(contact), "Contact wasn't removed!");
	}

	@Test
	void updateCreatedContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		String expectedPhones = "89123456752, 89324587256";
		contact.setName("UpdateNewContactTest");
		contact.setPhones("89123456752");
		service.saveContact(contact);
		contact.setPhones(expectedPhones);
		service.updateContact(contact);
		Assert.isTrue(service.getContactByName(contact.getName()).getPhones().equals(expectedPhones), "Contact wasn't updated!");
		service.deleteContact(contact.getName());
	}

	@Test
	void addPhoneToExistingContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		String expectedPhones = "89123456752, 89324587256";
		contact.setName("UpdateNewContactTest");
		contact.setPhones(expectedPhones.split(", ")[0]);
		service.saveContact(contact);
		service.addPhoneToContactByName(contact.getName(), expectedPhones.split(", ")[1]);
		Assert.isTrue(service.getContactByName(contact.getName()).getPhones().equals(expectedPhones), "Phone wasn't added!");
		service.deleteContact(contact.getName());
	}
}
