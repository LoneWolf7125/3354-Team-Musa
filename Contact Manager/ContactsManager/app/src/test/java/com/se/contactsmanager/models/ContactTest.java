package com.se.contactsmanager.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;

public class ContactTest {

    String name, phonenumber, device, email, profileImage;
    Contact milan;

    @Before
    public void setUp(){
        name = "Milan Gulati";
        phonenumber = "1234567899";
        device = "work";
        email = "milan.gulati@utdallas.edu";
        profileImage = "https://icon-library.net//images/default-profile-icon/default-profile-icon-18.jpg";
        milan = new Contact(name, phonenumber, device, email, profileImage);
    }

    @Test
    public void getName()
    {
        assertTrue(milan.getName() == "Milan Gulati");
    }
    @Test
    public void getPhoneNumber()
    {
        assertTrue(milan.getPhonenumber() == "1234567899");
    }
    @Test
    public void getDevice()
    {
        assertTrue(milan.getDevice() == "work");
    }
    @Test
    public void getEmail()
    {
        assertTrue(milan.getEmail() == "milan.gulati@utdallas.edui");
    }
    @Test
    public void getProfileImage()
    {
        assertTrue(milan.getProfileImage() == "https://icon-library.net//images/default-profile-icon/default-profile-icon-18.jpg");
    }
}