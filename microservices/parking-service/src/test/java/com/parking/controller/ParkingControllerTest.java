package com.parking.controller;

import com.parking.model.ParkingSlot;
import com.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ParkingController.class)
public class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    @Test
    public void testGetAllParkingSlots() throws Exception {
        List<ParkingSlot> slots = new ArrayList<>();
        ParkingSlot slot1 = new ParkingSlot();
        slot1.setId(1L);
        slot1.setSlotNumber("A1");
        slot1.setOccupied(false);

        ParkingSlot slot2 = new ParkingSlot();
        slot2.setId(2L);
        slot2.setSlotNumber("B1");
        slot2.setOccupied(true);

        slots.add(slot1);
        slots.add(slot2);

        Mockito.when(parkingService.getAllParkingSlots()).thenReturn(slots);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/parking/slots"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].slotNumber").value("A1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].occupied").value(false))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].slotNumber").value("B1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].occupied").value(true));
			   
    }
		
    @Test
    public void testReserveParkingSlot() throws Exception {
        // Mock the response from the service
        Mockito.when(parkingService.reserveParkingSlot(Mockito.anyLong(), Mockito.anyString()))
               .thenReturn(/* Return a Reservation object or null as needed */);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/parking/reserve")
               .param("slotId", "1")
               .param("userId", "user123"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testReleaseParkingSlot() throws Exception {
        // Mock the response from the service
        Mockito.doNothing().when(parkingService).releaseParkingSlot(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/parking/release")
               .param("slotId", "1"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

