package com.se.contactsmanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.android.controller.ActivityController;

import static org.robolectric.Robolectric.buildActivity;


//@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

    MainActivity mainActivity;

    @Before
    public void setUp(){
        ActivityController<MainActivity> mainActivityActivityController = buildActivity(MainActivity.class);
    }

    @Test
    public void testMainActivity(){
        Assert.assertNotNull(mainActivity);
    }

    @Test
    public void onCreate() {

    }
}