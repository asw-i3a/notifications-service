package org.uniovi.i3a.notifications_service.services;

import java.util.List;

import org.uniovi.i3a.notifications_service.types.Notification;

public interface NotificationsService {
    
    /**
     * Given the data of a user, checks if there's such an user, and if the password
     * matches
     * 
     * @param identifier
     *            The login email for the user
     * @param password
     *            The password given on the credentials
     * @return Either the proper user, if the user exists and the password matches.
     *         Null otherwise
     */
    Notification getNotification(String id);
    
    void save(Notification notification);
    
    void delete(Notification notification);
    
    List<Notification> getAllNotifications();
    
    List<Notification> getAllNotificationsForIncident(String id);

}
