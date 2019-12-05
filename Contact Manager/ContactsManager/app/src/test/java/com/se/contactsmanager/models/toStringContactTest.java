package com.se.contactsmanager.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class toStringContactTest
{
    String name, phonenumber, device, email, profileImage;
    Contact angad;

    @Before
    public void setUp(){
        name = "Angad Saluja";
        phonenumber = "49483839";
        device = "work";
        email = "angad@utdallas.edu";
        profileImage = "https://icon-library.net//images/default-profile-icon/default-profile-icon-18.jpg";
        angad = new Contact(name, phonenumber, device, email, profileImage);
    }

    @Test
    public void toString_empty()
    {
        Contact angad_empty = new Contact();
        assertEquals(angad_empty.toString(), "Contact{name='', phonenumber='', device='', email='', profileImage=''}");
    }

    @Test
    public void toString_verify()
    {
        assertEquals(angad.toString(), "Contact{name='Angad Saluja', phonenumber='49483839', device='work', email='angad@utdallas.edu', profileImage='https://icon-library.net//images/default-profile-icon/default-profile-icon-18.jpg'}");
    }
}