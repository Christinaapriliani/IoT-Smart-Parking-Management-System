package com.parking.service;

import com.parking.model.ParkingSlot;
import com.parking.model.Reservation;
import com.parking.repository.ParkingSlotRepository;
import com.parking.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class ParkingServiceTest {

    @Mock
    private ParkingSlotRepository slotRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ParkingServiceImpl parkingService;

    @Test
    public void testGetAllParkingSlots() {
        List<ParkingSlot> slots = new ArrayList<>();
        // Add sample ParkingSlot objects to the list

        Mockito.when(slotRepository.findAll()).thenReturn(slots);

        List<ParkingSlot> result = parkingService.getAllParkingSlots();

        assertEquals(slots.size(), result.size());
        // Add more specific assertions if needed
    }

    @Test
    public void testGetParkingSlotById() {
        Long slotId = 1L;
        ParkingSlot slot = new ParkingSlot();
        slot.setId(slotId);

        Mockito.when(slotRepository.findById(slotId)).thenReturn(Optional.of(slot));

        ParkingSlot result = parkingService.getParkingSlotById(slotId);

        assertEquals(slot.getId(), result.getId());
        // Add more specific assertions if needed
    }

    @Test
    public void testReserveParkingSlot() {
        Long slotId = 1L;
        String userId = "user123";

        ParkingSlot slot = new ParkingSlot();
        slot.setId(slotId);
        slot.setOccupied(false);

        Mockito.when(slotRepository.findById(slotId)).thenReturn(Optional.of(slot));
        Mockito.when(reservationRepository.save(any(Reservation.class))).thenReturn(new Reservation());

        Reservation result = parkingService.reserveParkingSlot(slotId, userId);

        assertNotNull(result);
        assertEquals(slotId, result.getSlotId());
        assertEquals(userId, result.getUserId());
        assertNotNull(result.getStartTime());
        assertNotNull(result.getEndTime());
        assertTrue(slot.isOccupied());
    }

    @Test
    public void testReleaseParkingSlot() {
        Long slotId = 1L;

        ParkingSlot slot = new ParkingSlot();
        slot.setId(slotId);
        slot.setOccupied(true);

        Mockito.when(slotRepository.findById(slotId)).thenReturn(Optional.of(slot));
        Mockito.doNothing().when(reservationRepository).deleteBySlotId(slotId);

        parkingService.releaseParkingSlot(slotId);

        assertFalse(slot.isOccupied());
    }

}
