package com.se.contactsmanager.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {

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
    public void getName() {
        assertTrue(milan.getName() == "Milan Gulati");
    }
}