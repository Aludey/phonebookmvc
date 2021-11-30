package com.griddynamics.gridu.phonebookmvc.repository;

import com.griddynamics.gridu.phonebookmvc.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Contact, String> {
}
