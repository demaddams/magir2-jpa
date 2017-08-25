/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entity;

import javax.persistence.*;

/**
 *
 * @author AROLI-PC
 */
@Entity
@Table(name = "T_PHONE")
public class Phone {
    private Long id;
    private String number;
    
    private Person person;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(name = "TABLE_GNERATOR", table = "T_SEQUENCES",pkColumnName = "SEQ_NAME",pkColumnValue = "PHONE")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="NUMBER")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    
    
}


