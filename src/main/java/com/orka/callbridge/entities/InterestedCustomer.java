package com.orka.callbridge.entities;


import javax.persistence.*;
@Entity
@Table(name = "interested_customer")
public class InterestedCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    // Constructors
    public InterestedCustomer(){
    	
    }

    public InterestedCustomer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
