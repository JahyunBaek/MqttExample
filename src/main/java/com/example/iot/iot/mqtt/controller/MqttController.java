package com.example.iot.iot.mqtt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.iot.iot.mqtt.dto.SampleDTO;
import com.example.iot.iot.mqtt.pub.MqttPublisher;

import lombok.RequiredArgsConstructor;

/**
 * This class is responsible for handling MQTT related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mqtt")
public class MqttController {
    
    private final MqttPublisher mqttPublisher;
    
    /**
     * Publishes a message to a MQTT topic.
     * 
     * @param sampleDTO The data to be published.
     * @return ResponseEntity with HTTP status 200 if successful.
     */
    @PostMapping("/pub")
    public ResponseEntity<?> pubTopic(@RequestBody SampleDTO sampleDTO) {
        mqttPublisher.pubTopic(sampleDTO);

        return ResponseEntity.ok().build();
    }
}
