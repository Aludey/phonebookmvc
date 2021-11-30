package com.griddynamics.gridu.phonebookmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTACT_TABLE")
public class Contact {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phones;
}
