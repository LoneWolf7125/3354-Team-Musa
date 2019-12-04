package com.se.contactsmanager.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditContactTest {

    private String name, phoneNumber, device, email, profileImage;
    private Contact milan;

    // Used the same contact from "ContactTest" by Milan to verify changes below
    @Before
    public void setUp(){
        name = "Milan Gulati";
        phoneNumber = "1234567899";
        device = "work";
        email = "milan.gulati@utdallas.edu";
        profileImage = "profile pic";
        milan = new Contact(name, phoneNumber, device, email, profileImage);
    }

    // Change to the number
    @Test
    public void editNumber() {
        String newNumber = "9092939192";
        milan.setPhonenumber(newNumber);
        assertTrue(milan.getPhonenumber().equals(newNumber)); // True
        assertFalse(milan.getPhonenumber().equals(phoneNumber)); // False
    }

    // Change to the name
    @Test
    public void editName() {
        String newName = "Jonathan Snead";
        milan.setName(newName);
        assertTrue(milan.getName().equals(newName)); // True
        assertFalse(milan.getName().equals(name)); // False
    }

    // Change to the email
    @Test
    public void editEmail() {
        String newEmail = "new.email@gmail.com";
        milan.setEmail(newEmail);
        assertTrue(milan.getEmail().equals(newEmail)); // True
        assertFalse(milan.getEmail().equals(email)); // False 
    }
}