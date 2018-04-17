package org.uniovi.i3a.notifications_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uniovi.i3a.notifications_service.services.NotificationsService;
import org.uniovi.i3a.notifications_service.types.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GetNotificationController {

    @Autowired
    NotificationsService service;

    @RequestMapping("/notifications/{id}")
    public Notification getNotification(@PathVariable String id) {
	log.info("Gettin notification: " + id.toString());
	return service.getNotification(id);
    }

    @RequestMapping("/notifications")
    public List<Notification> getNotifications(@Nullable @RequestParam("incidentId") String id) {
	if (id == null) {
	    log.info("Gettin all notification.");
	    return service.getAllNotifications();
	} else {
	    log.info("Gettin notification: " + id.toString());
	    return service.getAllNotificationsForIncident(id);
	}
    }
}
