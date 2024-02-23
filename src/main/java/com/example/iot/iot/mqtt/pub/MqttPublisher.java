package com.example.iot.iot.mqtt.pub;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iot.iot.mqtt.dto.SampleDTO;

import lombok.RequiredArgsConstructor;

/**
 * This class represents an MQTT publisher that publishes messages to a specified topic.
 */
@Service
@RequiredArgsConstructor
public class MqttPublisher {

    private final MqttClient mqttClient;

    /**
     * Publishes a message to the specified topic.
     *
     * @param sampleDTO the data transfer object containing the message and topic information
     */
    public void pubTopic(SampleDTO sampleDTO) {
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(String.valueOf(sampleDTO.getMessage()).getBytes());
            mqttClient.publish(sampleDTO.getTopic(), message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}