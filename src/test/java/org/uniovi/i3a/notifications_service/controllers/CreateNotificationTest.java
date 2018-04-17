package org.uniovi.i3a.notifications_service.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.uniovi.i3a.notifications_service.Service;
import org.uniovi.i3a.notifications_service.services.NotificationsService;
import org.uniovi.i3a.notifications_service.types.Notification;

import TestKit.IntegrationTest;

@SpringBootTest(classes = { Service.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class CreateNotificationTest {

    // {"name":"colunga91","location":"10N99W", "email":"colunga91@gmail.com",
    // "password":"123456","username":"47170929X","kindCode":1}

    @Autowired
    private WebApplicationContext context;
    private static final String QUERY_STRING = "{\"id\":\"%s\", \"incidentId\":\"%s\", \"operatorId\":\"%s\"}";
    private MockMvc mockMvc;

    @Autowired
    private NotificationsService service;
    private MockHttpSession session;
    private Notification notification;
    private String originalNotificationId;

    @Before
    public void setUp() throws Exception {

	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

	session = new MockHttpSession();

	// Setting up maria
	notification = new Notification(new ObjectId().toString(), new ObjectId().toString());
	originalNotificationId = notification.getId();
    }

    @After
    public void tearDown() {
	notification = service.getNotification(originalNotificationId);
	System.err.println(notification);
	service.delete(notification);
    }

    @Test
    public void successfulInsertTest() throws Exception {
	String payload = String.format(QUERY_STRING, notification.getId(), notification.getIncidentId(), notification.getOperatorId());

	// We send a POST request to that URI (from http:localhost...)
	MockHttpServletRequestBuilder request = post("/create").session(session)
		.contentType(MediaType.APPLICATION_JSON).content(payload.getBytes());

	MvcResult result = mockMvc.perform(request).andDo(print())
		// The state of the response must be CREATED. (201);
		.andExpect(status().isCreated()).andReturn();
	originalNotificationId = result.getResponse().getContentAsString();
    }
    
    @Test @Ignore
    public void unSuccessfulInsertTest() {
	// TODO
    }
}
