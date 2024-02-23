package com.example.iot.iot.mqtt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.iot.iot.mqtt.entity.SampleEntity;

public interface MqttRepository extends JpaRepository<SampleEntity, Long> {
    // Add custom repository methods here if needed
}
