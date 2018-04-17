package org.uniovi.i3a.notifications_service.types;

import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Document(collection = "notifications")
public class Notification {
    
    @Id
    private ObjectId _id;
    
    @Getter
    private String id;
    
    @Getter @Setter
    private String operatorId, incidentId;
    
    public Notification(String operatorId, String incidentId) {
	this.id = new ObjectId().toString();
	this.operatorId = operatorId;
	this.incidentId = incidentId;
    }

}
