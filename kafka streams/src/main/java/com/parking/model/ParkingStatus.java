package com.parking.model;

import lombok.Data;

@Data
public class ParkingStatus {
    private String slotId;
    private int totalOccupied;
    private int totalVacant;

    public ParkingStatus(String slotId) {
        this.slotId = slotId;
        this.totalOccupied = 0;
        this.totalVacant = 0;
    }
}
