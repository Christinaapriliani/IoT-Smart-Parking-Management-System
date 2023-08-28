package com.parking.util;

import com.parking.model.EventType;
import com.parking.model.ParkingEvent;
import com.parking.model.ParkingStatus;
import lombok.experimental.UtilityClass;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

@UtilityClass
public class StreamUtil {

    public Materialized<String, ParkingStatus, ?> getMaterialized() {
        return Materialized.with(Serdes.String(), new JsonSerde<>(ParkingStatus.class));
    }

    public Produced<String, ParkingStatus> getProducingOptions() {
        return Produced.with(Serdes.String(), new JsonSerde<>(ParkingStatus.class));
    }

    public ParkingStatus updateParkingStatus(ParkingStatus status, ParkingEvent event) {
        if (event.getEventType() == EventType.ENTRY) {
            status.setTotalOccupied(status.getTotalOccupied() + 1);
        } else if (event.getEventType() == EventType.EXIT) {
            status.setTotalOccupied(status.getTotalOccupied() - 1);
        }
        status.setTotalVacant(status.getTotalVacant() + 1);
        return status;
    }
}
