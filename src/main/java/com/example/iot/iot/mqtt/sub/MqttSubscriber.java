package com.example.iot.iot.mqtt.sub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

import com.example.iot.iot.mqtt.entity.SampleEntity;
import com.example.iot.iot.mqtt.repository.MqttRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MqttSubscriber {

    private final MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter;
                  
    private final MqttRepository mqttRepository;

    /**
     * Receives a message from the input channel "topic1" and saves it to the database.
     *
     * @param payload the message payload
     */
    @ServiceActivator(inputChannel = "topic1")
    public void receiveMessage(String payload){

        log.info("Received message : " + payload);
        SampleEntity sampleEntity = SampleEntity.builder().message(payload).build();

        mqttRepository.save(sampleEntity);
    }

    /**
     * Receives a message from the input channel "topic2" and saves it to the database.
     *
     * @param payload the message payload
     */
    @ServiceActivator(inputChannel = "topic2")
    public void receiveTopic2(String payload){
        log.info("Received message : " + payload);
        SampleEntity sampleEntity = SampleEntity.builder().message(payload).build();

        mqttRepository.save(sampleEntity);
    }
}