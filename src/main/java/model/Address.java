/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author Primax
 */
@Embeddable
public class Address {
    String provience;
    String district;
    String municipality;
    int ward;

    
    @Override
    public String toString() {
        return " Address: [ Provience: "+this.provience+" District: "+this.district+" Municipality: "+this.municipality+" Ward: "+this.ward+" ]";
        
        
    }
    public String getProvience() {
        return provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }
    
    
    
}
