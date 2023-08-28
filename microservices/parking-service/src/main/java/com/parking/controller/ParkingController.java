package com.parking.controller;

import com.parking.model.ParkingSlot;
import com.parking.model.Reservation;
import com.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/slots")
    public List<ParkingSlot> getAllParkingSlots() {
        return parkingService.getAllParkingSlots();
    }

    @GetMapping("/slots/{slotId}")
    public ParkingSlot getParkingSlotById(@PathVariable Long slotId) {
        return parkingService.getParkingSlotById(slotId);
    }

    @PostMapping("/reserve")
    public Reservation reserveParkingSlot(@RequestParam Long slotId, @RequestParam String userId) {
        return parkingService.reserveParkingSlot(slotId, userId);
    }

    @PostMapping("/release")
    public void releaseParkingSlot(@RequestParam Long slotId) {
        parkingService.releaseParkingSlot(slotId);
    }
}

