package com.parking.stream;

import com.parking.model.ParkingEvent;
import com.parking.model.ParkingStatus;
import com.parking.util.StreamUtil;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(ParkingStreamBinding.class)
public class ParkingStreamProcessor {

    @StreamListener(ParkingStreamBinding.INPUT)
    public void processParkingEvents(KStream<String, ParkingEvent> input) {
        input
            .groupByKey()
            .aggregate(
                ParkingStatus::new,
                (key, event, aggregate) -> StreamUtil.updateParkingStatus(aggregate, event),
                StreamUtil.getMaterialized())
            .toStream()
            .to(ParkingStreamBinding.OUTPUT, StreamUtil.getProducingOptions());
    }
}
