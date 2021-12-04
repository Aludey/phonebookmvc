package com.griddynamics.gridu.phonebookmvc;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import com.griddynamics.gridu.phonebookmvc.exception.ContactAlreadyExistException;
import com.griddynamics.gridu.phonebookmvc.exception.ContactNotFoundException;
import com.griddynamics.gridu.phonebookmvc.service.PhonebookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class PhonebookmvcApplicationTests {

	@Autowired
	private PhonebookService service;

	@Test
	void addNewContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		contact.setName("AddNewContactTest");
		contact.setPhones(Arrays.asList("89123456752"));
		service.saveContact(contact);
		Assert.isTrue(service.getContactByName(contact.getName()).getName().equals(contact.getName()), "Contact wasn't created!");
		service.deleteContact(contact.getName());
	}

	@Test
	void addSeveralContactsTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact firstContact = new Contact();
		Contact secondContact = new Contact();
		firstContact.setName("firstContact");
		firstContact.setPhones(Arrays.asList("89123456752"));
		secondContact.setName("secondContact");
		secondContact.setPhones(Arrays.asList("89122576543"));
		service.saveAllContacts(Arrays.asList(firstContact, secondContact));
		Assert.isTrue(service.getContactByName(firstContact.getName()).getName().equals(firstContact.getName()), "First contact wasn't created!");
		Assert.isTrue(service.getContactByName(secondContact.getName()).getName().equals(secondContact.getName()), "Second contact wasn't created!");
		service.deleteContact(firstContact.getName());
		service.deleteContact(secondContact.getName());
	}

	@Test
	void removeCreatedContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		contact.setName("RemoveNewContactTest");
		contact.setPhones(Arrays.asList("89123456752"));
		service.saveContact(contact);
		Assert.isTrue(service.getContacts().stream().anyMatch(a -> (a.getName().equals(contact.getName()))), "Contact wasn't created!");
		service.deleteContact(contact.getName());
		Assert.isTrue(!service.getContacts().stream().anyMatch(a -> (a.getName().equals(contact.getName()))), "Contact wasn't removed!");
	}

	@Test
	void updateCreatedContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		List<String> expectedPhones = Arrays.asList("89123456752", "89324587256");
		contact.setName("UpdateNewContactTest");
		contact.setPhones(Arrays.asList(expectedPhones.get(0)));
		service.saveContact(contact);
		contact.setPhones(expectedPhones);
		service.updateContact(contact);
		Assert.isTrue(service.getContactByName(contact.getName()).getPhones().containsAll(expectedPhones), "Contact wasn't updated!");
		service.deleteContact(contact.getName());
	}

	@Test
	void addPhoneToExistingContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		List<String> expectedPhones = Arrays.asList("89123456752", "89324587256");
		contact.setName("AddPhoneTest");
		contact.setPhones(Arrays.asList(expectedPhones.get(0)));
		service.saveContact(contact);
		service.addPhoneToContactByName(contact.getName(), expectedPhones.get(1));
		Assert.isTrue(service.getContactByName(contact.getName()).getPhones().containsAll(expectedPhones), "Phone wasn't added!");
		service.deleteContact(contact.getName());
	}

	@Test
	void deletePhoneFromExistingContactTest() throws ContactAlreadyExistException, ContactNotFoundException {
		Contact contact = new Contact();
		List<String> expectedPhones = Arrays.asList("89123456752", "89324587256");
		contact.setName("DeletePhoneTest");
		contact.setPhones(expectedPhones);
		service.saveContact(contact);
		service.deletePhoneFromContactByName(contact.getName(), expectedPhones.get(1));
		expectedPhones = new ArrayList<>(Collections.singleton(expectedPhones.get(0)));
		Assert.isTrue(service.getContactByName(contact.getName()).getPhones().containsAll(expectedPhones), "Phone wasn't added!");
		service.deleteContact(contact.getName());
	}
}
