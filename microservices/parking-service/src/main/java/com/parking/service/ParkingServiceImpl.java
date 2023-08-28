package com.parking.service;

import com.parking.model.ParkingSlot;
import com.parking.model.Reservation;
import com.parking.repository.ParkingSlotRepository;
import com.parking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSlotRepository slotRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ParkingServiceImpl(ParkingSlotRepository slotRepository, ReservationRepository reservationRepository) {
        this.slotRepository = slotRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ParkingSlot> getAllParkingSlots() {
        return slotRepository.findAll();
    }

    @Override
    public ParkingSlot getParkingSlotById(Long slotId) {
        return slotRepository.findById(slotId).orElse(null);
    }

    @Override
    public Reservation reserveParkingSlot(Long slotId, String userId) {
        ParkingSlot slot = slotRepository.findById(slotId).orElse(null);
        if (slot != null && !slot.isOccupied()) {
            // Set the slot as occupied
            slot.setOccupied(true);
            slotRepository.save(slot);

            // Calculate reservation timings
            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime endTime = startTime.plusHours(1); // Adjust as needed

            // Create and save reservation
            Reservation reservation = new Reservation(slotId, userId, startTime, endTime);
            return reservationRepository.save(reservation);
        }
        return null; // Return appropriate response when slot cannot be reserved
    }

    @Override
    public void releaseParkingSlot(Long slotId) {
        Optional<ParkingSlot> optionalSlot = slotRepository.findById(slotId);
        if (optionalSlot.isPresent()) {
            ParkingSlot slot = optionalSlot.get();
            // Set the slot as unoccupied
            slot.setOccupied(false);
            slotRepository.save(slot);

            // Delete associated reservation
            reservationRepository.deleteBySlotId(slotId);
        }
    }
}
