/*
 * This source file is part of the Agents_i3a open source project.
 *
 * Copyright (c) 2017 Agents_i3a project authors.
 * Licensed under MIT License.
 *
 * See /LICENSE for license information.
 * 
 * This class is based on the AlbUtil project.
 * 
 */
package org.uniovi.i3a.notifications_service.services;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uniovi.i3a.notifications_service.Service;
import org.uniovi.i3a.notifications_service.types.Notification;

import TestKit.IntegrationTest;

/**
 * Created by Nicolás on 15/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
@SpringBootTest(classes = { Service.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class NotificationsServiceTest {

    @Autowired
    private NotificationsService service;

    // User to use as reference for test
    private Notification notification1;
    private Notification notification2;

    @Before
    public void setUp() throws IOException {
	notification1 = new Notification(new ObjectId().toString(), new ObjectId().toString());
	service.save(notification1);
	
	notification2 = new Notification(new ObjectId().toString(), new ObjectId().toString());
	service.save(notification2);
    }

    @After
    public void tearDown() {
	service.delete(notification1);
	service.delete(notification2);
    }

    @Test
    public void getNotificationTest() {
	Notification notification = service.getNotification(notification1.getId());
	assertNotNull(notification);
	assertEquals(notification1.getIncidentId(), notification.getIncidentId());
    }
}
