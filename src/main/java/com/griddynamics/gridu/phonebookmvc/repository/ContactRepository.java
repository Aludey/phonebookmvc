package com.griddynamics.gridu.phonebookmvc.repository;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Optional<Contact> findByName(String name);
}
