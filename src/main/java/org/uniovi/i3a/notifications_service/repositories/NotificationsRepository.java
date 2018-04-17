package org.uniovi.i3a.notifications_service.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.uniovi.i3a.notifications_service.types.Notification;

public interface NotificationsRepository extends MongoRepository<Notification, ObjectId> {

    /**
     * Find a user by its email address.
     * 
     * @param id
     *            address of the user to look for.
     * @return the user if exists, null otherwise.
     */
    Notification findById(String id);
    
    List<Notification> findByIncidentId(String id);
}
