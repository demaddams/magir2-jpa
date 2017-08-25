/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author AROLI-PC
 */
@Entity
@Table(name = "T_PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="FIRST_NAME")
    private String firstName;
    
    @Column(name="LAST_NAME")
    private String lastName;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CARD_ID")
    private IdCard IdCard;
    
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Phone> phones = new ArrayList<Phone>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public IdCard getIdCard() {
        return IdCard;
    }

    public void setIdCard(IdCard IdCard) {
        this.IdCard = IdCard;
    }

    

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
    
    
}
