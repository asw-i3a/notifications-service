package org.uniovi.i3a.notifications_service.controllers;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.uniovi.i3a.notifications_service.services.NotificationsService;
import org.uniovi.i3a.notifications_service.types.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreateNotificationController {

    @Autowired
    NotificationsService service;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {
	    MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNotification(@RequestBody Map<String, Object> payload) {
	String returnObject = null;
	if (!isPayloadCorrect(payload)) {
	    // Not valid data.
	    log.warn("Not valid register attemp: " + new JSONObject(payload).toString());
	    return new ResponseEntity<String>(returnObject, HttpStatus.BAD_REQUEST);
	}

	Notification notification = new Notification(payload.get("incidentId").toString(),
		payload.get("operatorId").toString());

	service.save(notification);
	returnObject = notification.getId();

	log.info("Notification created: " + new JSONObject(payload).toString());
	return new ResponseEntity<String>(returnObject, HttpStatus.CREATED);
    }

    private boolean isPayloadCorrect(Map<String, Object> payload) {
	
	if(!payload.containsKey("incidentId")) {
	    log.warn("Error at creating. The payload does not contain incidentId");
	    return false;
	}
	
	if(!payload.containsKey("operatorId")) {
	    log.warn("Error at creating. The payload does not contain operatorId");
	    return false;
	}
	
	if(! (payload.get("incidentId") instanceof String)) {
	    log.warn("Error at creating. The payload instanceId id a " + payload.get("incidenceId").getClass() + " not an String");
	    return false;
	}
	
	if(! (payload.get("operatorId") instanceof String)) {
	    log.warn("Error at creating. The payload operatorId id a " + payload.get("operatorId").getClass() + " not an String");
	    return false;
	}
	
	return true;
    }
}
