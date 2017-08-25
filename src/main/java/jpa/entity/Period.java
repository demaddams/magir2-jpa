/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author AROLI-PC
 */
@Embeddable
public class Period {
    
    private Date startDate;
    private Date endDate;
    
    @Column(name = "START_DATE")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    
}
