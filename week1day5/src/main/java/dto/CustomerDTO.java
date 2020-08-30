/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author riisager
 */
public class CustomerDTO {
    
    private int id;
    private String fullName;
    private String accountNumber;
    private double balance;
    
    public CustomerDTO(BankCustomer b){
        this.id = b.getId().intValue();
        this.fullName = b.getFirstName() + " " + b.getLastName();
        this.accountNumber = b.getAccountNumber();
        this.balance = b.getBalance().doubleValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    
}
