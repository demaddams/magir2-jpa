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
@Table(name = "T_GEEKS") 
@Access(AccessType.PROPERTY)
public class Geek extends Person{
    
    private String favouriteProgrammingLanguage;
    
    private List<Project> projects = new ArrayList<Project>();
    
    @Column(name = "FAV_PROG_LANG")
    public String getFavouriteProgrammingLanguage() {
        return favouriteProgrammingLanguage;
    }

    public void setFavouriteProgrammingLanguage(String favouriteProgrammingLanguage) {
        this.favouriteProgrammingLanguage = favouriteProgrammingLanguage;
    }
    
   @ManyToMany(mappedBy = "geeks")
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    
    
}
