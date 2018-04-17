package org.uniovi.i3a.notifications_service.controllers;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.uniovi.i3a.notifications_service.Service;
import org.uniovi.i3a.notifications_service.services.NotificationsService;
import org.uniovi.i3a.notifications_service.types.Notification;
import TestKit.IntegrationTest;

@SpringBootTest(classes = { Service.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class GetNotificationTest {
    
    @Autowired
    RestTemplate restTemplate;
    
    private final String idThatExists = "5ad5f09e3a1d092d7a9705b0";

    @Autowired
    private NotificationsService service;
    private Notification notification;
    
    
    @Before
    public void setUp() throws Exception {
	// Setting up maria
	notification = service.getNotification(idThatExists);
    }
    
    
    @Test
    public void getNotificationThatExistsTest() {
	Notification response = restTemplate.getForObject("http://asw-i3a.guill.io:9001/notifications_service/notifications/5ad5f09e3a1d092d7a9705b0", Notification.class);
	assertEquals(notification.getId(), response.getId());
    }
}
