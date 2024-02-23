package com.example.iot.iot.mqtt.config;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

@Configuration 
public class MqttConfig {

    /**
     * MqttConnectOptions 빈을 생성합니다.
     * MQTT 브로커에 연결할 때 사용할 옵션을 설정합니다.
     *
     * @return MqttConnectOptions 객체
     */
    @Bean
    public MqttConnectOptions mqttConnectOptions(){
        MqttConnectOptions option = new MqttConnectOptions();
        /*
            MQTT 브로커의 URI를 설정합니다. 로컬 호스트의 1883 포트를 사용합니다.
            여러 개의 URI 설정도 가능합니다.
         */
        option.setServerURIs(new String[]{"tcp://localhost:1883"});
        return option;
    }

    /**
     * MqttPahoClientFactory 빈을 생성합니다.
     * MQTT 클라이언트 인스턴스를 생성할 때 사용합니다.
     *
     * @return MqttPahoClientFactory 객체
     */
    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory(){
        // DefaultMqttPahoClientFactory 클래스는 MqttPahoClientFactory 인터페이스를 구현합니다.
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        // 생성한 MqttConnectOptions 빈을 사용하여 연결 옵션을 설정합니다.
        factory.setConnectionOptions(mqttConnectOptions());
        return factory;
    }

    /**
     * MQTT 브로커 URL로 "someClientId"를 사용하는 MqttClient 빈을 생성합니다.
     *
     * @return MqttClient 객체
     * @throws MqttException MqttClient 생성 중 발생하는 예외
     */
    @Bean
    public MqttClient mqttClient() throws MqttException{
        return new MqttClient("tcp://localhost:1883", "someClientId");
    }

    /**
     * MqttPahoMessageDrivenChannelAdapter 빈을 생성합니다.
     * MqttPahoClientFactory를 사용하여 MQTT 메시지를 수신하는 채널 어댑터를 설정합니다.
     *
     * @param mqttPahoClientFactory MqttPahoClientFactory 객체
     * @return MqttPahoMessageDrivenChannelAdapter 객체
     */
    @Bean
    public MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter(MqttPahoClientFactory mqttPahoClientFactory){
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("someClientId", mqttPahoClientFactory,
                "topic1", "topic2");
        adapter.setCompletionTimeout(5000);  // 타임아웃 설정
        adapter.setConverter(new DefaultPahoMessageConverter());  // 메시지 컨버터 설정
        adapter.setQos(1);  // Quality of Service 설정
        adapter.setOutputChannelName("mqttInputChannel");//출력 채널 설정
        return adapter;
    }
}