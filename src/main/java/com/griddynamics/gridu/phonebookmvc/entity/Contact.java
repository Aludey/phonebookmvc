package com.griddynamics.gridu.phonebookmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> phones;
}
