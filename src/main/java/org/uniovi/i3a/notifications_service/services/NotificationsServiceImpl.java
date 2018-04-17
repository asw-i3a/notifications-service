package org.uniovi.i3a.notifications_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniovi.i3a.notifications_service.repositories.NotificationsRepository;
import org.uniovi.i3a.notifications_service.types.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationsServiceImpl implements NotificationsService {
    
    @Autowired
    NotificationsRepository repository;

    @Override
    public Notification getNotification(String id) {
	log.info("Getting notification: " + id);
	return repository.findById(id);
    }

    @Override
    public void save(Notification notification) {
	log.info("Saving notification: " + notification.toString());
	repository.save(notification);
    }

    @Override
    public void delete(Notification notification) {
	log.info("Deleting notification: " + notification.toString());
	repository.delete(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
	log.info("Getting all the notifications.");
	return repository.findAll();
    }

    @Override
    public List<Notification> getAllNotificationsForIncident(String id) {
	log.info("Getting all the notifications for the incident id: " + id);
	return repository.findByIncidentId(id);
    }
}
