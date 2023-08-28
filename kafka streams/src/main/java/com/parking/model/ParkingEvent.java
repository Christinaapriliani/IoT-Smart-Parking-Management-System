package com.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingEvent {
    private String eventId;
    private String slotId;
    private EventType eventType;
    private long timestamp;
}
