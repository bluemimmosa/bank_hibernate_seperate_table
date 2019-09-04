/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author Primax
 */

// Need to give hibernate clues on how to percieve these class and
// then map it to the database. for ths we will use annotations.
@Entity
public class Account{
    // to specify which memeber variable will be primary key. we need another annotaion here.
    @Id
    private long accountNumber;
    private String name;
    private long mobileNo;
    private double balance;
    private char accountType;
    //@ElementCollection(fetch= FetchType.EAGER)
    @ElementCollection(fetch = FetchType.LAZY)
    //@JoinTable(joinColumns=@JoinColumn)
    private Collection<Address> addr = new ArrayList<Address>();
    
    public Account(){
        
    }
    //Constructor to create an account based on the attributes for the Account class.
    public Account(long accountNumber, String name, long mobileNo, double balance, char accountType) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.mobileNo = mobileNo;
        this.balance = balance;
        this.accountType = accountType;
    }

    public Account(long accountNumber, String name, long mobileNo, double balance, char accountType, ArrayList<Address> addr) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.mobileNo = mobileNo;
        this.balance = balance;
        this.accountType = accountType;
        this.addr = addr;
    }

    public Collection<Address> getAddr() {
        return addr;
    }

    public void setAddr(Collection<Address> addr) {
        this.addr = addr;
    }



    
 
    

    
   
    @Override
    public String toString() {
	return "Account [Account Number= " + accountNumber + ", Name= " + name + ", Mobile No= " + mobileNo + ", Balance= " + balance + ", Account Type= " +accountType +"]" + addr.toString();
    }
    
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public double getAmount() {
        return balance;
    }

    public void setAmount(double amount) {
        this.balance = amount;
    }

    public char getAccountType() {
        return accountType;
    }

    public void setAccountType(char accountType) {
        this.accountType = accountType;
    }
    
    
}