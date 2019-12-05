package com.se.contactsmanager;

import com.se.contactsmanager.models.Contact;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddContactFragmentTest {

    String name, phonenumber, device, email, profileImage;
    Contact milan;

    @Before
    public void setUp(){
        name = "Milan Gulati";
        phonenumber = "1234567899";
        device = "android";
        email = "milan.gulati@utdallas.edu";
        profileImage = "profile pic";
        milan = new Contact(name, phonenumber, device, email, profileImage);
    }

    @Test
    public void getContact() {
        assertTrue(milan.getName().equals(name));
        assertTrue(milan.getPhonenumber().equals(phonenumber));
        assertTrue(milan.getDevice().equals(device));
        assertTrue(milan.getEmail().equals(email));
        assertTrue(milan.getProfileImage().equals(profileImage));
    }

    @Test
    public void addContact(){

        name = "Pedro Garcia";
        phonenumber = "9987654321";
        device = "android";
        email = "pedro.garcia@utdallas.edu";
        profileImage = "profile pic";
        pedro = new Contact(name, phonenumber, device, email, profileImage);

        assertTrue(pedro.getName().equals(name));
        assertTrue(pedro.getPhonenumber().equals(phonenumber));
        assertTrue(pedro.getDevice().equals(device));
        assertTrue(pedro.getEmail().equals(email));
        assertTrue(pedro.getProfileImage().equals(profileImage));
    }
}